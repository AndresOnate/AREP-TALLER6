package org.edu.eci.arep;

import static spark.Spark.*;

public class Main {
    public static void main(String[] args) {

        staticFiles.location("/public");
        get("/log", (req,res) ->  RRInvoker.invoke());

    }
}