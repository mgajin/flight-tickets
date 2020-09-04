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
        get("app/companies/:id", controller.getCompany);
        post("app/companies", controller.addCompany);
        put("app/companies", controller.updateCompany);
        delete("app/companies/:id", controller.deleteCompany);
    }
}
