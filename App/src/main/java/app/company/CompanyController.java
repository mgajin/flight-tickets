package app.company;

import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CompanyController {

    private static final Gson gson = new Gson();

    public static Route getCompanies = (Request req, Response res) -> {

        String response;
        List<Company> companies = CompanyService.getCompanies();

        response = gson.toJson(companies);
        res.type("application/json");
        res.status(200);

        return response;
    };

    public static Route addCompany = (Request req, Response res) -> {

        String response;
        String body = req.body();
        Company company = gson.fromJson(body, Company.class);
        CompanyService.createCompany(company);

        response = gson.toJson(company);
        res.type("application/json");
        res.status(201);

        return response;
    };
}
