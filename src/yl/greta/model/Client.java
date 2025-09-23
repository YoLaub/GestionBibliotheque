package yl.greta.model;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Client {
    private String nom;
    private String prenom;
    private String identifiant; // 6 chiffres : stocker en String pour garder zéros devant
    private Map<Timestamp, Livre> emprunts = new HashMap<>();

    public Client(String nom, String prenom, String identifiant) {
        this.nom = nom; this.prenom = prenom; this.identifiant = identifiant;
    }

    public void emprunter(Timestamp date, Livre livre) {
        emprunts.put(date, livre);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public Map<Timestamp, Livre> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(Map<Timestamp, Livre> emprunts) {
        this.emprunts = emprunts;
    }



    @Override
    public String toString() {
        return "Client{" +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", identifiant='" + identifiant + '\'' +
                ", emprunts=" + emprunts +
                '}';
    }

}

