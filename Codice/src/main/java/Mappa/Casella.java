package Mappa;

import Entita.Player;

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
    public void drawPow(Graphics g,BufferedImage image, int i, Player ps[]){
        switch (i) {
            case 0:
                g.drawImage(image,x*size+0,y*size+0,null);
            case 1:
                g.drawImage(image,x*size+25,y*size+0,null);
            case 2:
                g.drawImage(image,x*size+25,y*size+25,null);
            case 3:
                g.drawImage(image,x*size+0,y*size+25,null);
        }

    }
}
