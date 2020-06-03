package app.authentication;

import static spark.Spark.*;

public class AuthRoutes {

    static {
        get("app/auth/login", AuthController.login);
        post("app/auth/register", AuthController.register);
    }
}
