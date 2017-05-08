/**
 * Created on 5/5/17 6:14 PM by Raja Dushyant Vashishtha
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
@Table(name = "port")
public final class Port extends IdentityModel {

    @Column(name = "portname")
    private String portName;

    @Column(name = "uncode")
    private String uncode;

    @Column(name = "portid")
    private String portId;

    public Port() {
    }

    public Port(String portName, String uncode) {
        this.portName = portName;
        this.uncode = uncode;
    }

    public String getPortName() {
        return portName;
    }

    public void setPortName(String portName) {
        this.portName = portName;
    }

    public String getUncode() {
        return uncode;
    }

    public void setUncode(String uncode) {
        this.uncode = uncode;
    }

    public String getPortId() {
        return portId;
    }

    public void setPortId(String portId) {
        this.portId = portId;
    }
}
