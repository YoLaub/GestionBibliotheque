/**
 * La classe {@code FTPUploader} implémente l'interface {@link Fichier} et permet
 * d'envoyer des fichiers ou dossiers vers un serveur FTP en utilisant la bibliothèque
 * <a href="https://commons.apache.org/proper/commons-net/">Apache Commons Net</a>.
 * <p>
 * Elle gère :
 * <ul>
 *   <li>La connexion au serveur FTP avec authentification</li>
 *   <li>Le transfert récursif de dossiers locaux vers le serveur</li>
 *   <li>La création automatique des répertoires distants si nécessaire</li>
 * </ul>
 * </p>
 *
 * <p><b>Configuration requise :</b>
 * Un fichier JSON valide (ex: {@code conf/conf.json}) doit contenir les paramètres FTP :
 * {@code server}, {@code port}, {@code user}, et {@code pass}.</p>
 *
 * <p><b>Exemple d'utilisation :</b></p>
 * <pre>
 * FTPUploader uploader = new FTPUploader();
 * uploader.sendFile();  // Envoie le dossier "backup" vers le serveur FTP
 * </pre>
 *
 * @author [Votre Nom]
 * @version 1.0
 * @since 2025-09-24
 * @see Fichier
 * @see org.apache.commons.net.ftp.FTPClient
 */

package yl.greta.file;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import yl.greta.helper.AppConfig;
import yl.greta.helper.ConfigManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPUploader  implements Fichier{

    /**
     * Envoie le dossier local {@code backup/} et son contenu vers le serveur FTP configuré.
     * <p>
     * Les paramètres de connexion (serveur, port, identifiants) sont chargés depuis
     * le fichier de configuration spécifié ({@code conf/conf.json} par défaut).
     * </p>
     *
     * @throws IOException Si une erreur survient pendant la connexion, l'authentification
     *                     ou le transfert des fichiers.
     * @throws IllegalStateException Si la configuration FTP est invalide (ex: champs manquants).
     */
    @Override
    public void sendFile() throws IOException {
        AppConfig config = ConfigManager.loadConfig("conf/conf.json");

        String server = config.getServer();   // Adresse du serveur FTP
        int port = config.getPort();                       // Port par défaut
        String user = config.getUser();      // Identifiant FTP
        String pass = config.getPass();       // Mot de passe FTP

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            // Dossier local à envoyer
            File dossierLocal = new File("backup");
            // Répertoire cible sur le serveur
            String dossierServeur = "http://yoann.laubert.greta-bretagne-sud.org/ylaubert/backup";

            uploadDirectory(ftpClient, dossierServeur, dossierLocal);

            ftpClient.logout();
            ftpClient.disconnect();

            System.out.println("Transfert du dossier terminé !");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * Transfère récursivement un dossier local et son contenu vers le serveur FTP.
     * <p>
     * Crée les répertoires distants si ils n'existent pas et envoie chaque fichier.
     * </p>
     *
     * @param ftpClient      Le client FTP déjà connecté et authentifié.
     * @param cheminServeur  Chemin du répertoire <b>cible</b> sur le serveur (ex: {@code "backup/"}).
     * @param dossierLocal   Le dossier <b>source</b> à envoyer (objet {@link File}).
     * @throws IOException Si le transfert échoue (ex: permission refusée, fichier introuvable).
     */
    private static void uploadDirectory(FTPClient ftpClient, String cheminServeur, File dossierLocal) throws IOException {
        // Crée le répertoire distant s’il n’existe pas
        if (!ftpClient.changeWorkingDirectory(cheminServeur)) {
            ftpClient.makeDirectory(cheminServeur);
        }
        ftpClient.changeWorkingDirectory(cheminServeur);

        File[] fichiers = dossierLocal.listFiles();
        if (fichiers != null) {
            for (File fichier : fichiers) {
                if (fichier.isFile()) {
                    try (FileInputStream inputStream = new FileInputStream(fichier)) {
                        boolean done = ftpClient.storeFile(fichier.getName(), inputStream);
                        if (done) {
                            System.out.println("Fichier envoyé : " + fichier.getAbsolutePath());
                        } else {
                            System.out.println("Échec envoi : " + fichier.getAbsolutePath());
                        }
                    }
                } else if (fichier.isDirectory()) {
                    // Appel récursif pour sous-dossier
                    uploadDirectory(ftpClient, cheminServeur + "/" + fichier.getName(), fichier);
                }
            }
        }

        // Retour au dossier parent sur le serveur
        ftpClient.changeToParentDirectory();
    }

    /**
     * <b>Non implémenté.</b> Cette méthode est requise par l'interface {@link Fichier}
     * mais n'est pas utilisée pour le transfert FTP.
     *
     * @throws IOException Toujours levé (méthode non supportée).
     */
    @Override
    public void readFile() throws IOException {

    }

    /**
     * <b>Non implémenté.</b> Cette méthode est requise par l'interface {@link Fichier}
     * mais n'est pas utilisée pour le transfert FTP.
     *
     * @throws IOException Toujours levé (méthode non supportée).
     */
    @Override
    public void writeFile() throws IOException, ClassNotFoundException {

    }


}
