package app.city;

import app.database.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDao extends Dao<City> {

    public boolean insert(City city) {
        String query = "INSERT INTO cities (name) values (?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city.getName());
            statement.execute();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public City find(Object name) {
        City city = null;
        String query = "SELECT * FROM cities WHERE name= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, (String) name);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            if (resultSet.next()) {
                city = getResultData(resultSet);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return city;
    }

    @Override
    public List<City> getAll() {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM cities";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();
            readResultSet(resultSet, cities);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
        return cities;
    }

    @Override
    public City getById(int id) {
        return null;
    }

    @Override
    public boolean update(City item) {
        return false;
    }

    @Override
    public boolean delete(String query, int id) {
        return false;
    }

    @Override
    protected void readResultSet(ResultSet resultSet, List<City> cities) throws SQLException {
        while (resultSet.next()) {
            City city = getResultData(resultSet);
            cities.add(city);
        }
    }

    @Override
    protected City getResultData(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        City city = new City(name);
        city.setId(id);

        return city;
    }
}
