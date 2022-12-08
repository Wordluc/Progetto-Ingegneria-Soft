import Gestione.Gestore;
import Mappa.GestoreMappa;
import Entita.Player;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JFrame j =new JFrame();
        j.setSize(500,600);
            GestoreMappa m=new GestoreMappa(20,"C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\Eventi.txt",5);
            Gestore g=new Gestore(m, new String[]{"1", "2","3","4"});
        JButton b=new JButton("lancia");
        b.setSize(100,50);
        j.setLayout(null);
        g.setLocation(0,0);
        g.setSize(500,500);
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
            g.setStepsPlayers(g.getPlayer(0));
        g.setStepsPlayers(g.getPlayer(1));
        g.setStepsPlayers(g.getPlayer(2));
        g.setStepsPlayers(g.getPlayer(3));

        System.out.println(m);

        g.loop();



    }
}
