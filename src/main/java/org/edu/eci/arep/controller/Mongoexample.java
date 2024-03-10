package org.edu.eci.arep.controller;

import com.mongodb.client.MongoDatabase;

import java.util.Date;


public class Mongoexample {
        public static void main(String[] args) {
            MongoDatabase database = MongoUtil.getDB();
            LogDAO logDAO = new LogDAO(database);
            logDAO.addLog("Inicio de sesión", new Date());
            String jsonLogs = logDAO.listLogs();
            System.out.println(jsonLogs);
        }

}
