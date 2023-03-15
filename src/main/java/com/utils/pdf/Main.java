package com.utils.pdf;

/*
 ** @Author:         blue_sky
 ** @CreateDate:     2023-03-15  15:11
 ** @ProjectName:    pdf-utils
 ** @Package:        com.utils.pdf
 */

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        String html = parseThymeleafTemplate();
        try {
            generatePdfFromHtml(html);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static String parseThymeleafTemplate() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("to", "Baeldung");

        ArrayList<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User(i,"张三"+i,"1560545646","银河系地球"));
        }
        context.setVariable("to", "Baeldung");
        context.setVariable("userList", userList);
        return templateEngine.process("thymeleaf_template", context);
    }

    public static  void generatePdfFromHtml(String html) throws IOException, DocumentException {
        String outputFolder = "thymeleaf.pdf";
        OutputStream outputStream = new FileOutputStream(outputFolder);

        ITextRenderer renderer = new ITextRenderer();
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("C:/Windows/fonts/simsun.ttc", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);


        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
    }


    private static void pdfBox() throws IOException {
        // Create a new empty document
        PDDocument document = new PDDocument();


        // Create a new blank page and add it to the document
        PDPage page = new PDPage(PDRectangle.A5);
        document.addPage(page);



        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont( font, 12 );
        contentStream.newLineAtOffset( 10, 100 );
        contentStream.showText( "D:\\SoftWare\\Java\\openjdk-11windows-x64\\bin\\java.ex" );
        contentStream.newLineAtOffset( 0, 13 );
        contentStream.showText( "D:\\SoftWare\\Java\\openjdk-11windows-x64\\bin\\java.ex" );

        contentStream.endText();

        // Make sure that the content stream is closed:
        contentStream.close();

        // Save the newly created document
        document.save("BlankPage.pdf");

        // finally make sure that the document is properly
        // closed.
        document.close();
    }
}
