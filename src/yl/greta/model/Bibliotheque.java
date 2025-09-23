package yl.greta.model;

import yl.greta.file.PdfFile;
import yl.greta.helper.Util;

import java.io.*;
import java.util.*;


public class Bibliotheque {

    private static ArrayList<Livre> stockLivre = new ArrayList<>();
    private static ArrayList<Livre> listClient = new ArrayList<>();
    private static Map<Integer,Livre> listEmprunt;

    public ArrayList<Livre> getStockLivre() {
        return stockLivre;
    }

    void ajouterLivre(Livre livre) {
        stockLivre.add(livre);
        Util.log.info("Livre ajouter ");


    }

    public static void sauvegarde() {
        DataOutputStream ecrire;
        // Création du répertoire "binaire"
        File rep = new File("backup");
        rep.mkdir(); // Si le repertoire existe déjà cette méthode ne fait rien
        String backup = rep + File.separator + "backup.bin"; // nom du fichier binaire
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backup))) {
            oos.writeObject(stockLivre); // sérialise toute la liste
            System.out.println("Stock sauvegardé");
            Util.log.info("Enregistremant effectué");
        } catch (IOException e) {
            e.printStackTrace();
            Util.log.warn("Erreur lors de l'enregistrement");
        }
    }

    public static void chargerSauvegarde() {

        // Création du répertoire "binaire"
        File rep = new File("backup");
        String backup = rep + File.separator + "backup.bin"; // nom du fichier binaire
        if (!new File(backup).exists()) {
            System.out.println("Aucun fichier de sauvegarde trouvé.");
            Util.log.warn("Aucun fichier de sauvegarde trouvé.");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backup))) {
            // Lire l'objet et le caster en ArrayList<Livre>
            ArrayList<Livre> loadedStock = (ArrayList<Livre>) ois.readObject();
            stockLivre.clear(); // Effacer le stock actuel
            stockLivre.addAll(loadedStock); // Ajouter les livres chargés
            System.out.println("Stock chargé avec succès.");
            Util.log.info("Chargement du stock effectué.");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherContenuStock() {
        System.out.println(this.getStockLivre());
    }

    void rechercherLivreParTitre(String titre) {
       for(Livre livre: stockLivre){
           if (livre.getTitre() == titre){
               System.out.println(livre);
           }else{
               System.out.println("Ce livre n'exite pas ! ");
           }

       }
    }

    public static void saveStateToPdf(){
        PdfFile pdf = new PdfFile("state_of_the_day.pdf");
        try{
            pdf.writeFile();
            Util.log.info("Fichier PDF enregistré");
        } catch (IOException e) {
            Util.log.warn("Erreur :" + e);
        } catch (ClassNotFoundException e) {
            Util.log.fatal("Erreur :" + e);
        }

    }




}
