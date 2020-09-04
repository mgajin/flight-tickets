package app.company;

import app.database.Dao;
import app.ticket.Ticket;

import java.util.List;

public class CompanyService {

    private final Dao<Company> companyRepo;
    private final Dao<Ticket> ticketsRepo;

    public CompanyService(Dao<Company> companyRepo, Dao<Ticket> ticketsRepo) {
        this.companyRepo = companyRepo;
        this.ticketsRepo = ticketsRepo;
    }

    public List<Company> getCompanies() {
        return companyRepo.getAll();
    }

    public Company getCompany(int id) {
        return companyRepo.getById(id);
    }

    public boolean createCompany(Company company) {
        return companyRepo.insert(company);
    }

    public boolean updateCompany(Company company) {
        return companyRepo.update(company);
    }

    public boolean deleteCompany(int id) {
        String query = "DELETE FROM tickets WHERE company=?";
        if (ticketsRepo.delete(query, id)) {
            query = "DELETE FROM companies WHERE id = ?";
            return companyRepo.delete(query, id);
        }
        return false;
    }

}