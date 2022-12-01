package oggetti;

public class Player{
    public String nome;
    private int posizione;
    private int sizeMappa;
    public Pawn pawn;
    private String[] steps;
    public int iSteps;

    //--- private bool stato;
    //--- private int [][] posizione;
    //--- Pedina
    public Player(String nomePlayer,int sizeMappa){
        this.nome=nomePlayer;
        this.sizeMappa=sizeMappa;
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
    public String getStep(){
        if(iSteps<steps.length)
           return steps[iSteps];
        System.err.println("fuori range,steps");
        return "null";
    }
    public boolean upIsteps(int i){
        if(i<steps.length) {
            iSteps=i;
            return true;
        }else
            return false;

    }
    public boolean upIsteps(){
        if(iSteps+1<steps.length) {
            iSteps+=1;
            return true;
        }else
            return false;

    }
    public String[] getSteps(){
        return steps;
    }
    public void faiAzione(){}
    public int lanciaDado(){
        return Dado.lanciaDado();
    }



}
