package oggetti;

public class Player {
    public String nome;
    private int posizione;
    //--- private bool stato;
    //--- private int [][] posizione;
    //--- Pedina
    private Pawn pedina;

    public Player(String nome){

    }

    //--- Funzioni
    public void scegliPedina(Pawn pawn){
        pedina = pawn;
    }
    public void movimento(){}
    public void faiAzione(){}
    public int lanciaDado(){
        Dado dado = new Dado();
        return dado.lanciaDado();
    }







}
