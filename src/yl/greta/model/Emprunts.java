/**
 * La classe {@code Emprunts} représente une collection d'emprunts associés à un client identifié.
 * Elle permet de gérer, sauvegarder et charger les emprunts sous forme sérialisée.
 * <p>
 * Cette classe est générique et peut être utilisée avec différents types :
 * <ul>
 *   <li>{@code ID} : Type de l'identifiant du client (ex: {@code String}, {@code Integer})</li>
 *   <li>{@code K} : Type de la clé des emprunts (ex: {@code String} pour un titre de livre)</li>
 *   <li>{@code V} : Type de la valeur des emprunts (ex: {@code Livre}, {@code Date})</li>
 * </ul>
 * </p>
 *
 * <p><b>Exemple d'utilisation :</b></p>
 * <pre>
 * Emprunts{@code <String, String, Livre>} emprunts = new Emprunts{@code <>("CLIENT123");
 * emprunts.ajouter("LIVRE001", new Livre("Titre du livre"));
 * emprunts.sauvegardeEmprent();
 * </pre>
 *
 * @param <ID> Type de l'identifiant du client
 * @param <K>  Type de la clé dans la map des emprunts
 * @param <V>  Type de la valeur dans la map des emprunts
 *
 * @author [Votre Nom]
 * @version 1.0
 * @since 2025-09-24
 * @see Serializable
 * @see java.util.HashMap
 */
package yl.greta.model;
import yl.greta.helper.Util;
import java.io.*;
import java.util.*;

public class Emprunts<ID, K, V> implements Serializable {
    /**
     * Version sérialisée de la classe pour assurer la compatibilité lors de la désérialisation.
     */
    private static final long serialVersionUID = 1L;
    /**
     * Identifiant unique du client associé à ces emprunts.
     */
    private ID clientId;
    /**
     * Collection des emprunts sous forme de map (clé -> valeur).
     * <p>
     * Utilise une {@link HashMap} pour un accès rapide aux emprunts.
     * </p>
     */
    private Map<K, V> listeEmprunt = new HashMap<>();

    /**
     * Constructeur pour créer une nouvelle collection d'emprunts pour un client.
     *
     * @param clientId L'identifiant du client (ne doit pas être {@code null})
     * @throws IllegalArgumentException Si l'identifiant du client est {@code null}
     */
    public Emprunts(ID clientId) { this.clientId = clientId; }

    /**
     * Ajoute un nouvel emprunt à la collection.
     *
     * @param key   La clé de l'emprunt (ex: identifiant du livre)
     * @param value La valeur associée (ex: objet Livre)
     * @throws NullPointerException Si la clé ou la valeur est {@code null}
     */
    public void ajouter(K key, V value) { listeEmprunt.put(key, value); }

    /**
     * Retourne une vue non modifiable de tous les emprunts.
     * <p>
     * Cette méthode empêche les modifications externes de la map interne
     * en retournant une vue {@link Collections#unmodifiableMap(Map) immuable}.
     * </p>
     *
     * @return Une map non modifiable contenant tous les emprunts
     */
    public Map<K, V> getAll() {
        return Collections.unmodifiableMap(listeEmprunt); // Empêche les modifications externes
    }

    /**
     * Sauvegarde l'état actuel des emprunts dans un fichier binaire.
     * <p>
     * Le fichier est stocké dans le répertoire {@code backup/} avec un nom basé sur
     * l'identifiant du client : {@code emprunt_[ID].bin}.
     * </p>
     *
     * @throws IOException Si une erreur survient lors de l'écriture du fichier
     * @see #chargerEmprunt()
     */
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

    /**
     * Charge les emprunts précédemment sauvegardés pour ce client.
     * <p>
     * Recherche un fichier nommé {@code emprunt_[ID].bin} dans le répertoire {@code backup/}.
     * Si le fichier n'existe pas, retourne {@code null}.
     * </p>
     *
     * @return Une instance de {@code Emprunts} contenant les données chargées,
     *         ou {@code null} si aucun fichier n'existe
     * @throws RuntimeException Si une erreur survient lors de la désérialisation
     * @see #sauvegardeEmprent()
     */
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
