package app.city;

import static spark.Spark.*;

public class CityRoutes {

    static {
        get("app/cities", CityController.getCities);
        post("app/cities", CityController.addCity);

    }
}
