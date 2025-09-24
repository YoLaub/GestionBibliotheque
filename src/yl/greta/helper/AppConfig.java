/**
 * La classe {@code AppConfig} représente une configuration applicative,
 * généralement chargée depuis un fichier JSON (via Jackson).
 * <p>
 * Elle contient les paramètres nécessaires pour :
 * <ul>
 *   <li>L'authentification (login/mot de passe)</li>
 *   <li>La configuration d'un serveur (SMTP, API, etc.)</li>
 *   <li>Les informations d'expéditeur/destinataire (emails, etc.)</li>
 * </ul>
 * </p>
 *
 * <p><b>Exemple d'utilisation avec Jackson :</b></p>
 * <pre>
 * ObjectMapper mapper = new ObjectMapper();
 * AppConfig config = mapper.readValue(new File("config.json"), AppConfig.class);
 * </pre>
 *
 * @author [Yoann Laubert]
 * @version 1.0
 * @since 2025-09-24
 * @see com.fasterxml.jackson.annotation.JsonProperty
 */
package yl.greta.helper;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AppConfig {
    /**
     * Identifiant de connexion (ex: nom d'utilisateur pour un service).
     * <p><b>Format attendu :</b> Chaîne alphanumérique (ex: {@code "admin"}).</p>
     */
    private String login;

    /**
     * Mot de passe haché pour des raisons de sécurité.
     * <p><b>Note :</b> Toujours stocker les mots de passe sous forme hachée (ex: SHA-256, bcrypt).</p>
     */
    private String motDePasseHash;

    /**
     * Adresse email ou identifiant de l'expéditeur (ex: {@code "noreply@domaine.com"}).
     */
    private String sender;

    /**
     * Adresse email ou identifiant du destinataire (ex: {@code "client@domaine.com"}).
     */
    private String to;

    /**
     *
     * <p><b>Sécurité :</b> Ne jamais exposer cette clé en clair dans les logs.</p>
     */
    private String apiKey;

    /**
     * Modèle de message ou template (ex: chemin vers un fichier HTML ou texte brut).
     */
    private String template;

    /**
     * Adresse du serveur (ex: {@code "smtp.domaine.com"} pour un serveur SMTP).
     */
    private String server;

    /**
     * Port de connexion au serveur (ex: {@code 587} pour SMTP avec TLS).
     */
    private int port;

    /**
     * Mot de passe en clair pour la connexion au serveur.
     * <p><b>Avertissement :</b> Préférer {@link #motDePasseHash} pour le stockage.</p>
     */
    private String pass;

    /**
     * Nom d'utilisateur pour la connexion au serveur (alternative à {@link #login}).
     * <p><b>Usage :</b> Peut être redondant avec {@link #login} selon le contexte.</p>
     */
    private String user;

// --- Getters et Setters ---

    /**
     * Retourne l'identifiant de connexion.
     *
     * @return Le login sous forme de chaîne de caractères.
     */
    @JsonProperty("login")
    public String getLogin() {
        return login;
    }

    /**
     * Définit l'identifiant de connexion.
     *
     * @param login Le nouveau login (ne doit pas être {@code null}).
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Retourne le mot de passe haché.
     *
     * @return Le hash du mot de passe (ex: SHA-256).
     */
    @JsonProperty("motDePasseHash")
    public String getMotDePasseHash() {
        return motDePasseHash;
    }

    /**
     * Définit le mot de passe haché.
     *
     * @param motDePasseHash Le hash du mot de passe (doit être déjà haché).
     * @throws IllegalArgumentException Si le hash est {@code null} ou vide.
     */
    public void setMotDePasseHash(String motDePasseHash) {
        this.motDePasseHash = motDePasseHash;
    }


    /**
     * Retourne l'adresse du destinataire.
     *
     * @return Le destinataire (ex: email).
     */
    @JsonProperty("to")
    public String getTo() {
        return to;
    }


    /**
     * Définit l'adresse du destinataire.
     *
     * @param to Le destinataire (ex: {@code "client@domaine.com"}).
     */
    public void setTo(String to) {
        this.to = to;
    }


    /**
     * Retourne l'adresse de l'expéditeur.
     *
     * @return L'expéditeur (ex: email).
     */
    @JsonProperty("sender")
    public String getSender() {
        return sender;
    }

    /**
     * Définit l'adresse de l'expéditeur.
     *
     * @param sender L'expéditeur (ex: {@code "noreply@domaine.com"}).
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * Retourne la clé d'API.
     *
     * @return La clé d'API (ex: {@code "SG.123456789"}).
     */
    @JsonProperty("apiKey")
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Définit la clé d'API.
     *
     * @param apiKey La clé d'API (ne doit pas être {@code null}).
     * @throws IllegalArgumentException Si la clé est vide.
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }


    /**
     * Retourne le modèle de message/template.
     *
     * @return Le template (chemin ou contenu brut).
     */
    @JsonProperty("template")
    public String getTemplate() {
        return template;
    }


    /**
     * Définit le modèle de message/template.
     *
     * @param template Le template (ex: chemin vers un fichier).
     */
    public void setTemplate(String template) {
        this.template = template;
    }

    /**
     * Retourne l'adresse du serveur.
     *
     * @return L'URL ou l'adresse IP du serveur.
     */
    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    /**
     * Définit l'adresse du serveur.
     *
     * @param server L'URL ou l'adresse IP (ex: {@code "smtp.domaine.com"}).
     */
    public void setServer(String server) {
        this.server = server;
    }

    /**
     * Retourne le port de connexion.
     *
     * @return Le numéro de port (ex: {@code 587}).
     */
    @JsonProperty("port")
    public int getPort() {
        return port;
    }

    /**
     * Définit le port de connexion.
     *
     * @param port Le numéro de port (doit être valide, ex: {@code 25}, {@code 465}).
     * @throws IllegalArgumentException Si le port est invalide (&lt; 1 ou &gt; 65535).
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * Retourne le mot de passe en clair (à éviter en production).
     *
     * @return Le mot de passe (non haché).
     * @deprecated Utilisez {@link #getMotDePasseHash()} à la place pour des raisons de sécurité.
     */
    @JsonProperty("pass")
    public String getPass() {
        return pass;
    }

    /**
     * Définit le mot de passe en clair.
     *
     * @param pass Le mot de passe (non recommandé, préférer le hash).
     * @deprecated Utilisez {@link #setMotDePasseHash(String)} à la place.
     */
    public void setPass(String pass) {
        this.pass = pass;
    }

    /**
     * Retourne le nom d'utilisateur pour le serveur.
     *
     * @return L'utilisateur (ex: {@code "user123"}).
     */
    @JsonProperty("user")
    public String getUser() {
        return user;
    }

    /**
     * Définit le nom d'utilisateur pour le serveur.
     *
     * @param user Le nom d'utilisateur (ex: {@code "admin"}).
     */
    public void setUser(String user) {
        this.user = user;
    }
}

