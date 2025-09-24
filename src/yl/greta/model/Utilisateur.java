package yl.greta.model;

/**
 * Représente un utilisateur du système avec ses informations d'authentification.
 * <p>
 * Cette classe stocke les informations essentielles d'un utilisateur,
 * notamment son identifiant (login) et son mot de passe haché pour des raisons de sécurité.
 * </p>
 */
public class Utilisateur {

    private String login;
    private String motDePasseHash;

    public Utilisateur() {
    }

    public Utilisateur(String login, String motDePasseHash) {
        this.login = login;
        this.motDePasseHash = motDePasseHash;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMotDePasseHash() {
        return motDePasseHash;
    }

    public void setMotDePasseHash(String motDePasseHash) {
        this.motDePasseHash = motDePasseHash;
    }


}
