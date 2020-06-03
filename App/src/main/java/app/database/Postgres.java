package app.database;

import java.sql.*;
import java.util.Properties;

public class Postgres {

    protected static Properties props;
    protected static Connection connection;

    public static void init(Properties properties) {
        props = properties;

        try {
            connection = connect();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(props.getProperty("url"), props);
    }

}
