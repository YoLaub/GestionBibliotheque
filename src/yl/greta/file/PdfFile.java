/**
 * La classe {@code PdfFile} implémente l'interface {@link Fichier} et fournit
 * des fonctionnalités pour :
 * <ul>
 *   <li>Lire le contenu d'un fichier PDF existant</li>
 *   <li>Générer un rapport PDF du stock de livres à partir d'un fichier binaire</li>
 * </ul>
 * <p>
 * Cette classe utilise la bibliothèque <a href="https://itextpdf.com/">iTextPDF</a>
 * pour la création et la manipulation de documents PDF.
 * </p>
 *
 * <p><b>Exemple d'utilisation :</b></p>
 * <pre>
 * // Générer un PDF du stock de livres
 * PdfFile pdf = new PdfFile("rapport_stock.pdf");
 * pdf.writeFile();
 *
 * // Lire un PDF existant
 * PdfFile pdfReader = new PdfFile("document_existant.pdf");
 * pdfReader.readFile();
 * </pre>
 *
 * @author [Votre Nom]
 * @version 1.0
 * @since 2025-09-24
 * @see Fichier
 * @see com.itextpdf.kernel.pdf.PdfDocument
 * @see com.itextpdf.layout.Document
 */

package yl.greta.file;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import yl.greta.model.Bibliotheque;
import yl.greta.model.Livre;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PdfFile implements Fichier {

    /**
     * Chemin du fichier PDF à créer ou lire.
     * <p>Doit inclure l'extension {@code .pdf} pour une utilisation correcte.</p>
     */
    private String file;


    /**
     * Constructeur pour initialiser le chemin du fichier PDF.
     *
     * @param file Chemin complet vers le fichier PDF (ex: {@code "rapport.pdf"}).
     * @throws IllegalArgumentException Si le chemin est {@code null} ou vide.
     */
    public PdfFile(String file) {
        this.file = file;

    }


    /**
     * Lit le contenu d'un fichier PDF existant et affiche son texte dans la console.
     * <p>
     * Cette méthode extrait le texte de chaque page du document PDF et l'affiche
     * page par page.
     * </p>
     *
     * @throws IOException Si le fichier PDF est introuvable, corrompu ou inaccessible.
     * @throws IllegalStateException Si le fichier n'est pas un PDF valide.
     */
    @Override
    public void readFile() throws IOException {
        PdfReader pr = new PdfReader(file);
        PdfDocument doc = new PdfDocument(pr);
        int num = doc.getNumberOfPages();

        for (int i = 1; i <= num; i++) {
            String str = PdfTextExtractor.getTextFromPage(doc.getPage(i));
            System.out.println(str);
        }
        doc.close();
    }


    /**
     * Génère un document PDF contenant la liste des livres du stock.
     * <p>
     * Ce rapport PDF comprend :
     * <ul>
     *   <li>Un titre avec la date du jour</li>
     *   <li>Un tableau listant tous les livres avec leurs propriétés (titre, auteur, éditeur, date de sortie)</li>
     * </ul>
     * </p>
     * <p>
     * Les données des livres sont lues depuis le fichier binaire {@code stock.bin}
     * situé dans le dossier {@code backup/}.
     * </p>
     *
     * @throws IOException Si une erreur survient lors de la lecture du fichier binaire
     *                     ou de l'écriture du PDF.
     * @throws ClassNotFoundException Si la désérialisation des objets Livre échoue.
     * @throws FileNotFoundException Si le fichier {@code stock.bin} est introuvable.
     */
    @Override
    public void writeFile() throws IOException, ClassNotFoundException {
        String nomFichierEntrée = "backup" + File.separator + "stock.bin";

        // 1. Lire la liste des livres depuis le fichier binaire
        ArrayList<Livre> livres;
        try (ObjectInputStream lire = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(nomFichierEntrée)))) {
            livres = (ArrayList<Livre>) lire.readObject(); // Désérialisation de l'ArrayList<Livre>
        }

        // 2. Créer le document PDF
        try (PdfWriter writer = new PdfWriter(file);
             PdfDocument pdfDoc = new PdfDocument(writer);
             Document doc = new Document(pdfDoc)) {

            // Ajouter un titre
            PdfFont myFont = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            Paragraph p1 = new Paragraph("Stock du " + LocalDate.now());
            p1.setTextAlignment(TextAlignment.CENTER);
            p1.setFont(myFont);
            p1.setFontSize(28);
            doc.add(p1);

            // 3. Créer un tableau avec 4 colonnes
            Table table = new Table(new float[]{2, 3, 2, 2}); // Largeurs relatives des colonnes
            table.addHeaderCell("Titre");
            table.addHeaderCell("Auteur");
            table.addHeaderCell("Editeur");
            table.addHeaderCell("Date de sortie");

            // 4. Remplir le tableau avec les données des livres
            for (Livre livre : livres) {
                table.addCell(livre.getTitre());
                table.addCell(livre.getAuteur());
                table.addCell(livre.getEditeur());
                table.addCell(livre.getDateSortie().toString()); // Convertir LocalDate en String
            }

            // 5. Ajouter le tableau au document
            table.setHorizontalAlignment(HorizontalAlignment.CENTER);
            doc.add(table);
        }
    }


    /**
     * <b>Non implémenté.</b> Cette méthode est requise par l'interface {@link Fichier}
     * mais n'est pas utilisée pour la gestion des fichiers PDF.
     *
     * @throws IOException Toujours levé (méthode non supportée).
     */
    @Override
    public void sendFile() throws IOException {

    }

}
