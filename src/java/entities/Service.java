/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import com.sun.faces.application.resource.LibraryInfo;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author SIMO LAAOUIBI
 */
@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String libelle;
    @OneToMany(mappedBy = "service",fetch = FetchType.EAGER)
    private List<Employe> employes;
//    
 

    public Service() {
    }

    
    
    public Service(String code, String libelle) {
        this.code = code;
        this.libelle = libelle;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString() {
        return "Service{" + "id=" + id + ", code=" + code + ", libelle=" + libelle + '}';
    }

    public List<Employe> getEmployes() {
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

  
    
    
    
    
    
}
