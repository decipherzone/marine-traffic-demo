package com.decipherzone.loginno.scheduler.job;

import com.decipherzone.loginno.service.PortExpectedArrivalsService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by decipher on 8/5/17.
 */
public final class PortExpectedArrivalsJob implements Job{
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        PortExpectedArrivalsService portExpectedArrivalsService = new PortExpectedArrivalsService();

        portExpectedArrivalsService.getExpectedArrivalsOnPort();

    }
}
