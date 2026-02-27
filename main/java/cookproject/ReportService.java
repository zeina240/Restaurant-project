package cookproject;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class ReportService {

    public FinancialReport generateMonthlyReport() {
        String data = "Total Revenue: $5000, Expenses: $3000, Net Profit: $2000";
        return new FinancialReport("Monthly Revenue Report", data);
    }

    public boolean exportToPDF(FinancialReport report, String path) {
        try {
            File pdfFile = new File(path);
            pdfFile.getParentFile().mkdirs();

            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(100, 700);
            contentStream.showText(report.getData());
            contentStream.endText();
            contentStream.close();

            document.save(path);
            document.close();

            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
