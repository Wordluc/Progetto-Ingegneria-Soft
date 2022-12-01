import oggetti.Dado;
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
            players[i]=new Player(nomi[i],mappa.size);

    }
    public void setPlayers(Pawn pawns[]){
        for(int i=0;i<Nplayer;i++)
            players[i].scegliPedina(pawns);
    }
    private boolean loopEvento(Player player){//paramentro i->giocatore seguente
          String [] step=player.getStep().split(",");
          switch (step[0]){//seleziono evento da eseguire
              case "inizio":
                  player.setPosizione(0);
                  break;
              case "tiroDado":
                  player.lanciaDado();
                  break;
              case "movimento":
                  if(step.length==1)//se non vi sono parametri mi baso solo sul dado
                     player.setPosizione(Dado.faccia);
                  else{//vi sono parametri

                  }

                  break;
              case "stop":
                  break;
              case "ScambioPosizione":
                  break;
              case "sceltaPosNeg":
                  break;
              case "sceltaTutti":
                  break;
              case "movimentoTurnoPrec":
                  break;
              case "MovPari/dispari":
                  break;
          }

          return player.upIsteps();

    }
    public void loop(){
        int i=1;//esempio player
        boolean r;
        do {
            r=loopEvento(players[i]);
        }while(r);//vado avanti con il players[i],finche non finisco di eseguire gli eventi
    }
}
