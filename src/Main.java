import yl.greta.helper.Auth;
import yl.greta.helper.EmailBrevo;
import yl.greta.helper.Util;
import yl.greta.model.Bibliotheque;
import yl.greta.model.Client;
import yl.greta.model.Livre;
import yl.greta.task.SaveToPdfThread;
import yl.greta.task.SavingBinaryTask;
import yl.greta.task.sendEmailofState;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
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

        stock.chargerSauvegarde();

        Livre livre1 = new Livre("Le Petit Prince", "Antoine de Saint-Exupéry", "Gallimard", LocalDate.parse("1943-04-06"), stock);
        Livre livre2 = new Livre("1984", "George Orwell", "Secker & Warburg", LocalDate.parse("1949-06-08"), stock);
        Livre livre3 = new Livre("L'Étranger", "Albert Camus", "Gallimard", LocalDate.parse("1942-05-19"), stock);
        Livre livre4 = new Livre("Les Misérables", "Victor Hugo", "Pagnerre", LocalDate.parse("1862-04-03"), stock);
        Livre livre5 = new Livre("Madame Bovary", "Gustave Flaubert", "Michel Lévy", LocalDate.parse("1857-12-12"), stock);
        Livre livre6 = new Livre("Don Quichotte", "Miguel de Cervantes", "Francisco de Robles", LocalDate.parse("1605-01-16"), stock);
        Livre livre7 = new Livre("Hamlet", "William Shakespeare", "N. Ling", LocalDate.parse("1603-07-26"), stock);
        Livre livre8 = new Livre("La Peste", "Albert Camus", "Gallimard", LocalDate.parse("1947-06-10"), stock);
        Livre livre9 = new Livre("Germinal", "Émile Zola", "Charpentier", LocalDate.parse("1885-03-15"), stock);
        Livre livre10 = new Livre("Notre-Dame de Paris", "Victor Hugo", "Gosselin", LocalDate.parse("1831-03-16"), stock);
        Livre livre11 = new Livre("Candide", "Voltaire", "Cramer", LocalDate.parse("1759-01-15"), stock);
        Livre livre12 = new Livre("Crime et Châtiment", "Fiodor Dostoïevski", "The Russian Messenger", LocalDate.parse("1866-11-01"), stock);
        Livre livre13 = new Livre("Anna Karénine", "Léon Tolstoï", "The Russian Messenger", LocalDate.parse("1877-04-01"), stock);
        Livre livre14 = new Livre("Orgueil et Préjugés", "Jane Austen", "T. Egerton", LocalDate.parse("1813-01-28"), stock);
        Livre livre15 = new Livre("Moby Dick", "Herman Melville", "Harper & Brothers", LocalDate.parse("1851-11-14"), stock);
        Livre livre16 = new Livre("Le Rouge et le Noir", "Stendhal", "Levavasseur", LocalDate.parse("1830-11-13"), stock);
        Livre livre17 = new Livre("La Chartreuse de Parme", "Stendhal", "Levavasseur", LocalDate.parse("1839-03-01"), stock);
        Livre livre18 = new Livre("Les Fleurs du mal", "Charles Baudelaire", "Poulet-Malassis", LocalDate.parse("1857-06-25"), stock);
        Livre livre19 = new Livre("Voyage au centre de la Terre", "Jules Verne", "Hetzel", LocalDate.parse("1864-11-25"), stock);
        Livre livre20 = new Livre("Vingt mille lieues sous les mers", "Jules Verne", "Hetzel", LocalDate.parse("1870-06-20"), stock);

        List<Client> clients = new ArrayList<>();

        clients.add(new Client("Dupont", "Jean", "000001"));
        clients.add(new Client("Martin", "Claire", "000002"));
        clients.add(new Client("Durand", "Luc", "000003"));
        clients.add(new Client("Bernard", "Sophie", "000004"));
        clients.add(new Client("Petit", "Paul", "000005"));
        clients.add(new Client("Robert", "Julie", "000006"));
        clients.add(new Client("Richard", "Antoine", "000007"));
        clients.add(new Client("Moreau", "Emma", "000008"));
        clients.add(new Client("Simon", "Léa", "000009"));
        clients.add(new Client("Laurent", "Hugo", "000010"));

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

        Thread save =new Thread(new SavingBinaryTask());
        save.start();

        Thread savePdf = new Thread( new SaveToPdfThread());
        savePdf.start();

        Thread sendEmail = new Thread( new sendEmailofState());
        sendEmail.start();










    }
}
