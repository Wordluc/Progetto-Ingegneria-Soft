import Gestione.Gestore;
import Gestione.GestoreMappa;
import Entita.Player;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JPanel j=new JPanel();
            GestoreMappa m=new GestoreMappa(10,"C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\Eventi.txt",3);
            Gestore g=new Gestore(m, new String[]{"1", "2","3","4"});
            Player p=g.getPlayer(1);
            m.generaMappa();
            g.setStepsPlayers(g.getPlayer(0));
        g.setStepsPlayers(g.getPlayer(1));
        g.setStepsPlayers(g.getPlayer(2));
        g.setStepsPlayers(g.getPlayer(3));


            g.loop();


    }
}
