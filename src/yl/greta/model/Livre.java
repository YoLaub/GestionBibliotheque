package yl.greta.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Livre implements Serializable, Comparable {
    private String titre;
    private  String auteur;
    private String editeur;
    private LocalDate dateSortie;

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public Livre(String titre, String auteur, String editeur, LocalDate dateSortie, Bibliotheque stock) {
        this.titre = titre;
        this.auteur = auteur;
        this.editeur = editeur;
        this.dateSortie = dateSortie;
        stock.ajouterLivre(this);
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public String getDateSortie() {
        return dateSortie.format(FORMATTER);
    }

    public void setDateSortie(LocalDate dateSortie) {
        this.dateSortie = dateSortie;
    }

    @Override
    public String toString() {
        return titre +  "->{ auteur=" + auteur + '\'' +
                ", editeur=" + editeur + '\'' +
                ", dateSortie=" + dateSortie +
                '}' + "\n";
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
