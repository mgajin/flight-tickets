package app.routes;

import app.controllers.AuthController;

import static spark.Spark.*;

public class AuthRoutes {

    static {
        get("app/auth/user", AuthController.getUser);
        get("app/auth/login", AuthController.login);
        post("app/auth/register", AuthController.register);
    }

}
