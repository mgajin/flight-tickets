package app.user;

import static spark.Spark.*;

public class UserRoutes {

    private final UserController controller;

    public UserRoutes(UserController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("app/users", controller.getUsers);
        put("app/users/:id", controller.updateUser);
    }
}
