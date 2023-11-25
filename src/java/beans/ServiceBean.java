/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Employe;
import entities.Service;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.crypto.SealedObject;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import service.ServiceEmploye;
import service.ServiceService;
import sun.font.EAttribute;

/**
 *
 * @author SIMO LAAOUIBI
 */
@ManagedBean
public class ServiceBean {

 private Service service;
 private  ServiceService ss;
 private List<Service> services;
 private List<Employe> employes;
 private static ChartModel barModel;
 private Employe employe;
 private ServiceEmploye se;
    private Object serviceService;
  

    /**
     * Creates a new instance of ServiceBean
     */
    public ServiceBean() {
        service = new Service();
         ss = new ServiceService();
         employe = new Employe();
         se=new ServiceEmploye();
    }

    
    
    public void onCreateAction (){
        
      ss.create(service);
      service = new Service();
    
    }
     public void onDeleteAction() {
      ss.delete(service);
    }
      public void onCancel(RowEditEvent event) {
    }
        public void onEdit(RowEditEvent event) {
        service =  (Service) event.getObject();
        
        ss.update(service);
    }
           public List<Employe> getEmployes() {
        if (employes == null) {
            employes = service.getEmployes();
        }
        return employes;
    }
        
    public void load() {
        System.out.println(service.getLibelle());
        service = ss.getById(service.getId());
        getEmployes();
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public ServiceService getSs() {
        return ss;
    }

    public List<Service> getServices() {
        if(services == null)
            services = ss.getAll();
        return services;
    }

    public void setSs(ServiceService ss) {
        this.ss = ss;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

     public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries chartservices = new ChartSeries();
        chartservices.setLabel("Services");
        model.setAnimate(true);
        List<Service> list=ss.getAll();
         System.out.println(list.size());
        for(Service s : list) {
            System.out.println(s.getLibelle());
            int c=s.getEmployes().size();
            System.out.println(c);
            chartservices.set(s.getLibelle(), c);
            System.out.println(chartservices);
        }
        model.addSeries(chartservices);
      
        return model;
    }
       public ChartModel initBarModelm() {
        CartesianChartModel model = new CartesianChartModel();

        ChartSeries informatiqueSeries = new ChartSeries();
        informatiqueSeries.setLabel("Informatique");
        informatiqueSeries.set("Categorie 1", 10);
        informatiqueSeries.set("Categorie 2", 15);
        informatiqueSeries.set("Categorie 3", 8);

        model.addSeries(informatiqueSeries);

        return model;
    }

    public static ChartModel getBarModel() {
        return barModel;
    }

    public Employe getEmploye() {
        return employe;
    }

    public ServiceEmploye getSe() {
        return se;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public static void setBarModel(ChartModel barModel) {
        ServiceBean.barModel = barModel;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setSe(ServiceEmploye se) {
        this.se = se;
    }
    
     private TreeNode root;
     
    
    
    @PostConstruct
    public void init() {
        root = new DefaultTreeNode("Root", null);

        List<Service> services = ss.getAll();

        for (Service service : services) {
            TreeNode serviceNode = new DefaultTreeNode(service.getCode(), root);

            List<Employe> employees = se.getEmployeesByService(service);

            for (Employe employee : employees) {
                if (employee.getChef() == null) {
                    TreeNode chefNode = new DefaultTreeNode(employee.getNom()+" "+employee.getPrenom(), serviceNode);

                    List<Employe> managedEmployees =se.getManagedEmployees(employee);

                    for (Employe managedEmployee : managedEmployees) {
                        new DefaultTreeNode(managedEmployee.getNom()+" "+managedEmployee.getPrenom(), chefNode);
                    }
                }
            }
        }

    }
    public TreeNode getRoot() {
        return root;
    }
    
       
       
}

