package app.user;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class UserController {

    private static Gson gson = new Gson();

    public static Route getUsers = (Request req, Response res) -> {

        String response;
        List<User> users = UserService.getUsers();

        res.type("application/json");
        response = gson.toJson(users);

        return response;
    };

    public static Route updateUser = (Request req, Response res) -> {
        String response;
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

        List<User> users = UserService.updateUser(user);

        res.type("application/json");
        response = gson.toJson(users);

        return response;
    };
}
