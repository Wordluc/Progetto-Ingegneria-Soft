package oggetti;

public class Player{
    public String nome;
    private int posizione;
    public Pawn pawn;
    //--- private bool stato;
    //--- private int [][] posizione;
    //--- Pedina
    public Player(String nomePlayer){
        this.nome=nomePlayer;
    }

    //--- Funzioni
    public void scegliPedina(Pawn pawn[]){

    }
    public void movimento(){}
    public void faiAzione(){}
    public int lanciaDado(){
        Dado dado = new Dado();
        return dado.lanciaDado();
    }







}
