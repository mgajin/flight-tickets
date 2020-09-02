package app.user;

import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class UserController {

    private UserService userService;

    private final Gson gson = new Gson();

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public Route getUsers = (Request req, Response res) -> {
        List<User> users = userService.getUsers();
        res.type("application/json");

        if (users == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Users not found");
            res.status(404);

            return errorResponse.toJson();
        }
        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setUsers(users);

        res.status(200);
        return successResponse.toJson();
    };

    public Route updateUser = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Got: " + body);
        JsonObject json = gson.fromJson(body, JsonObject.class);

        int id = json.get("id").getAsInt();
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        String type = json.get("type").getAsString();

        UserType userType = (type.equals("ADMIN")) ? UserType.ADMIN : UserType.USER;

        User user = new User();
        user.setId(id);
        user.setUsername(username);
        user.setPassword(password);
        user.setType(userType);

        res.type("application/json");

        if (!userService.updateUser(user)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while updating user");
            res.status(500);

            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setUsers(userService.getUsers());

        res.status(200);
        return successResponse.toJson();
    };
}
