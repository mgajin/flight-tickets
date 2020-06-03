package app.database;

import java.sql.*;
import java.util.Properties;

public class Database {

    protected static Properties props;
    private static Connection connection;

    public static void init(Properties properties) {
        props = properties;

        try {
            connection = connect();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(props.getProperty("url"), props);
    }

}
