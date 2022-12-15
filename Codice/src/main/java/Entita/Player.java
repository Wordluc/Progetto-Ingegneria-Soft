package Entita;

import Mappa.GestoreMappa;

import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Player extends Pawn{
    public final String nome;
    private int posizione;
    public int posizioneAntecedente;
    private LinkedList<String> steps;
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
    public void setStep(LinkedList<String>steps){

        this.steps=steps;
        iSteps=0;
    }
    public void setStep(List<String>steps,int i){
        for(String d:steps) {
            this.steps.add(i, d);
            i++;
        }
    }
    public void delStep(int i){
        this.steps.remove(i);
    }
    public List<String> getSteps(){
        return steps;
    }//prendo tutti gli steps
    public String getStep(){
        if(iSteps<steps.size())
           return steps.get(iSteps);
        System.err.println("fuori range,steps");
        return "null";
    }
    public boolean incrIstep(int i){//step i-esimo eseguito
        if(i<steps.size()) {
            iSteps=i;
            return true;
        }else
            return false;
    }
    public boolean incrIstep(){//step iSteps-esimo eseguito
        if(iSteps<steps.size()) {
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
}
