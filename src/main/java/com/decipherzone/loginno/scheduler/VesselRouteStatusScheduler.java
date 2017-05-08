package com.decipherzone.loginno.scheduler;

import com.decipherzone.loginno.scheduler.job.PortExpectedArrivalsJob;
import com.decipherzone.loginno.scheduler.job.VesselRouteStatusJob;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by decipher on 6/5/17.
 */
public class VesselRouteStatusScheduler {

    private static Scheduler scheduler = null;

    /**
     * This method starts scheduler for present port of vessel
     */
    public static void startVesselPortScheduler() {
        try {

            StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();

            scheduler = stdSchedulerFactory.getScheduler();

//            buildVesselPresentPortJob();
            buildPortExpectedArrivalsJob();

            scheduler.start();

        } catch (Exception e) {
            System.out.println("Error while starting scheduler for vessel port in startVesselPortScheduler() : " + e);
        }
    }

    /**
     * This method builds job for present port of vessel
     */
    private static void buildVesselPresentPortJob() {

        try {

            JobDetail vesselPresentPortJob = JobBuilder.newJob(VesselRouteStatusJob.class).withIdentity("VesselPresentPortJob").build();

            Trigger vesselPresentPortTrigger = newTrigger().startAt(new Date()).
                    withIdentity("VesselPresentPortTrigger", "VesselPresentPortGroup").
                    withSchedule(simpleSchedule()
                            .withIntervalInMinutes(15)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(vesselPresentPortJob, vesselPresentPortTrigger);

        } catch (Exception e) {
            System.out.println("Error while building job for vessel present port scheduler in buildVesselPresentPortJob() : " + e);
        }

    }

    /**
     * This method builds job for expected arrivals of a port
     */
    private static void buildPortExpectedArrivalsJob() {

        try {

            JobDetail portExpectedArrivalJob = JobBuilder.newJob(PortExpectedArrivalsJob.class).withIdentity("PortExpectedArrivalJob").build();

            Trigger portExpectedArrivalsTrigger = newTrigger().startAt(new Date()).
                    withIdentity("PortExpectedArrivalTrigger", "PortExpectedArrivalGroup").
                    withSchedule(simpleSchedule()
                            .withIntervalInMinutes(15)
                            .repeatForever())
                    .build();

            scheduler.scheduleJob(portExpectedArrivalJob, portExpectedArrivalsTrigger);

        } catch (Exception e) {
            System.out.println("Error while building job for expected port arrivals in buildPortExpectedArrivalsJob() : " + e);
        }

    }

    public static boolean stopScheduler() {
        try {

            if (scheduler != null) {
                scheduler.shutdown(true);
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Error while stopping quartz scheduler in stopServiceScoringEngineQuartzScheduler() : " + e);
            return false;
        }
    }

}