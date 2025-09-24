package yl.greta.file;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import yl.greta.helper.AppConfig;
import yl.greta.helper.ConfigManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FTPUploader  implements Fichier{

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

    @Override
    public void readFile() throws IOException {

    }

    @Override
    public void writeFile() throws IOException, ClassNotFoundException {

    }


}
