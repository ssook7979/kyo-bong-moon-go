package com.kyobong.store.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3552407348446466822L;
	
	public static final long JWT_TOKEN_VALIDITY = 60 * 60;
	
	@Value("${jwt.secret}")
	private String secret;
	
	public String getUsername(String token) {
		return getClaim(token, Claims::getSubject);
	}
	
	public Date getExpirationDate(String token) {
		return getClaim(token, Claims::getExpiration);
	}
	
	public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims getAllClaims(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDate(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		long currentTimeMillis = System.currentTimeMillis();
		String username = userDetails.getUsername();
		return Jwts
			.builder()
			.setClaims(claims)
			.setSubject(username)
			.setIssuedAt(new Date(currentTimeMillis))
			.setExpiration(new Date(currentTimeMillis + JWT_TOKEN_VALIDITY * 1000))
			.signWith(SignatureAlgorithm.HS512, secret)
			.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		String username = userDetails.getUsername();
		return !isTokenExpired(token) && getUsername(token).equals(username);
	}

}
