package com.decipherzone.loginno.service;

import com.decipherzone.loginno.Application;
import com.decipherzone.loginno.errors.DataNotAvailableException;
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
public final class VesselRouteStatusService {

    public void getPresentRouteStatusOfVessel() {
        try {

            VesselService vesselService = new VesselService();
            List<Vessel> vesselList = vesselService.getAllVessel();

            JSONArray resultArray = null;

            for (Vessel vessel : vesselList) {
                System.out.println("Vessel Name : " + vessel.getVesselName());
                resultArray = HTTPService.getJSONArray(HTTPService.sendGet(Application.API_ENDPOINT + "exportvessel/v:5/" +Application.API_KEY+ "/timespan:20/mmsi:" + vessel.getMmsi() + "/protocol:jsono/msgtype:extended"));
                try {
                    vesselService.addVesselRouteStatus(vessel, resultArray);
                } catch (DataNotAvailableException e) {
                    e.printStackTrace();
                }
            }

        } catch (Exception e) {
            System.out.println("Error while getting present status of vessels in getPresentRouteStatusOfVessel() : " + e);
        }
    }

}
