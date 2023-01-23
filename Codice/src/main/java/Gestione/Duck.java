package Gestione;



import Mappa.GestoreMappa;
import PagineSecondarie.ChoosePage.WindowCharacter;
import PagineSecondarie.HomePage;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Duck {
    public static void main(String[] args) throws IOException {
        HomePage home=new HomePage();


    }
    public static void sceltaPersonaggi()  {
        WindowCharacter w=new WindowCharacter(4,new String[]{"codice\\resource\\sprite\\p1.png"
                ,"codice\\resource\\sprite\\p2.png"
                ,"codice\\resource\\sprite\\p3.png"
                ,"codice\\resource\\sprite\\p4.png"});
        w.makeChoose();

        w.setVisible(true);
        w.repaint();
    }
    public static void start(BufferedImage[] image, String[] nameFinal) throws IOException {
        JFrame frame =new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(650,600);
        GestoreMappa m=GestoreMappa.getInstance(30,"codice\\resource\\Eventi.txt",20,"codice\\resource\\sprite\\vuoto.png","codice\\resource\\sprite\\pieno.png");

        Gestore g=Gestore.getInstance(m, nameFinal,image);
        g.start();
        JButton b=new JButton("Lancia");
        b.setSize(100,50);
        frame.setLayout(null);

        g.setLocation(0,0);
        g.setSize(600,500);
        b.setLocation(400, 500);
        frame.add(b);

        g.repaint();
        frame.add(g);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                g.loop();
            }
        });
        frame.setVisible(true);
       // m.generaMappa();

    }
}
