package app.authentication;

import app.user.User;
import app.user.UserService;
import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.Route;

public class AuthController {

    private static final Gson gson = new Gson();
    private static final AuthService authService = new AuthService();
    private static final UserService userService = new UserService();

    public static Route login = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

        String username = (json.get("username").getAsString());
        String password = (json.get("password").getAsString());
        User user = userService.getUser(username);

        res.type("application/json");

        if (user == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("User not found");
            res.status(404);
            return errorResponse.toJson();
        } else if (!authService.matchPassword(password, user)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Wrong password!");
            res.status(401);
            return errorResponse.toJson();
        }

        String token = authService.getToken(user);
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setUser(user);
        successResponse.setToken(token);

        res.status(200);
        return successResponse.toJson();
    };

    public static Route register = (Request req, Response res) -> {
        String body = req.body();
        User user = gson.fromJson(body, User.class);

        res.type("application/json");

        if (userService.getUser(user.getUsername()) != null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("User already exists found");
            res.status(401);
            return errorResponse.toJson();
        } else if (!userService.addUser(user)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error creating new user");
            res.status(401);
            return errorResponse.toJson();
        }

        String token = authService.getToken(user);
        user = userService.getUser(user.getUsername());

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setUser(user);
        successResponse.setToken(token);

        res.status(201);
        return successResponse.toJson();
    };
}
