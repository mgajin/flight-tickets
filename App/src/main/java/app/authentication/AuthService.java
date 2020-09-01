package app.authentication;

import app.user.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;

public class AuthService {

    static Key key = MacProvider.generateKey();

    public boolean matchPassword(String password, User user) {
        return password.equals(user.getPassword());
    }

    public String getToken(User user) {
        return this.generateToken(user.getUsername());
    }

    private String generateToken(String username) {
        return Jwts.builder().setSubject(username)
            .setExpiration(new Date(new Date().getTime() + 1000*60*60L))
            .setIssuedAt(new Date())
            .signWith(SignatureAlgorithm.HS512, key)
            .compact();
    }
}
