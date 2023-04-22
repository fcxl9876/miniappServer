package xin.fcxl9876.common.entity.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ALE
 * @since 2022-07-29
 */
@Data
public class AuditOperationDto implements Serializable {

    private String stationCode;
    private String stationName;
    private String time;
    private String startTime;
    private String endTime;
    private String projectCode;
    private String value;
    private String oldDataState;
    private String newDataState;
    private String message;
    private String auditType;
    // 审核员
    private String auditPerson;
    private String auditOperate;

    private Integer pageSize;
    private Integer pageNum;


    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getAuditPerson() {
        return auditPerson;
    }

    public void setAuditPerson(String auditPerson) {
        this.auditPerson = auditPerson;
    }

    public String getAuditOperate() {
        return auditOperate;
    }

    public void setAuditOperate(String auditOperate) {
        this.auditOperate = auditOperate;
    }

    public String getStationCode() {
        return stationCode;
    }

    public void setStationCode(String stationCode) {
        this.stationCode = stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getOldDataState() {
        return oldDataState;
    }

    public void setOldDataState(String oldDataState) {
        this.oldDataState = oldDataState;
    }

    public String getNewDataState() {
        return newDataState;
    }

    public void setNewDataState(String newDataState) {
        this.newDataState = newDataState;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
