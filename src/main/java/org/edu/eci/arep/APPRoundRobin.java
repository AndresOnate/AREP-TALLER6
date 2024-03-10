package org.edu.eci.arep;

import static spark.Spark.*;

public class APPRoundRobin {
    public static void main(String[] args) {

        port(getPort());
        staticFiles.location("/public");

        get("/log", (req,res) ->  {
            res.type("application/json");
            return RRInvoker.invoke(req.queryParams("msg"));
        });

        get("/hello", (req,res) ->  {
            return "Servicio Funcionando";
        });
    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567;
    }
}