package com.gcy.ssm.beans;

import java.util.Date;

public class DeviceTrouble {
    private String troubleId;

    private String troubleDirect;

    private Integer troubleNum;

    private Date troubleDate;

    private Date repairDate;

    private String devState;

    private String devId;

    public String getTroubleId() {
        return troubleId;
    }

    public void setTroubleId(String troubleId) {
        this.troubleId = troubleId == null ? null : troubleId.trim();
    }

    public String getTroubleDirect() {
        return troubleDirect;
    }

    public void setTroubleDirect(String troubleDirect) {
        this.troubleDirect = troubleDirect == null ? null : troubleDirect.trim();
    }

    public Integer getTroubleNum() {
        return troubleNum;
    }

    public void setTroubleNum(Integer troubleNum) {
        this.troubleNum = troubleNum;
    }

    public Date getTroubleDate() {
        return troubleDate;
    }

    public void setTroubleDate(Date troubleDate) {
        this.troubleDate = troubleDate;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getDevState() {
        return devState;
    }

    public void setDevState(String devState) {
        this.devState = devState == null ? null : devState.trim();
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId == null ? null : devId.trim();
    }
}