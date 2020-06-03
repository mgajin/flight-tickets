package app.city;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CityController {

    private static Gson gson = new Gson();

    public static Route getCities = (Request req, Response res) -> {

        String response;
        List<City> cities = CityService.getCities();
        response = gson.toJson(cities);

        return response;
    };

    public static Route addCity = (Request req, Response res) -> {

        int status;
        String response;
        String body = req.body();
        City city = gson.fromJson(body, City.class);

        CityService.createCity(city);
        response = gson.toJson(city);

        return response;
    };

}
