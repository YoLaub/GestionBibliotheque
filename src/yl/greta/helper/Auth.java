package yl.greta.helper;

import org.mindrot.jbcrypt.BCrypt;
import java.io.IOException;
import java.util.Scanner;

/**
 * Gère l'authentification des utilisateurs via un système de login/mot de passe sécurisé.
 * <p>
 * Cette classe utilise l'algorithme BCrypt pour le hachage et la vérification des mots de passe,
 * offrant une protection contre les attaques par force brute grâce à son mécanisme de salage.
 * Les identifiants de référence sont chargés depuis un fichier de configuration.
 * </p>
 * <p>
 * <strong>Fichier de configuration requis:</strong>
 * Le fichier "conf/conf.json" doit contenir les propriétés suivantes:
 * <ul>
 *   <li>login: Identifiant de référence</li>
 *   <li>motDePasseHash: Mot de passe déjà haché avec BCrypt</li>
 * </ul>
 * </p>
 * <p>
 * <strong>Fonctionnement:</strong>
 * <ol>
 *   <li>Charge la configuration depuis conf/conf.json</li>
 *   <li>Demande à l'utilisateur son login et mot de passe</li>
 *   <li>Vérifie les informations contre les valeurs de référence</li>
 *   <li>Journalise le résultat de l'authentification</li>
 * </ol>
 * </p>
 */
public class Auth {

    /**
     * Affiche un menu d'authentification et vérifie les identifiants saisis.
     * <p>
     * Cette méthode:
     * <ul>
     *   <li>Demande à l'utilisateur son login et mot de passe via la console</li>
     *   <li>Hache le mot de passe saisi pour comparaison sécurisée</li>
     *   <li>Compare les informations avec celles du fichier de configuration</li>
     *   <li>Journalise le résultat (succès/échec)</li>
     * </ul>
     * </p>
     *
     * @return {@code true} dans tous les cas (même en cas d'échec d'authentification)
     *         <strong>Note:</strong> Le comportement actuel retourne toujours true.
     *         Pour une implémentation correcte, devrait retourner false en cas d'échec.
     *
     * @throws IOException Si le fichier de configuration ne peut pas être chargé
     *
     * @see ConfigManager#loadConfig(String) Pour le chargement de la configuration
     * @see BCrypt Pour les opérations de hachage et vérification des mots de passe
     */
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
