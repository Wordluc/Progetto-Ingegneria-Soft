package Entita;

import java.util.Random;

public class Dado {
    private static int max = 6;
    private static int min = 1;
    private static Random random;
    private static int faccia;
    private static String lastUtente;
    public Dado(){
        this(6,1);
        random= new Random();
    }
    public Dado(int max, int min){
        this.max = max;
        this.min = min;
    }
    public static  String getUtente(){
        return lastUtente;
    }
    public static int lanciaDado(){

        faccia= random.nextInt((max+1)-min)+min;
        return faccia;

    }
    public static int getDado(){
        return faccia;
    }
}
