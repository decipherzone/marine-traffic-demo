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
public final class PortExpectedArrivalsService {

    /**
     * This method hit respective api and get data for expected arrival of ports
     */
    public void getExpectedArrivalsOnPort() {

        PortService portService = new PortService();
        List<Port> portList = portService.getAllPorts();

        JSONArray resultArray = null;
        try {

            for (Port port : portList) {
                resultArray = HTTPService.getJSONArray(HTTPService.sendGet(Application.API_ENDPOINT + "expectedarrivals/v:3/"+ Application.API_KEY +"/portid:"+port.getPortId()+"/protocol:jsono/timespan:1"));
                if (resultArray != null) {
                    portService.saveExpectedArrivalOnPort(port, resultArray);
                }
            }

        } catch (Exception e) {
            System.out.println("Error while getting data for expected arrival for port in PortExpectedArrivalsService(): " + e);
        }

    }

}
