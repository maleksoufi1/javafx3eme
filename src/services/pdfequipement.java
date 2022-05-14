/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import entites.Produit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

/**
 *
 * @author dell
 */
public class pdfequipement {
    public void liste_equipement() throws FileNotFoundException, DocumentException {

        ProduitService ec = new ProduitService();
        String message = "Voici la liste des produits \n\n";

        String file_name = "src/service/liste_produits.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
        List<Produit> equipement = ec.getAll();
        PdfPTable table = new PdfPTable(5);

        
        
        PdfPCell cl1 = new PdfPCell(new Phrase("ref produit"));
        table.addCell(cl1);
        PdfPCell cl = new PdfPCell(new Phrase("Nom produit"));
        table.addCell(cl);
        PdfPCell cl2 = new PdfPCell(new Phrase("image"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("prix produit"));
        table.addCell(cl3);
        PdfPCell cl4 = new PdfPCell(new Phrase("description"));
        table.addCell(cl4);
        
        
        
        table.setHeaderRows(1);
        document.add(table);

        int i = 0;
        for (i = 0; i < equipement.size(); i++) {
            
            table.addCell("" + equipement.get(i).getNom());
            table.addCell("" + equipement.get(i).getImage());
            table.addCell("" + equipement.get(i).getPrix());
            
            table.addCell("" + equipement.get(i).getDescription());

        }
        document.add(table);

        document.close();

    }
}
