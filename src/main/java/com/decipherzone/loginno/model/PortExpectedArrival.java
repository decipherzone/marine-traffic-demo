package com.decipherzone.loginno.model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by decipher on 8/5/17.
 */
@Entity
@Table(name = "port_expected_arrival")
public class PortExpectedArrival extends IdentityModel {

    @OneToOne
    @JoinColumn(name = "id")
    private Port port;

    @Column(name = "mmsi")
    private String mmsi;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "currentport")
    private String currentPort;

    @Column(name = "currentportcountry")
    private String currentPortCountry;

    @Column(name = "nextportuncode")
    private String nextPortUnCode;

    @Column(name = "estimatetimeofarrival")
    private Date estimateTimeOfArrival;

    @Column(name = "lastupdatedat")
    private Date lastUpdatedAt;

    public PortExpectedArrival() {
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
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

    public String getCurrentPort() {
        return currentPort;
    }

    public void setCurrentPort(String currentPort) {
        this.currentPort = currentPort;
    }

    public String getCurrentPortCountry() {
        return currentPortCountry;
    }

    public void setCurrentPortCountry(String currentPortCountry) {
        this.currentPortCountry = currentPortCountry;
    }

    public String getNextPortUnCode() {
        return nextPortUnCode;
    }

    public void setNextPortUnCode(String nextPortUnCode) {
        this.nextPortUnCode = nextPortUnCode;
    }

    public Date getEstimateTimeOfArrival() {
        return estimateTimeOfArrival;
    }

    public void setEstimateTimeOfArrival(Date estimateTimeOfArrival) {
        this.estimateTimeOfArrival = estimateTimeOfArrival;
    }

    public Date getLastUpdatedAt() {
        return lastUpdatedAt;
    }

    public void setLastUpdatedAt(Date lastUpdatedAt) {
        this.lastUpdatedAt = lastUpdatedAt;
    }
}
