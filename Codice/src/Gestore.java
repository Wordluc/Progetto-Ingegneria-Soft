import oggetti.Pawn;
import oggetti.Player;

public class Gestore extends azioniPlayer{
    private Mappa mappa;
    private Player players[];
    private int Nplayer;
    public Gestore(Mappa mappa,String nomi[]){
        this.mappa=mappa;
        this.Nplayer=nomi.length;
        players=new Player[Nplayer];
        for(int i=0;i<Nplayer;i++)
            players[i]=new Player(nomi[i]);

    }
    public void setPlayers(Pawn pawns[]){
        for(int i=0;i<Nplayer;i++)
            players[i].scegliPedina(pawns);
    }
    public void loop(String steps,Player player){
          int i=player.iSteps;

    }
}
