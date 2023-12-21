import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class PdfStamping {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String text;

        do {
            System.out.print("Enter up to 3 characters to be added to the image: ");
            text = scanner.nextLine();
        } while (text.length() > 3);

        BufferedImage existingImage = ImageIO.read(new File("C:\\PdfStamping\\src\\main\\java\\stamp-clipart-round-7.png"));

        // Add text to the image
        addTextToImage(existingImage, text);

        // Save the modified image
        ImageIO.write(existingImage, "png", new File("C:\\PdfStamping\\src\\main\\java\\modified-stamp.png"));

        System.out.println("Text added to the stamp image");

        // Create PDF with the modified image
        createPdfWithImage(existingImage, "C:\\PdfStamping\\src\\main\\java\\modified-stamp-pdf.pdf");

        System.out.println("Stamp image is added to the PDF file");

        scanner.close();
    }

    private static void addTextToImage(BufferedImage image, String text) {
        // Create a graphics object to draw on the existing image
        Graphics graphics = image.getGraphics();

        // Set the font and color for the text
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 240));

        // Get the FontMetrics to calculate text width
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);

        // Calculate the center coordinates
        int centerX = (image.getWidth() - textWidth) / 2;
        int centerY = image.getHeight() / 2;

        // Draw the user input text at the center
        graphics.drawString(text, centerX, centerY);

        // Dispose of the graphics object
        graphics.dispose();
    }

    private static void createPdfWithImage(BufferedImage image, String pdfFilePath) throws IOException {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        PDImageXObject pdImage = LosslessFactory.createFromImage(document, image);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {

            // Add the image to the PDF page
            contentStream.drawImage(pdImage,450,20,100,100);
        }

        document.save(pdfFilePath);
        document.close();
    }
}
