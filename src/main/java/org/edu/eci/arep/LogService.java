package org.edu.eci.arep;

import com.mongodb.client.MongoDatabase;
import org.edu.eci.arep.controller.LogDAO;
import org.edu.eci.arep.controller.MongoUtil;

import java.util.Date;

import static spark.Spark.*;

public class LogService {

    public static void main(String[] args) {

        port(getPort());
        MongoDatabase database = MongoUtil.getDB();
        LogDAO logDAO = new LogDAO(database);

        get("/hello", (req,res) ->  {
            return "Servicio Funcionando";
        });

        get("/logservice", (req,res) ->  {
            System.out.println("Solicitud al Log Service");
            res.type("application/json");
            logDAO.addLog(req.queryParams("msg"), new Date());
            return logDAO.listLogs();
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}
