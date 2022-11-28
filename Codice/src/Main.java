import Caselle.Casella;

public class Main {
    public static void main(String[] args) throws Exception {
        Mappa m=new Mappa(3,3);
        m.genera("C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\src\\Risorse\\Eventi.txt",5);
        System.out.println(m);
    }
}