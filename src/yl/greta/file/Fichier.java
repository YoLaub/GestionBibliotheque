/**
 * L'interface {@code Fichier} définit un contrat pour les opérations de base
 * sur les fichiers, incluant la lecture, l'écriture et l'envoi de fichiers.
 * <p>
 * Cette interface est conçue pour être implémentée par des classes gérant
 * différents types de fichiers (PDF, sauvegardes binaires, transferts FTP, etc.).
 * Chaque méthode lève des exceptions spécifiques pour signaler les erreurs
 * d'entrée/sortie ou de format de données.
 * </p>
 *
 * <p><b>Utilisation typique :</b></p>
 * <pre>
 * class MonImplFichier implements Fichier {
 *     {@literal @}Override
 *     public void readFile() throws IOException {
 *         // Implémentation spécifique
 *     }
 *
 *     {@literal @}Override
 *     public void writeFile() throws IOException, ClassNotFoundException {
 *         // Implémentation spécifique
 *     }
 *
 *     {@literal @}Override
 *     public void sendFile() throws IOException {
 *         // Implémentation spécifique
 *     }
 * }
 * </pre>
 *
 * @author Yoann Laubert
 * @version 1.0
 * @since 2025-09-24
 * @see java.io.IOException
 * @see java.io.File
 */
package yl.greta.file;
import yl.greta.model.Livre;
import java.io.IOException;

public interface Fichier {

    /**
     * Lit le contenu d'un fichier.
     * <p>
     * Cette méthode doit être implémentée pour charger les données
     * depuis un fichier vers la mémoire ou un objet Java.
     * </p>
     *
     * @throws IOException Si une erreur survient pendant la lecture
     *                     (fichier introuvable, permissions insuffisantes, etc.).
     */
    public void readFile() throws IOException;

    /**
     * Écrit des données dans un fichier.
     * <p>
     * Cette méthode doit être implémentée pour sauvegarder les données
     * depuis la mémoire ou un objet Java vers un fichier.
     * </p>
     *
     * @throws IOException Si une erreur survient pendant l'écriture
     *                     (espace disque insuffisant, permissions, etc.).
     * @throws ClassNotFoundException Si la classe des objets à écrire
     *                                n'est pas trouvée (typiquement lors de la sérialisation).
     */
    public void writeFile() throws IOException, ClassNotFoundException;

    /**
     * Envoie un fichier vers une destination externe.
     * <p>
     * Cette méthode doit être implémentée pour gérer le transfert de fichiers
     * vers un serveur distant (FTP, HTTP, etc.) ou un autre système.
     * </p>
     *
     * @throws IOException Si une erreur survient pendant le transfert
     *                     (connexion échouée, timeout, etc.).
     */
    public void sendFile() throws IOException;


}
