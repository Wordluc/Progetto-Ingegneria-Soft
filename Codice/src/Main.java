import javax.swing.*;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        JPanel j=new JPanel();
            Mappa m=new Mappa(3,"C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\Eventi.txt",3);
            for(int i=0;i<5;i++)
                System.out.println(Arrays.toString(m.genera(i)));

    }
}
