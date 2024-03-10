package org.edu.eci.arep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class RRInvoker {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static int currentLogServer = 0;
    private static final String[] LOG_SERVERS = new String[]{
            "http://log-service1:35000",
            "http://log-service2:35000",
            "http://log-service3:35000",
    };

    public static String invoke(String log) throws IOException {
        String encodedLog = URLEncoder.encode(log, "UTF-8");
        String GET_URL = LOG_SERVERS[currentLogServer] + "/logservice?msg=" + encodedLog;
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        roundRobindServer();
        return response.toString();
    }

    private static void roundRobindServer() {
        if(currentLogServer < 2){
            currentLogServer++;
        }else {
            currentLogServer = 0;
        }
    }
}
