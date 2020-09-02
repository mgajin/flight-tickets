package app.city;

import app.database.Dao;

import java.util.List;

public class CityService {

    private final Dao<City> repository;

    public CityService(Dao<City> repository) {
        this.repository = repository;
    }

    public List<City> getCities() {
        return repository.getAll();
    }

    public City getCity(City city) {
        return repository.find(city.getName());
    }

    public boolean createCity(City city) {
        return repository.insert(city);
    }

}