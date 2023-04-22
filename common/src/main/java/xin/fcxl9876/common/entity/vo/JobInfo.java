package xin.fcxl9876.common.entity.vo;


import lombok.Data;

@Data
public class JobInfo {

    private String jobClassName;

    private String jobGroupName;

    private String cronExpression;

    private String jobType;

    private Integer timeType;

}
