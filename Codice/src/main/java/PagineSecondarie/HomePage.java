package PagineSecondarie;

import Gestione.Duck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class HomePage extends JFrame {
    private final JButton bstart;
    private final JButton binfo;
    private final JButton besci;

    public HomePage(){

        setSize(500,500);
        setLayout(null);
        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("codice\\resource\\homepagebk.jpg")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Font edgeFont = null;
        try {
            edgeFont = Font.createFont(Font.TRUETYPE_FONT, new File("codice\\resource\\edgefont.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        JLabel title=new JLabel("DUCK GAME");
        title.setFont(edgeFont.deriveFont(70f));
        title.setSize(400,100);
        title.setLocation(56,20);
        title.setForeground(new Color(255,204,6));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.bstart = addButton(110,250,150,"START",new Color(154,252,151), edgeFont);

        this.binfo = addButton(210,180,150,"INFO",new Color(255,252,155),edgeFont);

        this.besci = addButton(310,180,150,"ESCI",new Color(255,227,223),edgeFont);
        add(title);
        setVisible(true);
        setListener();
    }
    private JButton addButton(int px,int py,int dx,int dy,String testo){
        JButton b= new JButton(testo);
        b.setSize(dx,dy);
        b.setLocation(px,py);
        add(b);
        return b;
    }
    private JButton addButton(int py,int dx,int dy,String testo, Color color, Font font){
        JButton b= new JButton(testo);
        b.setFont(font.deriveFont(60f));
        b.setForeground(color);
        b.setSize(dx,dy);
        b.setLocation((this.getWidth()-dx)/2,py);
        b.setBorderPainted(false);
        b.setContentAreaFilled(false);
        b.setFocusPainted(false);
        b.setOpaque(false);
        add(b);
        return b;
    }

    private void setListener(){
        bstart.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                   Duck.sceltaPersonaggi();
                   setVisible(false);
            }
        });
        besci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        binfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

}
