package Entita;

import Gestione.resStep;
import Mappa.GestoreMappa;

import java.io.IOException;
import java.util.List;

public class Player extends Pawn implements Comparable<Player> {
    public final String nome;
    private int posizione;
    public int posizioneAntecedente;
    private resStep steps;
    public int iSteps;

    //--- private bool stato;
    //--- private int [][] posizione;
    //--- Pedina
    public Player(String nomePlayer,String url,int xPawn,int yPawn) throws IOException {
        super(url,xPawn,yPawn);


        this.nome=nomePlayer;
    }
    //--- Funzioni
    public boolean movimento (int n){//vado avanti,nel caso finisco ritorno true
        posizioneAntecedente=posizione;
        posizione+=n;
        if(posizione<GestoreMappa.size)
            if(posizione>=0)
               return false;
            else
                posizione=0;
        return true;
    }
    public void setPosizione(int i){
        posizioneAntecedente = posizione;
        if(i<GestoreMappa.size) {
              if(i>=0)
                  posizione = i;
              else
                posizione=0;
              return;
          }
          System.err.println("non posso spostarmi ,fuori ");
    }
    public int getPosizione(){
       return posizione;
    }
    public void setStep(resStep steps){
        this.steps=steps;
        iSteps=0;
    }
    public void setStep(List<String>stepsIn,int i){
        for(String stepIn:stepsIn) {
            this.steps.addStep(i, stepIn);
            i++;
        }
    }
    public void delStep(int i){
        this.steps.delStep(i);
    }
    public List<String> getSteps(){
        return steps.getStep();
    }
    public String getCurrentStep(){
        if(iSteps<steps.getSize())
           return steps.getStep(iSteps);
        System.err.println("fuori range,steps");
        return "null";
    }
    public resStep getStep(){return steps;}
    public boolean incrIstep(int i){//step i-esimo eseguito
        if(i<steps.getSize()) {
            iSteps=i;
            return true;
        }else
            return false;
    }
    public boolean incrIstep(){//step iSteps-esimo eseguito
        if(iSteps<steps.getSize()) {
            iSteps+=1;
            return true;
        }
        iSteps=0;
        return false;
    }

    @Override
    public String toString() {
        return "nome"+nome+",posizione"+getPosizione()+":"+getSteps()+"i-esimostep"+iSteps;

    }
    public int compareTo(Player u) {
        if(getPosizione()>u.getPosizione())
            return 1;
        else if(getPosizione()<u.getPosizione())
            return -1;
        return 0;
    }
}
