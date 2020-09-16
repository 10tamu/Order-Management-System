package swinRestaurant;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class TestPDF {
	public static void main(String[] args) {
		Document d = new Document(PageSize.A4);
		d.addAuthor("Palak");
		d.addTitle("My PDF Creation");
		try {
			PdfWriter.getInstance(d, new FileOutputStream("/swinRestaurant/Bill Receipt/test56.pdf"));
			d.open();
			Paragraph p = new Paragraph("haha i did it 213.");
			d.add(p);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		d.close();
	}
}
