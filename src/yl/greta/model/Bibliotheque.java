package yl.greta.model;

import yl.greta.file.PdfFile;
import yl.greta.helper.Util;

import java.io.*;
import java.util.*;


public class Bibliotheque {

    private static ArrayList<Livre> stockLivre = new ArrayList<>();
    private static ArrayList<Client> listClient = new ArrayList<>();
    private static Map<Integer,Livre> listEmprunt;

    public ArrayList<Livre> getStockLivre() {
        return stockLivre;
    }
    public ArrayList<Client> getListClient() {
        return listClient;
    }

    void ajouterLivre(Livre livre) {
        stockLivre.add(livre);
        Util.log.info("Livre ajouter ");
    }

    void ajouterClient(Client client) {
        listClient.add(client);
        Util.log.info("Client ajouter ");
    }

    public static void sauvegarde(String file) {
        DataOutputStream ecrire;
        // Création du répertoire "binaire"
        File rep = new File("backup");
        rep.mkdir(); // Si le repertoire existe déjà cette méthode ne fait rien
        String backup = rep + File.separator + file; // nom du fichier binaire
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backup))) {
           if(file == "stock.bin"){
               oos.writeObject(stockLivre); // sérialise toute la liste
               System.out.println("Fichier de stock sauvegardé");
               Util.log.info("Enregistremant de stock effectué");
           } else if (file == "client.bin") {
               oos.writeObject(listClient); // sérialise toute la liste
               System.out.println("Fichier client sauvegardé");
               Util.log.info("Enregistremant client effectué");

           }

        } catch (IOException e) {
            e.printStackTrace();
            Util.log.warn("Erreur lors de l'enregistrement");
        }
    }

    public static void chargerSauvegarde(String file) {

        // Création du répertoire "binaire"
        File rep = new File("backup");
        String backup = rep + File.separator + file; // nom du fichier binaire
        if (!new File(backup).exists()) {
            System.out.println("Aucun fichier de sauvegarde trouvé.");
            Util.log.warn("Aucun fichier de sauvegarde trouvé.");
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backup))) {

            if(file == "stock.bin"){
                // Lire l'objet et le caster en ArrayList<Livre>
                ArrayList<Livre> loadedStock = (ArrayList<Livre>) ois.readObject();
                stockLivre.clear(); // Effacer le stock actuel
                stockLivre.addAll(loadedStock); // Ajouter les livres chargés
                System.out.println("Stock chargé avec succès.");
                Util.log.info("Chargement du stock effectué.");

            } else if (file == "client.bin") {
                // Lire l'objet et le caster en ArrayList<Livre>
                ArrayList<Client> loadedStock = (ArrayList<Client>) ois.readObject();
                listClient.clear(); // Effacer le stock actuel
                listClient.addAll(loadedStock); // Ajouter les livres chargés
                System.out.println("Liste client chargé avec succès.");
                Util.log.info("Liste client chargé .");
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void afficherContenuStock() {
        System.out.println(this.getStockLivre());
    }

    public void afficherListClient() {
        System.out.println(this.getListClient());
    }

    public Client rechercherClientParId(String id) {

        for(Client client: listClient){
            if (client.getIdentifiant().equals(id)){
                return client;
            }
        }
        System.out.println("Ce client n'exite pas ! ");
        Util.log.warn("Client inexistant");
        return null;
    }

    public Livre rechercherLivreParTitre(String titre) {
       for(Livre livre: stockLivre){
           if (livre.getTitre().equals(titre)){
               return livre;
           }
       }
        return null;
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
