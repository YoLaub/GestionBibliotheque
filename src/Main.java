import yl.greta.helper.Auth;
import yl.greta.helper.EmailBrevo;
import yl.greta.helper.Util;
import yl.greta.model.Bibliotheque;
import yl.greta.model.Client;
import yl.greta.model.Emprunts;
import yl.greta.model.Livre;
import yl.greta.task.SaveToPdfThread;
import yl.greta.task.SavingBinaryTask;
import yl.greta.task.sendEmailofState;
import yl.greta.task.sendFTPThread;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        Util.log.info("Démarrage du projet");
        try {
            Auth.menuAuth();
        } catch (IOException e) {
            System.out.println("erreur" + e);
        }

        Bibliotheque stock = new Bibliotheque();

        stock.chargerSauvegarde("stock.bin");
        stock.chargerSauvegarde("client.bin");



//        Livre livre1 = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "Gallimard", LocalDate.parse("1943-04-06"), stock);
//        Livre livre2 = new Livre("1984", "George Orwell", "Secker & Warburg", LocalDate.parse("1949-06-08"), stock);
//        Livre livre3 = new Livre("L'Étranger", "Albert Camus", "Gallimard", LocalDate.parse("1942-05-19"), stock);
//        Livre livre4 = new Livre("Les Misérables", "Victor Hugo", "Pagnerre", LocalDate.parse("1862-04-03"), stock);
//        Livre livre5 = new Livre("Madame Bovary", "Gustave Flaubert", "Michel Lévy", LocalDate.parse("1857-12-12"), stock);
//        Livre livre6 = new Livre("Don Quichotte", "Miguel de Cervantes", "Francisco de Robles", LocalDate.parse("1605-01-16"), stock);
//        Livre livre7 = new Livre("Hamlet", "William Shakespeare", "N. Ling", LocalDate.parse("1603-07-26"), stock);
//        Livre livre8 = new Livre("La Peste", "Albert Camus", "Gallimard", LocalDate.parse("1947-06-10"), stock);
//        Livre livre9 = new Livre("Germinal", "Émile Zola", "Charpentier", LocalDate.parse("1885-03-15"), stock);
//        Livre livre10 = new Livre("Notre-Dame de Paris", "Victor Hugo", "Gosselin", LocalDate.parse("1831-03-16"), stock);
//        Livre livre11 = new Livre("Candide", "Voltaire", "Cramer", LocalDate.parse("1759-01-15"), stock);
//        Livre livre12 = new Livre("Crime et Châtiment", "Fiodor Dostoïevski", "The Russian Messenger", LocalDate.parse("1866-11-01"), stock);
//        Livre livre13 = new Livre("Anna Karénine", "Léon Tolstoï", "The Russian Messenger", LocalDate.parse("1877-04-01"), stock);
//        Livre livre14 = new Livre("Orgueil et Préjugés", "Jane Austen", "T. Egerton", LocalDate.parse("1813-01-28"), stock);
//        Livre livre15 = new Livre("Moby Dick", "Herman Melville", "Harper & Brothers", LocalDate.parse("1851-11-14"), stock);
//        Livre livre16 = new Livre("Le Rouge et le Noir", "Stendhal", "Levavasseur", LocalDate.parse("1830-11-13"), stock);
//        Livre livre17 = new Livre("La Chartreuse de Parme", "Stendhal", "Levavasseur", LocalDate.parse("1839-03-01"), stock);
//        Livre livre18 = new Livre("Les Fleurs du mal", "Charles Baudelaire", "Poulet-Malassis", LocalDate.parse("1857-06-25"), stock);
//        Livre livre19 = new Livre("Voyage au centre de la Terre", "Jules Verne", "Hetzel", LocalDate.parse("1864-11-25"), stock);
//        Livre livre20 = new Livre("Vingt mille lieues sous les mers", "Jules Verne", "Hetzel", LocalDate.parse("1870-06-20"), stock);

//        Client client1 = new Client("Dupont", "Jean", "000001",stock);
//        Client client2 = new Client("Martin", "Claire", "000002",stock);
//        Client client3 = new Client("Durand", "Luc", "000003",stock);
//        Client client4 = new Client("Bernard", "Sophie", "000004",stock);
//        Client client5 = new Client("Petit", "Paul", "000005",stock);
//        Client client6 = new Client("Robert", "Julie", "000006",stock);
//        Client client7 = new Client("Richard", "Antoine", "000007",stock);
//        Client client8 = new Client("Moreau", "Emma", "000008",stock);
//        Client client9 = new Client("Simon", "Léa", "000009",stock);
//        Client client10 = new Client("Laurent", "Hugo", "000010",stock);

        System.out.println("--------------Trie Par Titre------------");
        stock.getStockLivre().sort(Comparator.comparing(Livre::getTitre));
        Util.log.info("Trie par titre effectué");
        System.out.println("--------------------------");
        stock.afficherContenuStock();
        System.out.println("--------------------------");
        System.out.println("---------Trie Par Date-----------");
        stock.getStockLivre().sort(Comparator.comparing(Livre::getDateSortie));
        Util.log.info("Trie par date effectué ");
        System.out.println("--------------------------");
        stock.afficherContenuStock();
        System.out.println("-------------------------");

//        Thread save =new Thread(new SavingBinaryTask("stock.bin"));
//        save.start();

//        Thread savePdf = new Thread( new SaveToPdfThread());
//        savePdf.start();

//        Thread sendEmail = new Thread( new sendEmailofState());
//        sendEmail.start();

//        Thread saveClient =new Thread(new SavingBinaryTask("client.bin"));
//        saveClient.start();

        stock.afficherListClient();

//        Instant timestamp = Instant.now();
//        long millisNow = timestamp.toEpochMilli();
//
//        Emprunts emprunts = new Emprunts("000002");
//        emprunts.ajouter(millisNow,stock.rechercherLivreParTitre("Orgueil et Préjugés"));
//        emprunts.sauvegardeEmprent();
//
//        Emprunts emprunts2 = new Emprunts("000004");
//        emprunts2.ajouter(millisNow,stock.rechercherLivreParTitre("Anna Karénine"));
//        emprunts2.sauvegardeEmprent();
//
//        Emprunts emprunts3 = new Emprunts("000001");
//        emprunts3.ajouter(millisNow,stock.rechercherLivreParTitre("L'Étranger"));
//        emprunts3.sauvegardeEmprent();

        Emprunts<String, Long, Livre> emprunts = new Emprunts<>("000002");
        Emprunts<String, Long, Livre> empruntsCharges = emprunts.chargerEmprunt();
        if (empruntsCharges != null) {
            System.out.println("=== Emprunts pour le client 000002 ===");
            empruntsCharges.getAll().forEach((timestamp, livre) -> {
                System.out.println("Emprunté le " + new Date((Long) timestamp) + ": " + livre);
            });
        }

        Emprunts<String, Long, Livre> emprunts3 = new Emprunts<>("000001");
        Emprunts<String, Long, Livre> empruntsCharges3 = emprunts3.chargerEmprunt();
        if (empruntsCharges3 != null) {
            System.out.println("=== Emprunts pour le client 000001 ===");
            empruntsCharges3.getAll().forEach((timestamp, livre) -> {
                System.out.println("Emprunté le " + new Date((Long) timestamp) + ": " + livre);
            });
        }

        Emprunts<String, Long, Livre> emprunts2 = new Emprunts<>("000004");
        Emprunts<String, Long, Livre> empruntsCharges2 = emprunts2.chargerEmprunt();
        if (empruntsCharges2 != null) {
            System.out.println("\n=== Emprunts pour le client 000004 ===");
            empruntsCharges2.getAll().forEach((timestamp, livre) -> {
                System.out.println("Emprunté le " + new Date((Long) timestamp) + ": " + livre);
            });
        }

        Thread ftpSend = new Thread(new sendFTPThread());
        ftpSend.start();
















    }
}
