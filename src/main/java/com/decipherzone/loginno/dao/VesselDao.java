package com.decipherzone.loginno.dao;

import com.decipherzone.loginno.model.Vessel;
import com.decipherzone.loginno.model.VesselRouteStatus;
import com.decipherzone.loginno.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.*;

/**
 * Created by decipher on 6/5/17.
 */
public final class VesselDao {

    private final SessionFactory sessionFactory;

    public VesselDao() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    /**
     * This method returns list of vessels
     *
     * @return : vesselList : list of vessel
     */
    public List<Vessel> getAllVessel() {
        List<Vessel> vesselList = new ArrayList<>();

        Session session = null;
        try {

            session = sessionFactory.openSession();

            vesselList = session.createQuery("from Vessel").list();

        } catch (Exception e) {
            System.out.println("Error while getting list of vessel in getAllVessel() : " + e);
        } finally {
            session.close();
        }

        return vesselList;
    }

    /**
     * This method add vessel route status
     *
     * @param vesselRouteStatus : object of vessel route status
     * @return addVesselMap : It contains status of operation and saved object if operation is successful
     */
    public Map<String, Object> addVesselRouteStatus(VesselRouteStatus vesselRouteStatus) {
        Map<String, Object> addVesselMap = new HashMap<>();
        addVesselMap.put("status", false);

        Session session = null;
        Transaction transaction = null;
        try {

            session = sessionFactory.openSession();
            transaction = session.getTransaction();

            transaction.begin();
            session.save(vesselRouteStatus);
            transaction.commit();

            addVesselMap.put("status", true);
            addVesselMap.put("vesselRouteStatus", vesselRouteStatus);
            System.out.println("Vessel route information has been saved");
        } catch (Exception e) {
            System.out.println("Error while adding vessel route information in addVesselRouteStatus() : " + e);
        } finally {
            session.close();
        }

        return addVesselMap;
    }

}
