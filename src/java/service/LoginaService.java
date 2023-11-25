/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.Login;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

/**
 * @author SIMO LAAOUIBI
 */
public class LoginaService {
    
    public boolean login(String user,String password){
        boolean etat=false;
        List<Login> result;
        Session session = null;
        try{
        session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        String hql="FROM Login WHERE user = :user AND password = :password";
        Query query = session.createQuery(hql);
        query.setParameter("user", user);
        query.setParameter("password", password);
        result=query.list();
        etat = !result.isEmpty();
        session.getTransaction().commit();
        }
        catch(Exception e){
            if (session != null && session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return etat;
        }
         
        
}