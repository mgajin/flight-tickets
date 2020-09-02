package app.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Dao<T> {

    protected final Connection connection = Database.getConnection();

    public abstract List<T> getAll();
    public abstract T getById(int id);
    public abstract boolean insert(T item);
    public abstract boolean delete(T item);

    protected abstract void readResultSet(ResultSet resultSet, List<T> items) throws SQLException;
    protected abstract T getResultData(ResultSet resultSet) throws SQLException;
}
