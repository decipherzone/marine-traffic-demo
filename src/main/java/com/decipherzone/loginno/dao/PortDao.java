package com.decipherzone.loginno.dao;

import com.decipherzone.loginno.model.Port;
import com.decipherzone.loginno.model.PortExpectedArrival;
import com.decipherzone.loginno.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by decipher on 8/5/17.
 */
public final class PortDao {

    private final SessionFactory sessionFactory;

    public PortDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * This method returns list of all ports
     *
     * @return portList : List of ports
     */
    public List<Port> getAllPorts() {
        List<Port> portList = new ArrayList<>();

        Session session = null;
        try {

            session = sessionFactory.openSession();

            portList = session.createQuery("from Port").list();

        } catch (Exception e) {
            System.out.println("Error while getting list of ports in getAllPorts() : " + e);
        } finally {
            if(session != null)
                session.close();
        }

        return portList;
    }

    /**
     * This method save port expected arrival list
     * @param portExpectedArrivalList : list of vessel of expected arrivals
     * @return
     */
    public Map<String, Object> savePortExpectedArrivals(List<PortExpectedArrival> portExpectedArrivalList) {
        Map<String, Object> addArrivalMap = new HashMap<>();
        addArrivalMap.put("status", false);

        Session session = null;
        Transaction transaction = null;
        try {

            session = sessionFactory.openSession();
            transaction = session.getTransaction();

            transaction.begin();

            for (PortExpectedArrival portExpectedArrival : portExpectedArrivalList) {
                if(portExpectedArrival != null) {
                    session.save(portExpectedArrival);
                }
            }

            transaction.commit();

            addArrivalMap.put("status", true);
            addArrivalMap.put("portArrival", portExpectedArrivalList);
            System.out.println("Expected arrivals on port information has been saved");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error while adding expected arrivals on port information in savePortExpectedArrivals() : " + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return addArrivalMap;
    }
}
