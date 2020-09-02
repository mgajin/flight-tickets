package app.city;

import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CityController {

    private CityService cityService;

    private final Gson gson = new Gson();

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    public Route getCities = (Request req, Response res) -> {
        List<City> cities = cityService.getCities();

        if (cities == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Cities not found");
            res.status(404);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCities(cities);

        res.status(200);
        return successResponse.toJson();
    };

    public Route addCity = (Request req, Response res) -> {
        String body = req.body();
        City city = gson.fromJson(body, City.class);

        if (!cityService.createCity(city)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while creating city");
            res.status(501);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCities(cityService.getCities());

        res.status(201);
        return successResponse.toJson();
    };

}
