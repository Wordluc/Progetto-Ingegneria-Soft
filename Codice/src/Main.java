import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        JPanel j=new JPanel();
            Mappa m=new Mappa(4,"C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\Eventi.txt",3);
            Gestore g=new Gestore(m, new String[]{"cial", "frf"});
            g.loop(0,m.genera(3)[0]);

    }
}
