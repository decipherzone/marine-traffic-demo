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

    /**
     * This method hit respective api and get data for expected arrival of ports
     */
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

//            System.out.println("URL : " + apiUrl + "expectedarrivals/v:3/58d19e48d0faf49bac02e18a2e5d2e575587174d/portid:1458/protocol:jsono/fromdate:2017-05-08 03:00:00/timespan:1");
            for (Port port : portList) {

                System.out.println("Vessel Name : " + port.getUncode());
                httpClient = new DefaultHttpClient();

                getRequest = new HttpGet(apiUrl + "expectedarrivals/v:3/58d19e48d0faf49bac02e18a2e5d2e575587174d/portid:"+port.getPortId()+"/protocol:jsono/timespan:1");

                response = httpClient.execute(getRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

                System.out.println("Output for expected arrival .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);

//                output = "[{\"MMSI\":\"431138000\",\"LAT\":\"21.256180\",\"LON\":\"-158.472100\",\"SPEED\":\"10\",\"COURSE\":\"138\",\"STATUS\":\"0\",\"PORT_ID\":\"\",\"PORT_UNLOCODE\":\"\",\"CURRENT_PORT\":\"\",\"CURRENT_PORT_COUNTRY\":\"\",\"NEXT_PORT_ID\":\"1458\",\"NEXT_PORT_UNLOCODE\":\"USHNL\",\"NEXT_PORT_NAME\":\"HONOLULU \",\"NEXT_PORT_COUNTRY\":\"US\",\"ETA\":\"2017-05-08T19:00:00\",\"ETA_CALC\":\"2017-05-08T14:10:00\",\"ETA_UPDATED\":\"2017-05-08T11:13:00\",\"TIMESTAMP\":\"2017-05-08T11:44:00\"},{\"MMSI\":\"367007920\",\"LAT\":\"21.160660\",\"LON\":\"-157.656500\",\"SPEED\":\"75\",\"COURSE\":\"289\",\"STATUS\":\"0\",\"PORT_ID\":\"\",\"PORT_UNLOCODE\":\"\",\"CURRENT_PORT\":\"\",\"CURRENT_PORT_COUNTRY\":\"\",\"NEXT_PORT_ID\":\"1458\",\"NEXT_PORT_UNLOCODE\":\"USHNL\",\"NEXT_PORT_NAME\":\"HONOLULU \",\"NEXT_PORT_COUNTRY\":\"US\",\"ETA\":\"2017-05-08T05:00:00\",\"ETA_CALC\":\"2017-05-08T14:02:00\",\"ETA_UPDATED\":\"2017-05-08T10:10:00\",\"TIMESTAMP\":\"2017-05-08T12:26:54\"},{\"MMSI\":\"538001647\",\"LAT\":\"21.230850\",\"LON\":\"-154.980200\",\"SPEED\":\"104\",\"COURSE\":\"278\",\"STATUS\":\"0\",\"PORT_ID\":\"\",\"PORT_UNLOCODE\":\"\",\"CURRENT_PORT\":\"\",\"CURRENT_PORT_COUNTRY\":\"\",\"NEXT_PORT_ID\":\"1458\",\"NEXT_PORT_UNLOCODE\":\"USHNL\",\"NEXT_PORT_NAME\":\"HONOLULU \",\"NEXT_PORT_COUNTRY\":\"US\",\"ETA\":\"2017-05-08T18:00:00\",\"ETA_CALC\":\"2017-05-09T05:53:00\",\"ETA_UPDATED\":\"2017-05-08T11:12:00\",\"TIMESTAMP\":\"2017-05-08T12:17:00\"},{\"MMSI\":\"564015000\",\"LAT\":\"21.166610\",\"LON\":\"-159.989100\",\"SPEED\":\"128\",\"COURSE\":\"155\",\"STATUS\":\"0\",\"PORT_ID\":\"\",\"PORT_UNLOCODE\":\"\",\"CURRENT_PORT\":\"\",\"CURRENT_PORT_COUNTRY\":\"\",\"NEXT_PORT_ID\":\"1458\",\"NEXT_PORT_UNLOCODE\":\"USHNL\",\"NEXT_PORT_NAME\":\"HONOLULU \",\"NEXT_PORT_COUNTRY\":\"US\",\"ETA\":\"2017-05-08T13:00:00\",\"ETA_CALC\":\"2017-05-08T22:24:00\",\"ETA_UPDATED\":\"2017-05-08T10:51:00\",\"TIMESTAMP\":\"2017-05-08T12:18:00\"}]";
                    resultArray = (JSONArray) jsonParser.parse(output);

                    if (resultArray != null) {
                        portService.saveExpectedArrivalOnPort(port, resultArray);
                    }
                }

                httpClient.getConnectionManager().shutdown();
            }

        } catch (Exception e) {
            System.out.println("Error while getting data for expected arrival for port in PortExpectedArrivalsService(): " + e);
        }

    }

}
