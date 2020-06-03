package app.city;

import app.database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CityDAO {
    private final Connection connection = Database.getConnection();

    public void createCity(City city) {
        String query = "INSERT INTO cities (name) values (?)";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, city.getName());
            statement.execute();

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public City getCity(String name) {
        City city = null;
        String query = "SELECT * FROM cities WHERE name= ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String cityName = resultSet.getString("name");
                city = new City();
                city.setName(cityName);
                city.setId(id);
            }

        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return city;
    }

    public List<City> getCities() {
        List<City> cities = new ArrayList<>();
        String query = "SELECT * FROM cities";

        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.getResultSet();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                City city = new City();
                city.setId(id);
                city.setName(name);
                cities.add(city);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return cities;
    }

}
