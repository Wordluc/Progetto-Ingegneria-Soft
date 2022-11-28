package Caselle;

public class Casella extends Evento {
    public String url;

    public Casella(String stepsString,String descrizione) {
        super(stepsString.split(","),descrizione);
        vuota=false;
    }
    public Casella() {
        super();
        vuota=true;
    }
    public void disegna(){
        
    }
    public String[] getSteps(){
        return steps;
    }
    public String getSteps(int i){
        return steps[i];
    }
    @Override
    public String toString() {
        return super.toString();
    }

}
