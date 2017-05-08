package com.decipherzone.loginno.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by decipher on 6/5/17.
 */
@Entity
@Table(name = "vessel_route_status")
public final class VesselRouteStatus extends IdentityModel {

    @OneToOne
    @JoinColumn(name = "vesselid")
    private Vessel vessel;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "currentportid")
    private Integer currentPortId;

    @Column(name = "currentport")
    private String currentPort;

    @Column(name = "lastport")
    private Integer lastPortId;

    @Column(name = "nextportid")
    private Integer nextPortId;

    @Column(name = "lastupdatedtime")
    private Date lastUpdatedTime;

    public VesselRouteStatus() {
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getCurrentPortId() {
        return currentPortId;
    }

    public void setCurrentPortId(Integer currentPortId) {
        this.currentPortId = currentPortId;
    }

    public String getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    public Integer getLastPortId() {
        return lastPortId;
    }

    public void setLastPortId(Integer lastPortId) {
        this.lastPortId = lastPortId;
    }

    public Integer getNextPortId() {
        return nextPortId;
    }

    public void setNextPortId(Integer nextPortId) {
        this.nextPortId = nextPortId;
    }

    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }
}
