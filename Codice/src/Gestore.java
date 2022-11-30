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
    private void loopEvento(int i,String step){//paramentro i->giocatore seguente,steps->[evento,paramentro,parametro]
        System.out.println(step.split(",")[0]);
          switch (step.split(",")[0]){//seleziono evento da eseguire

              case "inizio":
                  break;
              case "tiroDado":
                  break;
              case "movimento":
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
          players[i].iSteps+=1;

    }
    public void loop(){
        int i=1;//esempio
        loopEvento(i,players[i].getStep());
    }
}
