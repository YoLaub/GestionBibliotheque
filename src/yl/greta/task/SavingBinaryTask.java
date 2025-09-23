package yl.greta.task;

import yl.greta.model.Bibliotheque;

public class SavingBinaryTask extends Thread {


    public void run() {
        System.out.println("***********************");
        System.out.format(">>> DEBUT DE TACHE Sauvegarde Fichier BIN");
        System.out.println("***********************");

        try{
           Bibliotheque.sauvegarde();
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("***********************");
        System.out.format(">>> FIN DE TACHE");

    }


}
