package yl.greta.model;

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
