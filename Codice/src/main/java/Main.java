import ChoosePage.ChooseCharacter;
import ChoosePage.WindowCharacter;
import OpenPage.HomePage;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
/*
        JFrame j =new JFrame();
        j.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        j.setSize(650,600);
        GestoreMappa m=GestoreMappa.getInstance(30,"resource\\Eventi.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");

        Gestore g=Gestore.getInstance(m, new String[]{"1", "2","3","4"},new String[]
                {"resource\\sprite\\p1.png"
                ,"resource\\sprite\\p2.png"
                ,"resource\\sprite\\p3.png"
                ,"resource\\sprite\\p4.png"
                });

        JButton b=new JButton("Lancia");
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

*/
        WindowCharacter w=new WindowCharacter(4,new String[]{"resource\\sprite\\p1.png"
                ,"resource\\sprite\\p2.png"
                ,"resource\\sprite\\p3.png"
                ,"resource\\sprite\\p4.png"});
        w.makeChoose();
        w.setVisible(true);


    }
}
