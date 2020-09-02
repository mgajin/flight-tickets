package app.company;

import app.database.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompanyDao extends Dao<Company> {

    @Override
    public boolean insert(Company company) {
        String query = "INSERT INTO companies (name) values (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, company.getName());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companies = new ArrayList<>();
        String query = "SELECT * FROM companies";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, companies);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return companies;
    }

    @Override
    public Company getById(int id) {
        return null;
    }

    @Override
    public Company find(Object item) {
        return null;
    }

    @Override
    public boolean update(Company item) {
        return false;
    }

    @Override
    public boolean delete(String query, int id) {
        return false;
    }

    @Override
    protected void readResultSet(ResultSet resultSet, List<Company> companies) throws SQLException {
        while (resultSet.next()) {
            Company company = getResultData(resultSet);
            companies.add(company);
        }
    }

    @Override
    protected Company getResultData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        Company company = new Company();
        company.setId(id);
        company.setName(name);

        return company;
    }
}
