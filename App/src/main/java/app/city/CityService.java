package app.city;

import java.util.List;

public class CityService {

    private static final CityDAO repository = new CityDAO();

    public static void createCity(City city) {
        repository.createCity(city);
    }

    public static City getCity(City city) {
        return repository.getCity(city.getName());
    }

    public static List<City> getCities() {
        return repository.getCities();
    }

}