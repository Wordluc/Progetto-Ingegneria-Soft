package Caselle;

import java.util.Arrays;

public abstract class Evento {
    protected String descrizione;
    protected String [] steps;
    protected boolean vuota;
    public Evento(String []steps,String descrizione){
        this.steps=steps;
        this.descrizione=descrizione;
        vuota=false;
    }

    public Evento() {//evento nullo
        this(new String[]{"vuota"},"vuota");
        vuota=true;

    }
    @Override
    public String toString() {
        if(!vuota)
          return "descrizione:"+descrizione+",steps:"+ Arrays.toString(steps);
        return "vuota";
    }
}
