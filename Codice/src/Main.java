import oggetti.Dado;

public class Main {
    public static void main(String[] args) {
        Dado d = new Dado();
        for(int i = 0; i<40; i++)
            System.out.println(d.lanciaDado());
        System.out.println("Hello world!");
    }
}