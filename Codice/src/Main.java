import Caselle.Casella;

import javax.swing.*;

public class Main {
    public static void main(String[] args) throws Exception {
        JFrame frame=new JFrame();

        JPanel canvas=new JPanel();
        frame.add(canvas);
        Mappa m=new Mappa(3,3,canvas);
        m.genera("C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\src\\Risorse\\Eventi.txt",1);

        System.out.println(m.getStep(1,5,3));
    }
}