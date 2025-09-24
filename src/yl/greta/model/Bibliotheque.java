/**
 * La classe {@code Bibliotheque} gère le stock de livres, la liste des clients,
 * et les opérations de sauvegarde/chargement des données.
 * Elle permet également d'exporter l'état courant en PDF et de l'envoyer via FTP.
 *
 * @author Laubert
 * @version 1.0
 * @since 2025-09-24
 */
package yl.greta.model;
import yl.greta.file.FTPUploader;
import yl.greta.file.PdfFile;
import yl.greta.helper.Util;
import java.io.*;
import java.util.*;

public class Bibliotheque {

    /**
     * Liste statique des livres disponibles dans la bibliothèque.
     */
    private static ArrayList<Livre> stockLivre = new ArrayList<>();
    /**
     * Liste statique des clients inscrits dans la bibliothèque.
     */
    private static ArrayList<Client> listClient = new ArrayList<>();

    /**
     * Retourne la liste complète des livres en stock.
     *
     * @return {@code ArrayList<Livre>} contenant tous les livres disponibles.
     */
    public ArrayList<Livre> getStockLivre() {
        return stockLivre;
    }

    /**
     * Retourne la liste complète des clients inscrits.
     *
     * @return {@code ArrayList<Client>} contenant tous les clients.
     */
    public ArrayList<Client> getListClient() {
        return listClient;
    }


    /**
     * Ajoute un livre au stock de la bibliothèque et log l'opération.
     *
     * @param livre Le livre à ajouter (objet {@code Livre}).
     */
    void ajouterLivre(Livre livre) {
        stockLivre.add(livre);
        Util.log.info("Livre ajouter ");
    }


    /**
     * Ajoute un client à la liste des clients et log l'opération.
     *
     * @param client Le client à ajouter (objet {@code Client}).
     */
    void ajouterClient(Client client) {
        listClient.add(client);
        Util.log.info("Client ajouter ");
    }

    /**
     * Sauvegarde les données (livres ou clients) dans un fichier binaire.
     * <p>Les fichiers sont stockés dans le répertoire {@code backup/}.</p>
     *
     * @param file Nom du fichier de sauvegarde (ex: {@code "stock.bin"} ou {@code "client.bin"}).
     * @throws IOException Si une erreur d'écriture survient.
     */
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


    /**
     * Charge les données (livres ou clients) depuis un fichier binaire.
     * <p>Remplace le contenu actuel par les données chargées.</p>
     *
     * @param file Nom du fichier à charger (ex: {@code "stock.bin"} ou {@code "client.bin"}).
     * @throws IOException Si le fichier est introuvable ou corrompu.
     * @throws ClassNotFoundException Si la désérialisation échoue.
     */
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

    /**
     * Affiche le contenu du stock de livres dans la console.
     * <p>Utilise la méthode {@code toString()} de {@code ArrayList}.</p>
     */
    public void afficherContenuStock() {
        System.out.println(this.getStockLivre());
    }


    /**
     * Affiche la liste des clients dans la console.
     * <p>Utilise la méthode {@code toString()} de {@code ArrayList}.</p>
     */
    public void afficherListClient() {
        System.out.println(this.getListClient());
    }


    /**
     * Recherche un client par son identifiant.
     *
     * @param id L'identifiant du client (chaîne de caractères).
     * @return L'objet {@code Client} correspondant, ou {@code null} si introuvable.
     */
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


    /**
     * Recherche un livre par son titre.
     *
     * @param titre Le titre du livre (chaîne de caractères, sensible à la casse).
     * @return L'objet {@code Livre} correspondant, ou {@code null} si introuvable.
     */
    public Livre rechercherLivreParTitre(String titre) {
       for(Livre livre: stockLivre){
           if (livre.getTitre().equals(titre)){
               return livre;
           }
       }
        return null;
    }


    /**
     * Génère un PDF contenant l'état courant de la bibliothèque (stock et clients).
     * <p>Le fichier est nommé {@code "state_of_the_day.pdf"}.</p>
     *
     * @throws IOException Si l'écriture du fichier échoue.
     * @throws ClassNotFoundException Si la génération du contenu échoue.
     */
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


    /**
     * Envoie le fichier PDF de l'état courant via FTP.
     *
     * @throws IOException Si le transfert FTP échoue.
     */
    public static void sendStateFTP(){
        FTPUploader ftpSend = new FTPUploader();
        try{
            ftpSend.sendFile();
            Util.log.info("Fichiers transférés");
        } catch (IOException e) {
            Util.log.warn("Erreur :" + e);
        }

    }




}
