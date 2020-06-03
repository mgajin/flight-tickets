package app.company;

import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDAO {

    private final Connection connection = Database.getConnection();

    public void createCompany(Company company) {
        String query = "INSERT INTO companies (name) values (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, company.getName());
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Company> getCompanies() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM companies";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Company company = new Company();
                company.setId(id);
                company.setName(name);
                companies.add(company);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return companies;
    }
}
