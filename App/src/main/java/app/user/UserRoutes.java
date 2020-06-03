package app.user;

import static spark.Spark.*;

public class UserRoutes {

    static {
        get("app/users", UserController.getUsers);
    }
}
