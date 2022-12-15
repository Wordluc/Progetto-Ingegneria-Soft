import Gestione.Gestore;
import Mappa.GestoreMappa;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame j =new JFrame();
        File directory = new File("./");
        System.out.println(directory.getAbsolutePath());
        j.setSize(650,600);
        GestoreMappa m=new GestoreMappa(30,"resource\\Eventi.txt",2,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");

        Gestore g=new Gestore(m, new String[]{"1", "2","3","4"},new String[]
                {"resource\\sprite\\p1.png"
                ,"resource\\sprite\\p2.png"
                ,"resource\\sprite\\p3.png"
                ,"resource\\sprite\\p4.png"
                });

        JButton b=new JButton("lancia");
        b.setSize(100,50);
        j.setLayout(null);

        g.setLocation(0,0);
        g.setSize(600,500);
        b.setLocation(400, 500);
        j.add(b);

        g.repaint();
       j.add(g);
       b.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               g.turnoPLayer();
           }
       });
       j.setVisible(true);
            m.generaMappa();




    }
}
