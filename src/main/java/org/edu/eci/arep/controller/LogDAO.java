package org.edu.eci.arep.controller;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Sorts;
import org.bson.Document;
import com.mongodb.client.FindIterable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;

public class LogDAO {

    private final MongoCollection<Document> logsCollection;

    public LogDAO(MongoDatabase database) {
        this.logsCollection = database.getCollection("logs");
    }

    public void addLog(String msg, Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String dateString = dateFormat.format(date);
        Document newLog = new Document("Date", dateString)
                .append("log", msg);
        logsCollection.insertOne(newLog);
    }

    public String listLogs() {
        FindIterable<Document> logs = logsCollection.find().sort(Sorts.descending("Date")).limit(10);
        List<String> jsonLogs = new ArrayList<>();
        for (Document log : logs) {
            log.remove("_id");
            jsonLogs.add(log.toJson());
        }
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < jsonLogs.size(); i++) {
            result.append(jsonLogs.get(i));
            if (i < jsonLogs.size() - 1) {
                result.append(",");
            }
        }
        result.append("]");
        return result.toString();
    }


    public void deleteLog(String msg) {
        logsCollection.deleteOne(eq("log", msg));
    }
}
