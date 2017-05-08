package com.decipherzone.loginno.service;

import com.decipherzone.loginno.dao.VesselDao;
import com.decipherzone.loginno.model.Vessel;
import com.decipherzone.loginno.model.VesselRouteStatus;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by decipher on 6/5/17.
 */
public class VesselService {

    private VesselDao vesselDao;

    public VesselService() {
        this.vesselDao = new VesselDao();
    }

    /**
     * This method returns list of vessels
     *
     * @return
     */
    public List<Vessel> getAllVessel() {
        return vesselDao.getAllVessel();
    }

    /**
     * This method add vessel route status
     *
     * @param vessel : object of vessel from which vessel route status is belonged
     * @return
     */
    public Map<String, Object> addVesselRouteStatus(Vessel vessel, JSONArray resultArray) {

        JSONObject resultObj = (JSONObject) resultArray.get(0);

        Double latitude = Double.parseDouble(resultObj.get("LAT").toString());
        Double longitude = Double.parseDouble(resultObj.get("LON").toString());
        Integer currentPortId = Integer.parseInt(resultObj.get("PORT_ID").toString());
        String currentPort = (String) resultObj.get("CURRENT_PORT");
        Integer lastPortId = Integer.parseInt(resultObj.get("LAST_PORT_ID").toString());
        Integer nextPortId = Integer.parseInt(resultObj.get("NEXT_PORT_ID").toString());

        VesselRouteStatus vesselRouteStatus = new VesselRouteStatus();

        vesselRouteStatus.setVessel(vessel);
        vesselRouteStatus.setLatitude(latitude);
        vesselRouteStatus.setLongitude(longitude);
        vesselRouteStatus.setCurrentPortId(currentPortId);
        vesselRouteStatus.setCurrentPort(currentPort);
        vesselRouteStatus.setLastPortId(lastPortId);
        vesselRouteStatus.setNextPortId(nextPortId);
        vesselRouteStatus.setLastUpdatedTime(new Date());

        return vesselDao.addVesselRouteStatus(vesselRouteStatus);
    }

}
