package yl.greta.helper;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.Scanner;

public class Auth {

    public static boolean menuAuth() throws IOException{

        AppConfig config = ConfigManager.loadConfig("conf/conf.json");

        Scanner scan = new Scanner(System.in);

        System.out.println("Login: ");
        String login = scan.nextLine();

        System.out.println("Mot de passe: ");
        String motDePasse = scan.nextLine();
        String pwdHash = BCrypt.hashpw(motDePasse, BCrypt.gensalt());
        Util.log.info("pwd haché: " + pwdHash);
        boolean motDePasseOk = BCrypt.checkpw(motDePasse, config.getMotDePasseHash());

        if( login.equals(config.getLogin()) && motDePasseOk){
            Util.log.info("Connexion réussie, Bienvenue " + config.getLogin());
        }else {
            Util.log.warn("Mot de passe incorrect");
        }

        return true;
    }
}
