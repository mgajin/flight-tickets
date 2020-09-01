package app.authentication;

import static spark.Spark.post;

public class AuthRoutes {

    static {
        post("app/auth/login", AuthController.login);
        post("app/auth/register", AuthController.register);
    }
}
