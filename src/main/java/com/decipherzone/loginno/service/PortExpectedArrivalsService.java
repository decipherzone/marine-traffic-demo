package com.decipherzone.loginno.service;

import com.decipherzone.loginno.Application;
import com.decipherzone.loginno.model.Port;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by decipher on 8/5/17.
 */
public class PortExpectedArrivalsService {

    public void getExpectedArrivalsOnPort() {

        PortService portService = new PortService();
        List<Port> portList = portService.getAllPorts();

        DefaultHttpClient httpClient = null;
        HttpGet getRequest = null;
        HttpResponse response = null;
        BufferedReader br = null;

        String apiUrl = Application.getAPIUrl();
        JSONParser jsonParser = new JSONParser();
        String output = "";
        JSONArray resultArray = null;


        try {

            for (Port port : portList) {

                System.out.println("Vessel Name : " + port.getUncode());
                httpClient = new DefaultHttpClient();

                getRequest = new HttpGet(apiUrl + "expectedarrivals/v:3/22a3ca6fd5f4cbc33f6f5b2a52ec9b1eab63c17f/protocol:jsono/ fromdate:2017-05-08 03:00:00/timespan:1/portid:"+ port.getUncode());

                response = httpClient.execute(getRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

                System.out.println("Output for expected arrival .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);

                    resultArray = (JSONArray) jsonParser.parse(output);

                    if(resultArray != null) {
                        portService.saveExpectedArrivalOnPort(port, resultArray);
                    }
                }

                httpClient.getConnectionManager().shutdown();
            }

        } catch (Exception e) {
            System.out.println("Error while ");
        }

    }

}
