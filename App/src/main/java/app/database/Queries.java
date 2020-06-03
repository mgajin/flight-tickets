package app.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Queries {

    //  Get all flights from database
    public static ResultSet getFlights() throws SQLException {

        String query = "SELECT * FROM flights";
        PreparedStatement statement = Postgres.connection.prepareStatement(query);
        statement.execute();

        return statement.getResultSet();
    }

    //  Get all tickets from database
    public static ResultSet getTickets() throws SQLException {

        String query = "SELECT * FROM tickets";
        PreparedStatement statement = Postgres.connection.prepareStatement(query);
        statement.execute();

        return statement.getResultSet();
    }

    //  Get one-way or round-trip tickets from database
    public static ResultSet getFilteredTickets(boolean filter) throws SQLException {

        String query = "SELECT * FROM tickets WHERE oneWay= ?";
        PreparedStatement statement = Postgres.connection.prepareStatement(query);
        statement.setBoolean(1, filter);
        statement.execute();

        return statement.getResultSet();
    }

}
