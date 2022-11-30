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

    public Evento() {//evento nullo
        this.steps=new String[1];
        steps[0]="vuota";
    }
    @Override
    public String toString() {
        if(!vuota)
          return "descrizione:"+descrizione+",steps:"+ Arrays.toString(steps);
        return "vuota";
    }
}
