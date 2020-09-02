package app.company;

import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CompanyController {

    private CompanyService companyService;

    private static final Gson gson = new Gson();

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Route getCompanies = (Request req, Response res) -> {
        res.type("application/json");
        List<Company> companies = companyService.getCompanies();

        if (companies == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Not found");
            res.status(404);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCompanies(companies);

        res.status(200);
        return successResponse.toJson();
    };

    public Route addCompany = (Request req, Response res) -> {
        String body = req.body();
        Company company = gson.fromJson(body, Company.class);

        res.type("application/json");

        if (!companyService.createCompany(company)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while creating company");
            res.status(501);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCompanies(companyService.getCompanies());

        res.status(201);
        return successResponse.toJson();
    };
}
