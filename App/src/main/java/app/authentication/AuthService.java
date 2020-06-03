package app.authentication;

import app.user.User;
import app.user.UserService;
import app.user.UserType;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AuthService {

    static Key key = MacProvider.generateKey();

    public static boolean matchPassword(String password, User user) {
        return password.equals(user.getPassword());
    }

    public static void login(User user) {
        user.setToken(generateToken(user));
    }

    public static void registerUser(User user) {
        user.setType(UserType.USER);
        user.setToken(generateToken(user));

        UserService.addUser(user);
    }

    private static String generateToken(User user) {

        String token;
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", user.getUsername());
        claims.put("password", user.getPassword());
        claims.put("user", user);

        token =  Jwts.builder().setSubject(user.getUsername())
//                .setClaims(claims)
                .setExpiration(new Date(new Date().getTime() + 1000*60*60L))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512, key)
                .compact();

        return  token;
    }
}
