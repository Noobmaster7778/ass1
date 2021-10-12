import java.awt.*;
import java.awt.print.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import com.lowagie.text.*;
import java.io.FileOutputStream;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.apache.pdfbox.printing.PDFPrintable;
import org.apache.pdfbox.printing.Scaling;

public class Print_Function {
    public static void text2pdf(String text, String pdf) throws DocumentException, IOException {
        Document document = new Document();
        OutputStream os = new FileOutputStream(new File(pdf));
        PdfWriter.getInstance(document, os);
        document.open();
        //use Windows system font (TrueType)
        BaseFont baseFont = BaseFont.createFont("src\\main\\resources\\simsun.ttc,0", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        Font font = new Font(baseFont);
        InputStreamReader isr = new InputStreamReader(new FileInputStream(new File(text)), "GBK");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            document.add(new Paragraph(str, font));
        }
        document.close();
    }

    public void printPdf(String pdfpath) throws IOException, PrinterException {
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        String printerName = "Win64 Printer : HP LaserJet Professional P1106";
        File file = new File(pdfpath);
        // read .pdf
        PDDocument document = PDDocument.load(file);
        // create the task
        PrinterJob job = PrinterJob.getPrinterJob();
        // Traverse the names of all printers
        for (PrintService ps : PrinterJob.lookupPrintServices()) {
            // Select the specified printer
            if (ps.equals(defaultService)) {
                job.setPrintService(ps);
                break;
            }
        }

        job.setPageable(new PDFPageable(document));
        Paper paper = new Paper();
        // Set print paper size
        paper.setSize(598, 842); // 1/72 inch
        // Set plot position coordinates
        paper.setImageableArea(0, 0, paper.getWidth(), paper.getHeight()); // no margins
        // custom page format
        PageFormat pageFormat = new PageFormat();
        pageFormat.setPaper(paper);
        // override the page format
        Book book = new Book();
        // append all pages, set some properties, such as whether to zoom, the number of prints, etc
        book.append(new PDFPrintable(document, Scaling.ACTUAL_SIZE), pageFormat, 1);
        job.setPageable(book);
        // Start printing
        job.print();
    }

    FileInputStream keywords;
    {
        try {
            keywords = new FileInputStream("src\\main\\resources\\keyword.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    InputStreamReader read = new InputStreamReader(keywords);
    BufferedReader br = new BufferedReader(read);
    String[] kw;
    {
        try {
            kw = br.readLine().split(" ");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    JFrame mainWin = new JFrame("JTextPane");
    JTextPane txt = new JTextPane();
    StyledDocument doc = txt.getStyledDocument();//---model---Combine attribute set and text content
    //defend SimpleAttributeSet
    SimpleAttributeSet a = new SimpleAttributeSet();//---Attribute set---put the attributes to be added together - and then add the text content
    SimpleAttributeSet b = new SimpleAttributeSet();

    public void init(StringBuilder s) {
        txt.setEditable(true);
        StyleConstants.setForeground(b, Color.BLACK);
        doc.setCharacterAttributes(0, s.length(), b, true);
        String[] s1=s.toString().split(" ");
        txt.setText(s.toString());
        for (int j = 0; j < kw.length; j++) {
            StyleConstants.setForeground(a, Color.getHSBColor(10 * j, 10 * j, 10 * j));
            for (int i = 0; i < s.length(); i++) {
                i = s.indexOf(kw[j], i);
                if(i!=-1){
                    if (i>=1 && !String.valueOf(s.toString().charAt(i - 1)).equals(" ") && !String.valueOf(s.toString().charAt(i+kw[j].length())).equals(" ")){i=i+kw[j].length();}
                    else {
                        doc.setCharacterAttributes(i, kw[j].length(), a, true);
                        i = i + kw[j].length();}
                }else {break;}
            }
        }

        mainWin.add(new JScrollPane(txt), BorderLayout.CENTER);
        //Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int inset = 100;
        //Sets the size of the main window
        mainWin.setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.setVisible(true);
    }

    public static void search(String s,String keyword) {
        FileInputStream keywords = null;
        {
            try {
                keywords = new FileInputStream("keyword.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        InputStreamReader read = new InputStreamReader(keywords);
        BufferedReader br = new BufferedReader(read);
        String[] kw;
        {
            try {
                kw = br.readLine().split(" ");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        JFrame mainWin = new JFrame("JTextPane");
        JTextPane txt = new JTextPane();
        StyledDocument doc = txt.getStyledDocument();//---model---Combine attribute set and text content
        //defend SimpleAttributeSet
        SimpleAttributeSet a = new SimpleAttributeSet();//---Attribute set---put the attributes to be added together - and then add the text content
        SimpleAttributeSet b = new SimpleAttributeSet();
        txt.setEditable(true);
        StyleConstants.setForeground(b, Color.BLACK);
        doc.setCharacterAttributes(0, s.length(), b, true);
        txt.setText(s);
        StyleConstants.setForeground(a, Color.red);
        for (int i = 0; i < s.length(); i++) {
            i = s.indexOf(keyword, i);
            if(i!=-1){
                doc.setCharacterAttributes(i, keyword.length(), a, true);
                i = i + keyword.length()-1;
            }else {break;}
        }
        mainWin.add(new JScrollPane(txt), BorderLayout.CENTER);
        //Get screen size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int inset = 100;
        //Sets the size of the main window
        mainWin.setBounds(inset, inset, screenSize.width - inset * 2, screenSize.height - inset * 2);
        mainWin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainWin.setVisible(true);
    }
}