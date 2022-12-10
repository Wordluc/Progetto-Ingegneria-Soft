package Gestione;

import Entita.*;
import Mappa.GestoreMappa;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class Gestore extends JPanel {
    private final GestoreMappa mappa;
    private final Player players[];
    private int Nplayer;
    private final Dado dado;
    private JLabel labelsteps;
    private int iPlayer=0;//playeer attivo
    public Gestore(GestoreMappa mappa, String[] nomi, String[] url, JLabel labSteps) throws IOException {
        this.mappa=mappa;
        this.dado=new Dado();
        this.Nplayer=nomi.length;
        this.labelsteps=labSteps;
        players=new Player[Nplayer];
        for(int i=0;i<Nplayer;i++) {
            players[i] = new Player(nomi[i], mappa.size,url[i]);
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
          player.setStep(mappa.multiEventi(nTurni-1,player));//nTurni-1 poichè la prima itarazione la sto gia facendo
          switch (step[0]){//seleziono evento da eseguire
              case "inizio":
                  player.setPosizione(0);
                  break;
              case "tiroDado":
                  dado.lanciaDado();
                  player.movimento(dado.faccia);
                  break;
              case "movimento":
                  dado.lanciaDado();
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
                  dado.lanciaDado();
                  player.movimento(dado.faccia);
                  break;
              case "sceltaPosNeg":
                  dado.lanciaDado();
                  player.movimento(Dado.faccia);
                  break;
              case "sceltaTutti":
                  dado.lanciaDado();
                  player.movimento(Dado.faccia);
                  break;
              case "movimentoTurnoPrec":
                  dado.lanciaDado();
                  player.setPosizione(player.posizioneAntecedente);
                  break;
              case "MovPari/dispari":
                  dado.lanciaDado();
                  if(dado.faccia%2==0)
                      player.movimento(dado.faccia);
                  else
                      player.movimento(-dado.faccia);
                  break;
          }

    }
    public boolean turnoPLayer(){//turno del giocatore,true->sono arrivato alla fine
        boolean blocco=false;
        Player p=players[iPlayer];//player attivo

        loopEvento(p);

        List<String> steps = mappa.generaSteps(p.getPosizione());//genero un altro step
        if(!p.getStep().equals("[tiroDado,1]")) {
            iPlayer = incrIplayer();


        }
        labelsteps.setText(players[iPlayer].toString());
            boolean s=p.incrIstep();

        if(!p.getStep().equals("[stop,1]")) {
            if (mappa.getStatusCasellaVuota(p.getPosizione())) {
               if(!s)
                   p.setStep(mappa.getDefaultSteps());
            } else {
                setStepsPlayers(p);
                if (steps.get(0).equals("finito")) {
                    return true;
                }
            }
        }





        revalidate();
        repaint();
        return false;

    }
    public void paint(Graphics g) {
        mappa.draw(g,players);

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
    private int incrIplayer(){
        int i=iPlayer;
        if(iPlayer<3)
            i+=1;
        else {
            i = 0;
        }
        return i;
    }

}
