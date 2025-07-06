package practice.jpa.util.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtProvider {

    private int accessTokenValiditySeconds = 3600;
    private int refreshTokenValiditySeconds = 3600;

    public String createToken() {
        String key = "A";

        Map<String, Object> headers = new HashMap<>();
        headers.put("typ", "JWT");
        headers.put("alg", "HS256");

        Map<String, Object> payloads = new HashMap<>();

        Date now = new Date();
        now.setTime(now.getTime() + accessTokenValiditySeconds * 1000);

        payloads.put("exp", now);
        payloads.put("data", "asdasd");

        String jwt = Jwts.builder()
                .setHeader(headers)
                .setClaims(payloads)
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        return jwt;
    }

}
