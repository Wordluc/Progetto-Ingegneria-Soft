package GUI;

import Mappa.Casella;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GestoreGui {
    private static JFrame frame;
    private Casella caselle[];
    public GestoreGui(){

    }
    public static BufferedImage getImage(String url) throws IOException {
        BufferedImage image = ImageIO.read(new File(url));
        return image;
    }
}
