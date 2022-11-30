package oggetti;

public class Player{
    public String nome;
    private int posizione;
    public Pawn pawn;
    private String[] steps;
    public int iSteps;
    //--- private bool stato;
    //--- private int [][] posizione;
    //--- Pedina
    public Player(String nomePlayer){
        this.nome=nomePlayer;
    }

    //--- Funzioni
    public void scegliPedina(Pawn pawn[]){

    }
    public String getStep(){
        if(iSteps<steps.length)
           return steps[iSteps];
        return "null";
    }
    public String[] getSteps(){
        return steps;
    }
    public void movimento(){}
    public void faiAzione(){}
    public int lanciaDado(){
        Dado dado = new Dado();
        return dado.lanciaDado();
    }







}
