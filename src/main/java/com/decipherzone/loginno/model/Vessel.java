/**
 * Created on 5/5/17 4:17 PM by Raja Dushyant Vashishtha
 * Sr. Software Engineer
 * rajad@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

package com.decipherzone.loginno.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "vessel")
public class Vessel extends IdentityModel {

    @Column(name = "vesselid")
    private String vesselId;

    @Column(name = "mmsi")
    private String mmsi;

    @Column(name = "vesselname")
    private String vesselName;

    public Vessel() {
    }

    public Vessel(String vesselId, String mmsi, String vesselName) {
        this.vesselId = vesselId;
        this.mmsi = mmsi;
        this.vesselName = vesselName;
    }

    public String getVesselId() {
        return vesselId;
    }

    public void setVesselId(String vesselId) {
        this.vesselId = vesselId;
    }

    public String getMmsi() {
        return mmsi;
    }

    public void setMmsi(String mmsi) {
        this.mmsi = mmsi;
    }

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }
}
