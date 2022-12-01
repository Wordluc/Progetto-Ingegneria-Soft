package Entita;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Player{
    public String nome;
    private int posizione;
    private int sizeMappa;
    public Pawn pawn;
    private List<String> steps;
    public int iSteps;
    private Dado dado;
    //--- private bool stato;
    //--- private int [][] posizione;
    //--- Pedina
    public Player(String nomePlayer,int sizeMappa,Dado dado){
        this.nome=nomePlayer;
        this.sizeMappa=sizeMappa;
        this.dado=dado;
    }

    //--- Funzioni
    public void scegliPedina(Pawn pawn[]){

    }

    public void setPosizione(int i){
        if(i<sizeMappa) {
              posizione = i;
              return;
          }
          System.err.println("non posso spostarmi ,fuori ");
    }
    public int getPosizione(){
       return posizione;
    }
    public void setStep(List<String>steps){
        this.steps=steps;
    }
    public String getStep(){
        if(iSteps<steps.size())
           return steps.get(iSteps);
        System.err.println("fuori range,steps");
        return "null";
    }
    public void multiEventi(int n){//duplicazione evento,es:muoviti più veloce per due turni=[muovitiVeloce,2]->[muovitiVeloce;muovitiVeloce]

        List<String> step= Arrays.asList(getStep().split(","));
        step.set(1,"1");
        Stream<String> stream = Stream.of();
        stream = Stream.concat(stream, steps.stream());
        for (int i=0;i<n;i++) {//duplico gli eventi che valgono per più turni
            stream = Stream.concat(stream, step.stream());
        }

        steps=stream.collect(Collectors.toList());



    }
    public boolean upIsteps(int i){
        if(i<steps.size()) {
            iSteps=i;
            return true;
        }else
            return false;

    }
    public boolean upIsteps(){
        if(iSteps+1<steps.size()) {
            iSteps+=1;
            return true;
        }
        iSteps=0;
        return false;


    }
    public List<String> getSteps(){
        return steps;
    }
    public void faiAzione(){}
    public int lanciaDado(){
        return dado.lanciaDado();
    }
    public void movimento(){

    }
    @Override
    public String toString() {
        return steps.toString();

    }
}
