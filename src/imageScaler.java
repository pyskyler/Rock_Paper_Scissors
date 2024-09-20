import javax.swing.*;
import java.awt.*;

public class imageScaler {

    /**
     * Constructs an imageScaler object.
     */
    public imageScaler() {


    }
    /**
     * Scales an image to the specified width and height.
     * @param imageFile the path to the image file
     * @param width the desired width
     * @param height the desired height
     * @return the scaled image
     */
    public Image scaleImage(String imageFile, int width, int height) {

        ImageIcon icon = new ImageIcon(imageFile);
        Image image = icon.getImage();
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);

    }
}
