package Mappa;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Casella extends JFrame{
    public BufferedImage image;
    public Canvas canvas;
    public int pos;
    public int x;
    public int y;
    private int size;
    public boolean vuota;
    public Casella(int pos,boolean vuota,int x,int y,BufferedImage image) {
        this.pos=pos;
        this.vuota = vuota;
        this.x=x;
        this.y=y;
        this.image=image;
        size=image.getHeight();
    }
    @Override
    public String toString() {
        return x+","+y+pos+"vuota:"+ vuota;
    }
    public void paint(Graphics g){
            g.drawImage(image, x*size, y*size, null);
    }
    public int [] getPos(){
        return new int[]{x*size,y*size};
    }
}
