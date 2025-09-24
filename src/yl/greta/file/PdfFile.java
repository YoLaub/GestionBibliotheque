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

    private String file;


    public PdfFile(String file) {
        this.file = file;

    }

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

    @Override
    public void sendFile() throws IOException {

    }

}
