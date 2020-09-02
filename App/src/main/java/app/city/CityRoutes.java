package app.city;

import static spark.Spark.*;

public class CityRoutes {

    private final CityController controller;

    public CityRoutes(CityController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("app/cities", controller.getCities);
        post("app/cities", controller.addCity);

    }
}
