package app.company;

import java.util.List;

public class CompanyService {

    private static final CompanyDAO repository = new CompanyDAO();

    public static void createCompany(Company company) {
        repository.createCompany(company);
    }

    public static List<Company> getCompanies() {
        return repository.getCompanies();
    }

}
