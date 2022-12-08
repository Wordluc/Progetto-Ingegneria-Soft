package Gestione;

import Entita.*;
import Mappa.GestoreMappa;


import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Gestore extends JPanel {
    private final GestoreMappa mappa;
    private final Player players[];
    private int Nplayer;
    private final Dado dado;
    private int iPlayer=0;//playeer attivo
    public Gestore(GestoreMappa mappa, String nomi[]){
        this.mappa=mappa;
        this.dado=new Dado();
        this.Nplayer=nomi.length;
        players=new Player[Nplayer];
        for(int i=0;i<Nplayer;i++) {
            players[i] = new Player(nomi[i], mappa.size);
        }
    }
    public void setPlayers(String url){
          players[iPlayer].setPawn(url);
    }

    public void setStepsPlayers(Player player){//genero l'evento per il player
        List<String> e=mappa.generaSteps(player.getPosizione());
        player.setStep(e);
    }
    private void loopEvento(Player player){
          String steps=player.getStep();

          if(steps.equals("null")){
              player.movimento(dado.faccia);
              return;
           }
          String [] step=steps.split(",");//prendo step con i sui parametri da eseguire
          int nTurni;
          //controllo quante volte eseguire un evento
          if(step[1].equals("n"))//dipende dal dado
              nTurni=dado.faccia;
          else
              nTurni= Integer.parseInt(step[1]);//controllo per quante azioni effettuare l'evento step
          mappa.multiEventi(nTurni-1,player);//nTurni-1 poichè la prima itarazione la sto gia facendo
          switch (step[0]){//seleziono evento da eseguire
              case "inizio":
                  player.setPosizione(0);
                  break;
              case "tiroDado":
                  player.movimento(dado.faccia);
                  dado.lanciaDado();
                  player.movimento(dado.faccia);
                  break;
              case "movimento":
                  if(step.length==2)
                    player.movimento(dado.faccia);
                  else{
                      if(step[2].equals("*2"))
                        player.movimento(dado.faccia*2);
                      else if(step[2].equals("-1"))
                        player.movimento(-dado.faccia);
                      else if(step[2].equals("0.5"))
                          player.movimento(Integer.parseInt(String.valueOf(dado.faccia/2)));
                  }
                  break;
              case "stop"://non faccio niente
                  break;
              case "scambioPosizione":
                  player.movimento(dado.faccia);
                  break;
              case "sceltaPosNeg":
                  player.movimento(dado.faccia);
                  break;
              case "sceltaTutti":
                  player.movimento(dado.faccia);
                  break;
              case "movimentoTurnoPrec":
                  player.setPosizione(player.posizioneAntecedente);
                  break;
              case "MovPari/dispari":
                  if(dado.faccia%2==0)
                      player.movimento(dado.faccia);
                  else
                      player.movimento(-dado.faccia);
                  break;
          }

    }
    public boolean turnoPLayer(){//turno del giocatore,true->sono arrivato alla fine
        Player p=players[iPlayer];//player attivo
        dado.lanciaDado();
        loopEvento(p);
        List<String> steps = mappa.generaSteps(p.getPosizione());//genero un altro step
        if(steps.get(0).equals("finito")) {
            System.out.print("finito" + p.nome);
            return true;
        }
        if(!p.incrIstep())
            p.setStep(mappa.getDefaultSteps());
        if(!mappa.getStatusCasellaVuota(p.getPosizione())) {//genero se vado su una casella con evento
            setStepsPlayers(p);

        }
        System.out.println("dado:"+dado.faccia+"\n"+toString());
        incrIplayer();
        return false;
    }
    public void paint(Graphics g) {
        mappa.draw(g);
    }
    public void loop(){
        boolean r;
       /* do {

            if(turnoPLayer()) {
                break;
            }



        }while(true);//vado avanti con il players[i],finche non finisco di eseguire gli eventi*/
    }
    public Player getPlayer(int i){
        return players[i];
    }
    @Override
    public String toString() {
        String a="";
        for (Player p:players)
            a+=p;
        return a;
    }
    private void incrIplayer(){
        if(iPlayer<3)
            iPlayer+=1;
        else {
            iPlayer = 0;
            System.out.println("NUOVO TURNO");
        }
    }

}
