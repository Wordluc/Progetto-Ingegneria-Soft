package Caselle;

import javax.swing.*;

public class Casella extends Evento {
    public String url;

    public Casella(String stepsString,String descrizione) {//con azione
        super(stepsString.split(","),descrizione);
        vuota=false;
    }
    public Casella() {//senza azione
        super();
        vuota=true;
    }
    //gestione grafica della singola casella
    public void disegna(JPanel canvas){
        
    }

    public String[] getSteps(){//ottenere gli steps da compiere
          return steps;
    }

    public String getSteps(int i){//ottenere lo step[i]
        if(i<steps.length)
          return steps[i];
        return "fuori";
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
