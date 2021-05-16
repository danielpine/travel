package com.demo.travel.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

	private String key = "travel";
	private long ttl = 3600 * 24;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public long getTtl() {
		return ttl;
	}

	public void setTtl(long ttl) {
		this.ttl = ttl;
	}

	/**
	 * 生成JWT
	 *
	 * @param id
	 * @param subject
	 * @param roles
	 * @return
	 */
	public String createJWT(String id, String subject, String roles) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		JwtBuilder jwt = Jwts.builder().setId(id)
		        .setSubject(subject)
		        .setIssuedAt(now)
		        .signWith(SignatureAlgorithm.HS256, key)
		        .claim("roles", roles);
		if (ttl > 0) {
			jwt.setExpiration(new Date(nowMillis + ttl));
		}
		return jwt.compact();
	}

	/**
	 * 解析JWT
	 *
	 * @param jwtStr
	 * @return
	 */
	public Claims parseJWT(String jwtStr) {
		return Jwts.parser()
		        .setSigningKey(key)
		        .parseClaimsJws(jwtStr)
		        .getBody();
	}

}
