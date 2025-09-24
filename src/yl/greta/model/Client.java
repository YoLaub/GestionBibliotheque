package yl.greta.model;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Représente un client d'une bibliothèque pouvant emprunter des livres.
 * <p>
 * Cette classe implémente {@link Serializable} pour la persistance des données
 * et {@link Comparator} pour comparer les clients. Chaque client est identifié
 * par un numéro à 6 chiffres (stocké sous forme de String pour préserver les zéros initiaux)
 * et peut effectuer plusieurs emprunts de livres.
 * </p>
 */
public class Client implements Serializable, Comparator {
    private String nom;
    private String prenom;
    private String identifiant; // 6 chiffres : stocker en String pour garder zéros devant
    private Map<Timestamp, Livre> emprunts = new HashMap<>();

    public Client(String nom, String prenom, String identifiant, Bibliotheque client) {
        this.nom = nom; this.prenom = prenom; this.identifiant = identifiant; client.ajouterClient(this);
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
        return   "\'" +identifiant + '\'' +
                "nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", emprunts=" + emprunts +
                '}'+ "\n";
    }


    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }
}

