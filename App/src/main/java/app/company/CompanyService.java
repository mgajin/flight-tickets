package app.company;

import app.database.Dao;

import java.util.List;

public class CompanyService {

    private final Dao<Company> repository;

    public CompanyService(Dao<Company> repository) {
        this.repository = repository;
    }

    public List<Company> getCompanies() {
        return repository.getAll();
    }

    public boolean createCompany(Company company) {
        return repository.insert(company);
    }

}
