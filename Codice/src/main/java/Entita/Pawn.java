package Entita;

import GUI.GestoreGui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Pawn {
    public BufferedImage image;
    public int[]pos;
    public Pawn(String url,int xPawn,int yPawn) throws IOException {
        this.pos=new int[]{xPawn,yPawn};//posizione relativa(in pixel)
        setURL(url);

    }
    public void draw(Graphics g,int x,int y){
        g.drawImage(image,x+pos[0],y+pos[1],null);
    }
    public void setURL(String url) throws IOException {
        image= ImageIO.read(new File(url));
    }
}
