package Gestione;

import Entita.*;
import Entita.Dado;

import java.util.List;

public class Gestore {
    private Mappa mappa;
    private Player players[];
    private int Nplayer;
    private Dado dado;
    public Gestore(Mappa mappa, String nomi[]){
        this.mappa=mappa;
        this.dado=new Dado();
        this.Nplayer=nomi.length;
        players=new Player[Nplayer];
        for(int i=0;i<Nplayer;i++) {
            players[i] = new Player(nomi[i], mappa.size,dado);
            System.out.println(nomi[i]+i);
        }

    }
    public void setPlayers(Pawn pawns[]){
        for(int i=0;i<Nplayer;i++)
            players[i].scegliPedina(pawns);
    }
    public void setStepsPlayers(Player player){//genero l'evento per il player
        List<String> e=mappa.generaSteps(player.getPosizione());
        player.setStep(e);
    }
    private void movimento(String [] step,Player player){//gestisco lo spostamento
        if(step.length==2)//se non vi sono parametri mi baso solo sul dado
            player.setPosizione(Dado.getDado());
        else{//vi sono parametri

        }
          setStepsPlayers(player);
    }
    private boolean loopEvento(Player player){//paramentro i->giocatore seguente
          String [] step=player.getStep().split(",");//prendo step da eseguire
          int nTurni= Integer.parseInt(step[1]);//controllo per quante azioni effettuare l'evento step
          mappa.multiEventi(nTurni-1,player);//nTurni-1 poichè la prima itarazione la sto gia facendo
          switch (step[0]){//seleziono evento da eseguire
              case "inizio":
                  player.setPosizione(0);
                  break;
              case "tiroDado":
                  player.lanciaDado();
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

          return player.upIsteps();//Ho eseguito step ,quindi vado avanti

    }
    public void loop(){
        int i=0;
        Player p=players[i];//player attivo
        boolean r;
        do {
            p.lanciaDado();
            p.movimento();
            if(mappa.getStatusCasellaVuota(p.getPosizione()))//se ricado in una cella vuota
               loopEvento(players[i]);//gestisco vecchi eventi
            else {//se ricado in una cella con evento
                List<String> steps = mappa.generaSteps(p.getPosizione());//genero un altro step
                p.setStep(steps);
                loopEvento(players[i]);
            }

            if(i<3)
               i+=1;
            else
                i=0;
        }while(true);//vado avanti con il players[i],finche non finisco di eseguire gli eventi
    }
    public Player getPlayer(int i){
        return players[i];
    }
}
