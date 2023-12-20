import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class PdfStamping {
    public static void main(String[] args) throws Exception {

        Scanner scanner = new Scanner(System.in);
        String text;

        do {
            System.out.print("Enter up to 3 characters to be added to the image: ");
            text = scanner.nextLine();
        } while (text.length() > 3);

        BufferedImage existingImage = ImageIO.read(new File("C:\\Users\\maria\\stamp-clipart-round-7.png"));

        // Create a graphics object to draw on the existing image
        Graphics graphics = existingImage.getGraphics();

        // Set the font and color for the text
        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Arial Black", Font.BOLD, 250));

        // Get the FontMetrics to calculate text width
        FontMetrics fontMetrics = graphics.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(text);

        // Calculate the center coordinates
        int centerX = (existingImage.getWidth() - textWidth) / 2;
        int centerY = existingImage.getHeight() / 2;

        // Draw the user input text at the center
        graphics.drawString(text, centerX, centerY);


        ImageIO.write(existingImage, "png", new File("C:\\Users\\maria\\modified-stamp14.png"));

        System.out.println("Text added to the existing image at the center");

        scanner.close();
    }
}
