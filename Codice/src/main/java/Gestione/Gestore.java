package Gestione;

import Entita.*;
import GUI.SchermataPlayers;
import Mappa.GestoreMappa;


import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Gestore extends JPanel {
    private final GestoreMappa mappa;
    private final Player players[];
    private final SchermataPlayers ScPlayer;
    private int Nplayer;
    private boolean stato=true;
    private final Dado dado;
    private int iPlayer=0;//playeer attivo
    public Gestore(GestoreMappa mappa, String[] nomi, String[] url) throws IOException {
        this.mappa=mappa;
        this.dado=new Dado();
        this.Nplayer=nomi.length;
        this.ScPlayer=new SchermataPlayers(nomi.length);
        players=new Player[Nplayer];
        for(int i=0;i<Nplayer;i++) {
            players[i] = new Player(nomi[i],url[i],(i%2==0?0:50),(i<2?0:50));
            setStepsPlayers(players[i]);
        }
        updateGPlayer();
        ScPlayer.revalidate();
        ScPlayer.repaint();
    }
    public void setStepsPlayers(Player player){//genero l'evento per il player
        LinkedList<String> e=mappa.generaSteps(player.getPosizione());
        player.setStep(e);
    }
    private void updateGPlayer(){
        for(int i =0;i<players.length;i++)
            if(i==iPlayer)
                ScPlayer.setLabel(i,new String[]{"->Giocatore:"+players[i].nome, players[i].toString()});
            else
                ScPlayer.setLabel(i,new String[]{"Giocatore:"+players[i].nome, players[i].toString()});
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
                  int a;
                  if(step[2].equals("Cu")) {
                      do {
                          Random random = new Random();
                        a=random.nextInt(4);
                      }while(a==iPlayer);//la pedina A con cui mi scambio deve essere diversa da A
                      //scambio posizione
                      int p=players[a].getPosizione();
                      players[a].setPosizione(players[iPlayer].getPosizione());
                      players[iPlayer].setPosizione(p);
                  }else if(step[2].equals("Ct")){
                      for (int i =0;i<4;i++) {
                          do {
                              Random random = new Random();
                              a = random.nextInt(4);
                          } while (a == i);//la pedina A con cui mi scambio deve essere diversa da A
                          //scambio posizione
                          int p=players[a].getPosizione();
                          players[a].setPosizione(players[iPlayer].getPosizione());
                          players[iPlayer].setPosizione(p);
                      }
                  }
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
    public void turnoPLayer(){//turno del giocatore,true->sono arrivato alla fine
        if(stato) {
            Player p = players[iPlayer];//player attivo
            loopEvento(p);
            if (p.getPosizione() > mappa.size) {
                stato = false;
                return;
            }
            ScPlayer.setLabel(4, new String[]{"Dado:", String.valueOf(dado.faccia)});
            List<String> steps = mappa.generaSteps(p.getPosizione());//genero un altro step
            boolean prosStep = p.incrIstep();
            if (!p.getStep().equals("stop,1")) {
                if (!prosStep)
                    p.setStep(mappa.getDefaultSteps());
                if (!mappa.getStatusCasellaVuota(p.getPosizione())) {
                    setStepsPlayers(p);
                    ScPlayer.setLabel(iPlayer, new String[]{"Giocatore:" + players[iPlayer].nome, players[iPlayer].getSteps().toString()});
                }
            }
            if (!p.getStep().equals("tiroDado,1")) {
                iPlayer = incrIplayer();
            }
        }
        updateGPlayer();
        revalidate();
        repaint();
    }
    public void paint(Graphics g) {
        mappa.draw(g,players);
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
