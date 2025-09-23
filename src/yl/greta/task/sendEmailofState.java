package yl.greta.task;

import yl.greta.helper.EmailBrevo;
import yl.greta.model.Bibliotheque;

public class sendEmailofState extends Thread{

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
