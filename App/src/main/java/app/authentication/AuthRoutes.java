package app.authentication;

import static spark.Spark.post;

public class AuthRoutes {

    private final AuthController controller;

    public AuthRoutes(AuthController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        post("app/auth/login", controller.login);
        post("app/auth/register", controller.register);
    }
}
