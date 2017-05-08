package com.decipherzone.loginno.service;

import com.decipherzone.loginno.Application;
import com.decipherzone.loginno.model.Vessel;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by decipher on 6/5/17.
 */
public class VesselRouteStatusService {

    public void getPresentRouteStatusOfVessel() {
        try {

            VesselService vesselService = new VesselService();

            List<Vessel> vesselList = vesselService.getAllVessel();


            DefaultHttpClient httpClient = null;
            HttpGet getRequest = null;
            HttpResponse response = null;
            BufferedReader br = null;

            String apiUrl = Application.getAPIUrl();
            JSONParser jsonParser = new JSONParser();
            String output = "";
            JSONArray resultArray = null;

            for (Vessel vessel : vesselList) {

                System.out.println("Vessel Name : " + vessel.getVesselName());
                httpClient = new DefaultHttpClient();

                getRequest = new HttpGet(apiUrl + "exportvessel/v:5/1372a2fb73966aa64dfeae8e2f3cdf99b4f1be5e/timespan:20/mmsi:" + vessel.getMmsi() + "/protocol:jsono/msgtype:extended");

                response = httpClient.execute(getRequest);

                if (response.getStatusLine().getStatusCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + response.getStatusLine().getStatusCode());
                }

                br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);

                    resultArray = (JSONArray) jsonParser.parse(output);

                    if(resultArray != null) {
                        vesselService.addVesselRouteStatus(vessel, resultArray);
                    }
                }

                httpClient.getConnectionManager().shutdown();
            }

        } catch (Exception e) {
            System.out.println("Error while getting present status of vessels in getPresentRouteStatusOfVessel() : " + e);
        }
    }

}
