package yl.greta.task;

import yl.greta.model.Bibliotheque;

public class sendFTPThread extends Thread{

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
