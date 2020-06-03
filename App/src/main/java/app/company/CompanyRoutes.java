package app.company;

import static spark.Spark.*;

public class CompanyRoutes {

    static {
        get("app/companies", CompanyController.getCompanies);
        post("app/companies", CompanyController.addCompany);
    }

}
