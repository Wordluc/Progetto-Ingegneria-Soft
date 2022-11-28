package Caselle;

import java.util.Arrays;

public abstract class Evento {
    protected String descrizione;
    protected String [] steps;
    protected boolean vuota;
    public Evento(String []steps,String descrizione){
        this.steps=steps;
        this.descrizione=descrizione;
    }

    public Evento() {

    }
    @Override
    public String toString() {
        if(!vuota)
          return "descrizione:"+descrizione+",steps:"+ Arrays.toString(steps);
        return "vuota";
    }
}
