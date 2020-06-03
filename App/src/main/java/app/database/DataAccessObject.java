package app.database;

import java.util.List;

public interface DataAccessObject {

    Object getSingle();
    List<Object> getAll();
    void insert(Object o);

}
