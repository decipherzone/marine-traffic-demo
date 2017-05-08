package com.decipherzone.loginno.service;

import com.decipherzone.loginno.dao.PortDao;
import com.decipherzone.loginno.model.Port;
import com.decipherzone.loginno.model.PortExpectedArrival;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by decipher on 8/5/17.
 */
public class PortService {

    private PortDao portDao;

    public PortService() {
        portDao = new PortDao();
    }

    public List<Port> getAllPorts() {
        return portDao.getAllPorts();
    }

    public void saveExpectedArrivalOnPort(Port port, JSONArray resultArray) {

        if (resultArray != null) {

            JSONObject vesselArrivalObj = null;
            PortExpectedArrival portExpectedArrival = null;
            List<PortExpectedArrival> portExpectedArrivalList = new ArrayList<>();

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            String mmsi = "";
            Double latitude = 0.0;
            Double longitude = 0.0;
            String currentPort = "";
            String currentPortCountry = "";
            String nextPortUnCode = "";
            Date estimateTimeOfArrival;
            String estimatedDate = "";

            try {

                for (int i = 0; i < resultArray.size(); i++) {
                    vesselArrivalObj = (JSONObject) resultArray.get(i);

                    if (vesselArrivalObj != null) {

                        portExpectedArrival = new PortExpectedArrival();
                        latitude = Double.parseDouble(vesselArrivalObj.get("LAT").toString());
                        longitude = Double.parseDouble(vesselArrivalObj.get("LON").toString());
                        mmsi = (String) vesselArrivalObj.get("MMSI");
                        currentPort = (String) vesselArrivalObj.get("CURRENT_PORT");
                        currentPortCountry = (String) vesselArrivalObj.get("CURRENT_PORT_COUNTRY");
                        nextPortUnCode = (String) vesselArrivalObj.get("NEXT_PORT_UNLOCODE");
                        estimatedDate = (String) vesselArrivalObj.get("ETA");

                        estimateTimeOfArrival = dateFormat.parse(estimatedDate);

                        portExpectedArrival.setPort(port);
                        portExpectedArrival.setMmsi(mmsi);
                        portExpectedArrival.setLatitude(latitude);
                        portExpectedArrival.setLongitude(longitude);
                        portExpectedArrival.setCurrentPort(currentPort);
                        portExpectedArrival.setCurrentPortCountry(currentPortCountry);
                        portExpectedArrival.setNextPortUnCode(nextPortUnCode);
                        portExpectedArrival.setEstimateTimeOfArrival(estimateTimeOfArrival);
                        portExpectedArrival.setLastUpdatedAt(new Date());
                        portExpectedArrival.setUploaded(true);

                        portExpectedArrivalList.add(portExpectedArrival);
                    }
                }

                portDao.savePortExpectedArrivals(portExpectedArrivalList);
            } catch (Exception e) {
                System.out.println("Error while getting expected arrival in saveExpectedArrivalOnPort() " + e);
            }

        }

    }
}
