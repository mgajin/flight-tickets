package app.services;

import app.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    static Key key = MacProvider.generateKey();

    public static void login(User user) {
        user.setToken(generateToken(user));
    }

    private static String generateToken(User user) {

        String token;
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());
        claims.put("user", user);

        token = Jwts.builder().setSubject(user.getUsername())
                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + 1000*60*60L))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.ES512, key)
                .compact();

        return token;
    }

}
