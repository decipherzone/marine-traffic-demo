/**
 * Created on 5/5/17 3:55 PM by Raja Dushyant Vashishtha
 * Sr. Software Engineer
 * rajad@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

package com.decipherzone.loginno.config;

import com.decipherzone.loginno.scheduler.VesselRouteStatusScheduler;
import com.decipherzone.loginno.util.HibernateUtil;
import org.hibernate.Session;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApplicationInitializer implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // Check database version
        String sql = "select version()";

        String result = (String) session.createNativeQuery(sql).getSingleResult();
        System.out.println("result : " + result);

        session.getTransaction().commit();
        session.close();

        VesselRouteStatusScheduler.startVesselPortScheduler();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        HibernateUtil.shutdown();

        VesselRouteStatusScheduler.stopScheduler();
    }
}
