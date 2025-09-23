package yl.greta.task;

import yl.greta.model.Bibliotheque;

public class SavingBinaryTask extends Thread {

    private final String filename;

    public SavingBinaryTask(String filename){
        this.filename =filename;
    }

    public void run() {
        System.out.println("***********************");
        System.out.format(">>> DEBUT DE TACHE Sauvegarde Fichier BIN");
        System.out.println("***********************");

        try{
           Bibliotheque.sauvegarde(filename);
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("***********************");
        System.out.format(">>> FIN DE TACHE");

    }


}
