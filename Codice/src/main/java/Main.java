import Gestione.Gestore;
import Gestione.Mappa;
import Entita.Player;
import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        JPanel j=new JPanel();
            Mappa m=new Mappa(4,"C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\Eventi.txt",3);
            Gestore g=new Gestore(m, new String[]{"cial", "frf"});
            Player p=g.getPlayer(1);
            m.generaMappa();
            g.setStepsPlayers(p);


            p.multiEventi(1);


            System.out.println(p);

    }
}
