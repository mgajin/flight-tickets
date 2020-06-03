package app.user;

import com.google.gson.Gson;
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



}
