package Entita;

import GUI.GestoreGui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Pawn {
    private String URL;
    public BufferedImage image;
    public Pawn(String url) throws IOException {
        System.out.println(url);
        image= ImageIO.read(new File(url));

    }
    public void setPawn( String URL){
        this.URL=URL;
    }
    public void draw(){

    }
    public void draw(Graphics g,int x, int y){
        g.drawImage(image, x, y, null);
    }
    public String getURL(){
        return URL;
    }
    public void setURL(String URL){
        this.URL = URL;
    }
}
