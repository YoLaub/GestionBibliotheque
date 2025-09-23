package yl.greta.model;

import yl.greta.helper.Util;

import java.io.*;
import java.util.*;

public class Emprunts<ID, K, V> implements Serializable {
    private static final long serialVersionUID = 1L;
    private ID clientId;
    private Map<K, V> listeEmprunt = new HashMap<>();

    public Emprunts(ID clientId) { this.clientId = clientId; }

    public void ajouter(K key, V value) { listeEmprunt.put(key, value); }

    public Map<K, V> getAll() {
        return Collections.unmodifiableMap(listeEmprunt); // Empêche les modifications externes
    }

    public void sauvegardeEmprent() {
        // Création du répertoire "binaire"
        File rep = new File("backup");
        rep.mkdir(); // Si le repertoire existe déjà cette méthode ne fait rien
        String backup = rep + File.separator + "emprunt_"+clientId+".bin"; // nom du fichier binaire
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(backup))) {
                oos.writeObject(this);
                System.out.println("Emprunt sauvegardé pour le client :" + clientId );
                Util.log.info("Emprunt effectuépour le client :" + clientId);

        } catch (IOException e) {
            e.printStackTrace();
            Util.log.warn("Erreur lors de l'enregistrement");
        }
    }

    public Emprunts<ID, K, V> chargerEmprunt() {
        File rep = new File("backup");
        String backupPath = rep + File.separator + "emprunt_" + clientId + ".bin";

        if (!new File(backupPath).exists()) {
            Util.log.warn("Aucun fichier de sauvegarde trouvé pour le client " + clientId);
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupPath))) {
            Emprunts<ID, K, V> emprunts = (Emprunts<ID, K, V>) ois.readObject();
            Util.log.info("Chargement réussi pour le client " + clientId);

            // Affiche les emprunts chargés
            System.out.println("Emprunts chargés pour le client " + clientId + ":");
            emprunts.getAll().forEach((key, value) ->
                    System.out.println("- " + key + ": " + value)
            );

            return emprunts;
        } catch (IOException | ClassNotFoundException e) {
            Util.log.error("Erreur lors du chargement pour le client " + clientId, e);
            return null;
        }
    }

}
