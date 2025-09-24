package yl.greta.task;
import yl.greta.model.Bibliotheque;


/**
 * Thread dédié à l'envoi de l'état de la bibliothèque vers un serveur FTP.
 * <p>
 * Cette classe étend {@link Thread} pour effectuer le transfert FTP de manière asynchrone,
 * permettant à l'application principale de continuer son exécution sans attente.
 * </p>
 */
public class sendFTPThread extends Thread{


    /**
     * Méthode exécutée lors du démarrage du thread.
     * <p>
     * Affiche des messages de début et de fin de transfert, puis tente d'envoyer
     * l'état actuel de la bibliothèque vers un serveur FTP via la méthode
     * {@link Bibliotheque#sendStateFTP()}.
     * En cas d'erreur pendant le transfert, la stack trace est affichée dans la console.
     * </p>
     *
     * @see Bibliotheque#sendStateFTP()
     */
    @Override
    public void run() {
        System.out.println("***********************");
        System.out.format(">>> DEBUT DE TRANSFERT");
        System.out.println("***********************");

        try{
            Bibliotheque.sendStateFTP();

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("***********************");
        System.out.format(">>> FIN DE TACHE");

    }
}
