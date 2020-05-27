package app.controllers;

import app.models.User;
import app.services.AuthService;
import app.services.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthController {

    private static Gson gson = new Gson();

    public static Route getUser = (Request req, Response res) -> {

        return "";
    };

    public static Route login = (Request req, Response res) -> {

        String username = req.queryParams("username");
        String password = req.queryParams("password");

        User user = UserService.getUser(username);

        if (user == null) {
            res.status(401);
            return "User not found";
        } else if (!password.equals(user.getPassword())) {
            res.status(401);
            return "Wrong password!";
        }

        AuthService.login(user);
        res.type("application/json");
        res.status(200);

        return gson.toJson(user);
    };

    public static Route register = (Request req, Response res) -> {

        String body = req.body();
        User user = gson.fromJson(body, User.class);

        if (UserService.getUser(user.getUsername()) != null) {
            res.status(401);
            return "User already exists!";
        }

        AuthService.registerUser(user);
        res.type("application/json");
        res.status(201);

        return gson.toJson(user);
    };

}
