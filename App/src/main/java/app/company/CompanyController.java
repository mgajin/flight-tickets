package app.company;

import app.ticket.Ticket;
import app.ticket.TicketService;
import app.utils.ErrorResponse;
import app.utils.SuccessResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class CompanyController {

    private CompanyService companyService;
    private TicketService ticketService;

    private static final Gson gson = new Gson();

    public CompanyController(CompanyService companyService, TicketService ticketService) {
        this.companyService = companyService;
        this.ticketService = ticketService;
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

    public Route getCompany = (Request req, Response res) -> {
        int id = Integer.parseInt(req.params("id"));
        Company company = companyService.getCompany(id);
        List<Ticket> tickets;

        if (company == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Company not found");
            res.status(404);
            return errorResponse.toJson();
        }
        tickets = ticketService.getCompanyTickets(id);
        if (tickets == null) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Tickets not found");
            res.status(404);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCompany(company);
        successResponse.setTickets(tickets);

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

    public Route updateCompany = (Request req, Response res) -> {
        String body = req.body();
        System.out.println("Edit company: " + body);

        JsonObject json = gson.fromJson(body, JsonObject.class);
        int id = json.get("id").getAsInt();
        String name = json.get("name").getAsString();

        Company company = new Company();
        company.setId(id);
        company.setName(name);

        if (!companyService.updateCompany(company)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while updating company");
            res.status(501);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCompanies(companyService.getCompanies());

        res.status(200);
        return successResponse.toJson();
    };

    public Route deleteCompany = (Request req, Response res) -> {

        int id = Integer.parseInt(req.params("id"));
        System.out.println(id);

        res.type("application/json");

        if (!companyService.deleteCompany(id)) {
            ErrorResponse errorResponse = new ErrorResponse();
            errorResponse.setMessage("Error while deleting company");
            res.status(501);
            return errorResponse.toJson();
        }

        SuccessResponse successResponse = new SuccessResponse();
        successResponse.setCompanies(companyService.getCompanies());

        res.status(200);
        return successResponse.toJson();
    };
}
