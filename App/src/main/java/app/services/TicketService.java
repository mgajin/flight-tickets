package app.services;

import app.database.Database;
import spark.Request;
import spark.Response;
import spark.Route;

public class TicketService {

    public static Route getTickets = (Request req, Response res) -> {

        return Database.getAllTickets();
    };

}
