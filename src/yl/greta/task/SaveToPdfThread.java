package yl.greta.task;

import yl.greta.model.Bibliotheque;

public class SaveToPdfThread extends Thread{

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
