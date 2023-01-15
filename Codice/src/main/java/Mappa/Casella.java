package Mappa;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Casella extends JFrame{
    public BufferedImage image;
    public int pos;
    public int x;
    public int y;
    private int size;
    private TypeCasella type;
    public Casella(int pos, TypeCasella type, int x, int y, BufferedImage image) {
        this.pos=pos;
        this.type = type;
        this.x=x;
        this.y=y;
        this.image=image;
        size=image.getHeight();
    }

    @Override
    public String toString() {
        return x+","+y+pos+"vuota:"+ type;
    }
    public void paint(Graphics g){
            g.drawImage(image, x*size, y*size, null);
    }
    public int [] getPos(){//get posizione assoluta (pixel)
        return new int[]{x*size,y*size};
    }
    public TypeCasella getTypeC(){
        return type;
    }
}
