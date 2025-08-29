package Cybersoft.ExJPA_Security.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${jwt.private-key}")
    private String secretKey;

    private final long expireTime = 24 * 60 * 60 * 1000; // 1 ngay
    public String generateToken(String username, String role) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        Date now = new Date();
        Date expTime = new Date(now.getTime() + expireTime);
        return Jwts.builder()
                .subject(username)
                .claim("role",role)
                .signWith(key)
                .expiration(expTime)
                .compact();
    }

    public Claims decodeToken(String token) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();

    }
}
