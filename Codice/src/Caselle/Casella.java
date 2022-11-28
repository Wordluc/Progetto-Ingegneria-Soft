package Caselle;

public class Casella extends Evento {
    public String url;

    public Casella(String stepsString,String descrizione) {//con evento
        super(stepsString.split(","),descrizione);
        vuota=false;
    }
    public Casella() {//senza evento
        super();
        vuota=true;
    }
    //gestione grafica della singola casella
    public void disegna(){
        
    }
    //ottenere gli steps da compiere
    public String[] getSteps(){
        return steps;
    }
    //ottenere lo step alla posizione i
    public String getSteps(int i){
        return steps[i];
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
