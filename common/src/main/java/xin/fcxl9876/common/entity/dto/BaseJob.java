package xin.fcxl9876.common.entity.dto;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public interface BaseJob extends Job {

    public void execute(JobExecutionContext context) throws JobExecutionException;
}
