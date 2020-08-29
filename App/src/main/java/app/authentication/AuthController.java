package app.authentication;

import app.user.User;
import app.user.UserService;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthController {

    private static final Gson gson = new Gson();

    public static Route login = (Request req, Response res) -> {

        int status;
        String response;
        String username = req.queryParams("username");
        String password = req.queryParams("password");
        User user = UserService.getUser(username);

        if (user == null) {
            status = 401;
            response = "User not found";
        } else if (!AuthService.matchPassword(password, user)) {
            status = 401;
            response = "Wrong password";
        } else {
            AuthService.login(user);
            status = 200;
            response = gson.toJson(user);
        }

        res.type("application/json");
        res.status(status);

        return response;
    };

    public static Route register = (Request req, Response res) -> {

        int status;
        String response;
        String body = req.body();
        User user = gson.fromJson(body, User.class);

        if (UserService.getUser(user.getUsername()) != null) {
            status = 401;
            response = "User already exists";
        } else {
            AuthService.registerUser(user);
            status = 201;
            response = gson.toJson(user);
        }

        res.type("application/json");
        res.status(status);

        return response;
    };

}
