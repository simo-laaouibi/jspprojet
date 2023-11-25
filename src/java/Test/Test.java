/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import beans.EmployeBean;
import entities.Employe;
import entities.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import service.ServiceEmploye;
import service.ServiceService;
import util.HibernateUtil;

/**
 *
 * @author SIMO LAAOUIBI
 */
public class Test {
    public static void main(String[] args) {
        //HibernateUtil.getSessionFactory();
       ServiceService ss = new ServiceService();
        //création des Services
//        ss.create(new Service("I", "Informatique"));
//        ss.create(new Service("A", "Arabe"));
//        ss.create(new Service("C", "Comptailité"));
//        //La liste des services
//        for (Service s : ss.getAll()) {
//            System.out.println("" + s.getCode());
//        }
        
        
        
        //creation des emlpoyes
        ServiceEmploye se=new ServiceEmploye();
//       
//          se.create(new Employe("amine", "oifik", new Date(),"photo1",ss.getById(1)));
//            se.create(new Employe("youness", "oifik", new Date(),"photo3",ss.getById(2)));
//              se.create(new Employe("m.reda", "oifik", new Date(),"photo4",ss.getById(3)));
//       
//              
//        List<Employe> e = ss.getById(1).getEmployes();
//        List<Service> services=ss.getAll();
//        
      

    }
    
   
  
}
