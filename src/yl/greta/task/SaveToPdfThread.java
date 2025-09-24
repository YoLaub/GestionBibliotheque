/**
 * Thread dédié à la sauvegarde de l'état de la bibliothèque au format PDF.
 * Cette classe étend {@link Thread} pour exécuter la sauvegarde de manière asynchrone,
 * permettant ainsi de ne pas bloquer le thread principal de l'application.
 *
 * <p>La tâche effectuée consiste à appeler la méthode statique
 * {@link Bibliotheque#saveStateToPdf()} et à gérer les éventuelles exceptions.</p>
 *
 * @see Bibliotheque
 * @see Thread
 */

package yl.greta.task;
import yl.greta.model.Bibliotheque;

public class SaveToPdfThread extends Thread{

    /**
     * Méthode exécutée lors du démarrage du thread.
     * Affiche des messages de début et de fin de tâche, puis tente de sauvegarder
     * l'état de la bibliothèque au format PDF via {@link Bibliotheque#saveStateToPdf()}.
     * Toute exception survenant pendant le processus est imprimée sur la sortie d'erreur standard.
     */

    public void run() {
        System.out.println("***********************");
        System.out.format(">>> DEBUT DE TACHE Sauvegarde PDF");
        System.out.println("***********************");

        try{
            Bibliotheque.saveStateToPdf();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("***********************");
        System.out.format(">>> FIN DE TACHE");

    }
}
