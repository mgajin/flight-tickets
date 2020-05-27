package app.routes;

import app.services.AuthService;

import static spark.Spark.*;

public class AuthRoutes {

    static {
        get("app/auth/user", AuthService.getMe);
        post("app/auth/login", AuthService.login);
        post("app/auth/register", AuthService.register);
    }

}
