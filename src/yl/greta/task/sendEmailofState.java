package yl.greta.task;
import yl.greta.helper.EmailBrevo;

/**
 * Tâche exécutée dans un thread séparé pour envoyer un email d'état
 * via le service Brevo.
 * <p>
 * Cette classe étend {@link Thread} afin d'effectuer l'envoi d'email
 * de manière asynchrone sans bloquer l'exécution principale du programme.
 * </p>
 */
public class sendEmailofState extends Thread{

    /**
     * Méthode exécutée lors du démarrage du thread.
     * <p>
     * Affiche des messages de début et de fin de tâche, puis tente d'envoyer
     * un email via le service Brevo. En cas d'erreur, la stack trace est
     * affichée dans la console.
     * </p>
     *
     * @see EmailBrevo#sendEmail()
     */
    @Override
    public void run() {
        System.out.println("***********************");
        System.out.format(">>> DEBUT DE TACHE ENVOI MAIL");
        System.out.println("***********************");

        try{
            EmailBrevo.sendEmail();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("***********************");
        System.out.format(">>> FIN DE TACHE");

    }


}
