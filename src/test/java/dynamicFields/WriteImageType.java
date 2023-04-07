package dynamicFields;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class WriteImageType {
    static public void main(String args[]) throws Exception {
        try {
            int width = 430, height = 80;

            // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
            // into integer pixels
            BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

            Graphics2D ig2 = bi.createGraphics();


            Font font = new Font("TimesRoman", Font.BOLD, 20);
            ig2.setFont(font);
            String message = "Image4";
            FontMetrics fontMetrics = ig2.getFontMetrics();
            int stringWidth = fontMetrics.stringWidth(message);
            int stringHeight = fontMetrics.getAscent();
            ig2.setPaint(Color.black);
            ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);





            ImageIO.write(bi, "PNG", new File("/Users/innovative/Documents/Development/dev/v3/DataValidatorV3/src/test/ads/Ad4.PNG"));
            ////ImageIO.write(bi, "JPEG", new File("c:\\yourImageName.JPG"));
            //ImageIO.write(bi, "gif", new File("c:\\yourImageName.GIF"));
            //ImageIO.write(bi, "BMP", new File("c:\\yourImageName.BMP"));

        } catch (IOException ie) {
            ie.printStackTrace();
        }

    }
}
