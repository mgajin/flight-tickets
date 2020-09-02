package app.company;

import static spark.Spark.*;

public class CompanyRoutes {

    private final CompanyController controller;

    public CompanyRoutes(CompanyController controller) {
        this.controller = controller;
        init();
    }

    private void init() {
        get("app/companies", controller.getCompanies);
        post("app/companies", controller.addCompany);
    }

}
