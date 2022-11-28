package oggetti;

import java.util.Random;

public class Dado {
    public int max = 6;
    public int min = 1;
    public Dado(){
        this(6,1);
    }
    public Dado(int max, int min){
        this.max = max;
        this.min = min;
    }
    public int lanciaDado(){
        Random random = new Random();
        return random.nextInt((max+1)-min)+min;

    }
}
