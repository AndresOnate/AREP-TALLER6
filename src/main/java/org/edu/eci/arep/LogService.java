package org.edu.eci.arep;

import static spark.Spark.*;

public class LogService {

    public static void main(String[] args) {
        port(5000); //No hacer quemado, configurar

        get("/logservice", (req,res) ->  "{\"msg\": \"Primer mensaje, 2024-07-03\"}");
    }
}
