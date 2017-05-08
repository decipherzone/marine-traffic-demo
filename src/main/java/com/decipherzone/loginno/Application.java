/**
 * Created on 5/5/17 3:30 PM by Raja Dushyant Vashishtha
 * Sr. Software Engineer
 * rajad@decipherzone.com
 * Decipher Zone Softwares
 * www.decipherzone.com
 */

package com.decipherzone.loginno;

import com.decipherzone.loginno.model.Vessel;
import com.decipherzone.loginno.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public final class Application {

    private final static String API_KEY = "e7727f4288a97a4d010e46ea309526fb90d0e87b";
    private final static String API_ENDPOINT = "http://services.marinetraffic.com/api";

    public final static String getAPIUrl() {
        return API_ENDPOINT + "/";
    }

    public static void main(String ... arg) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();

//            session.save(new Vessel("413788101"))


            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }


        HibernateUtil.shutdown();
    }

}
