/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.Employe;
import entities.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import service.ServiceEmploye;
import service.ServiceService;

/**
 *
 * @author SIMO LAAOUIBI
 */
@ManagedBean(name = "employeBean")
public class EmployeBean {
    private Employe employe;
    private ServiceEmploye se;
    private Service service;
    private List<Employe> employes;
    private List<Service> services;
    private ServiceService ss;
    private Employe chef;
    private List<Employe> chefs;
    private List<Employe>  employesBetweenDates ;
    private Employe selectedChef;
    private Date d1;
    private Date d2;
    private static ChartModel barModel;

    public EmployeBean() {
        employe = new Employe();
        se = new ServiceEmploye();
        chef = new Employe();
        ss = new ServiceService();
        selectedChef = new Employe();
    }
    

       public void onEdit(RowEditEvent event) {
           
        employe = (Employe) event.getObject();
        Service service = ss.getById(this.employe.getService().getId());
        employe.setService(service);
        employe.getService().setLibelle(service.getLibelle());
        employe.setChef(selectedChef);
        se.update(employe);
        
    }
          public void onCancel(RowEditEvent event) {
    }
        public void onCreateAction() {
            employe.setChef(selectedChef);
        se.create(employe);
        service = new Service();
        
    
    }

    public void onDeleteAction() {
        
         Employe e = se.getById(employe.getId());
         System.out.println(e);
         se.delete(e);
      
    }
    
      public List<Employe> employeLoad() {
        employesBetweenDates = se.getByDates(d1, d2);
        return null;

    }

    public List<Service> getServices() {
        if(services== null)
            services = ss.getAll();
        return services;
    }
    
    
    
    
    
    public Employe getEmploye() {
        return employe;
    }

    public ServiceEmploye getSe() {
        return se;
    }

    public Service getService() {
        return service;
    }

    public List<Employe> getEmployes() {
            if (employes == null) {
            employes = se.getAll();
        }
        return employes;
    }

    public ServiceService getSs() {
        return ss;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public void setSe(ServiceEmploye se) {
        this.se = se;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public void setSs(ServiceService ss) {
        this.ss = ss;
    }

    public List<Employe> getChefs() {
        
       if (chefs == null ) {
            chefs= se.getAll();
        }
   chefs= chefs.stream()
            .filter(employe -> employe.getChef() != null && employe.getChef().equals(employe))
            .collect(Collectors.toList());
   return chefs;
    }
   

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public void setChefs(List<Employe> chefs) {
        this.chefs = chefs;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public Employe getSelectedChef() {
        return selectedChef;
    }

    public void setSelectedChef(Employe selectedChef) {
        this.selectedChef = selectedChef;
    }

    public List<Employe> getEmployesBetweenDates() {
        return employesBetweenDates;
    }

    public Date getD1() {
        return d1;
    }

    public Date getD2() {
        return d2;
    }

    public static ChartModel getBarModel() {
        return barModel;
    }

    public void setEmployesBetweenDates(List<Employe> employesBetweenDates) {
        this.employesBetweenDates = employesBetweenDates;
    }

    public void setD1(Date d1) {
        this.d1 = d1;
    }

    public void setD2(Date d2) {
        this.d2 = d2;
    }

    public static void setBarModel(ChartModel barModel) {
        EmployeBean.barModel = barModel;
    }
    
    
    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries employes = new ChartSeries();
        employes.setLabel("employes");
        model.setAnimate(true);
        for (Object[] m : se.nbEmployes()) {
            Employe ee=(Employe) m[1];
            employes.set(ee.getNom(), Integer.parseInt(m[0].toString()));
        }
        model.addSeries(employes);

        return model;
    }
    
    

    
}

