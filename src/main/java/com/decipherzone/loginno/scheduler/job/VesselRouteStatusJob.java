package com.decipherzone.loginno.scheduler.job;

import com.decipherzone.loginno.service.VesselRouteStatusService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Created by decipher on 6/5/17.
 */
public class VesselRouteStatusJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        try {

            VesselRouteStatusService vesselPresentJobService = new VesselRouteStatusService();

            vesselPresentJobService.getPresentRouteStatusOfVessel();

        } catch (Exception e) {
            System.out.println("Exception while running VesselPresentPortJob in execute() : " + e);
        }


    }
}
