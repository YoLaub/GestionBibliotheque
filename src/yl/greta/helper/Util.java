/**
 * La classe utilitaire {@code Util} fournit des méthodes et outils communs
 * pour l'application, notamment :
 * <ul>
 *   <li>La gestion des dates (formatage, parsing)</li>
 *   <li>Un logger centralisé pour la journalisation</li>
 *   <li>Des méthodes utilitaires pour les modèles de données</li>
 * </ul>
 *
 * <p>Cette classe utilise <a href="https://logging.apache.org/log4j/2.x/">Log4j 2</a>
 * pour la gestion des logs et l'API {@link java.time} pour les opérations sur les dates.</p>
 *
 * <p><b>Exemple d'utilisation du logger :</b></p>
 * <pre>
 * Util.log.info("Début du traitement...");
 * Util.log.error("Erreur critique : {}", e.getMessage());
 * </pre>
 *
 * @author Yoann Laubert
 * @version 1.0
 * @since 2025-09-24
 * @see org.apache.logging.log4j.Logger
 * @see java.time.LocalDate
 * @see java.time.format.DateTimeFormatter
 */

package yl.greta.helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import yl.greta.model.Emprunts;
import yl.greta.model.Livre;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;


public class Util {

    /**
     * Instance de logger configurée pour cette classe.
     * <p>Ce logger peut être utilisé dans toute l'application pour :
     * <ul>
     *   <li>Enregistrer des messages d'information ({@code info})</li>
     *   <li>Journaliser les erreurs ({@code error})</li>
     *   <li>Déboguer ({@code debug})</li>
     *   <li>Suivre les avertissements ({@code warn})</li>
     * </ul>
     * </p>
     *
     * <p><b>Exemple :</b></p>
     * <pre>
     * Util.log.debug("Valeur de la variable : {}", variable);
     * Util.log.error("Erreur lors de l'opération", exception);
     * </pre>
     */
    public static final Logger log = LogManager.getLogger(Util.class);

    /**
     * Formate une date en chaîne de caractères au format français (JJ/MM/AAAA).
     * <p>Cette méthode est utile pour afficher les dates de manière lisible
     * dans l'interface utilisateur ou les rapports.</p>
     *
     * @param d La date à formater (objet {@link LocalDate}).
     * @return Une chaîne représentant la date au format "JJ/MM/AAAA".
     * @throws DateTimeParseException Si la date est invalide (ne devrait jamais se produire
     *                                avec un objet {@link LocalDate} valide).
     * @throws IllegalArgumentException Si la date est {@code null}.
     *
     * @see LocalDate#format(DateTimeFormatter)
     * @see DateTimeFormatter
     */
    public static String dateJour(LocalDate d) throws DateTimeParseException {

        DateTimeFormatter formatFR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return d.format(formatFR);
    }
}


