package Gestione;
import Entita.*;
import GUI.SceltaPosNeg;
import GUI.SceltaTutti;
import GUI.SchermataPlayers;
import Mappa.GestoreMappa;
import Mappa.TypeCasella;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class Gestore extends JPanel {
    private final GestoreMappa mappa;
    private final Player[] players;
    private final SchermataPlayers ScPlayer;
    private final SceltaPosNeg ScPosNeg;
    private final int Nplayer;
    private static boolean stato=true;
    private final Dado dado;
    private static int iPlayer=0;//playeer attivo
    private static Gestore me=null;
    private final SceltaTutti ScTot;

    private Gestore(GestoreMappa mappa, String[] nomi, BufferedImage image[]) throws IOException {
        this.mappa=mappa;
        this.ScPosNeg=new SceltaPosNeg(nomi.length-1);
        this.ScTot=new SceltaTutti(nomi.length-1);
        this.dado=new Dado();
        this.Nplayer=nomi.length;
        this.ScPlayer=new SchermataPlayers(nomi.length);
        players=new Player[Nplayer];
        for(int i=0;i<Nplayer;i++) {
            players[i] = new Player(nomi[i],image[i],(i%2==0?0:50),(i<2?0:50));
            newStepPlayer(players[i]);
        }
        updateGPlayer();
        ScPlayer.revalidate();
        ScPlayer.repaint();


    }
    public static Gestore getInstance(GestoreMappa mappa, String[] nomi, BufferedImage image[]) throws IOException {
        if(me==null)
            me=new Gestore(mappa,nomi,image);
        return me;
    }
    private void newStepPlayer(Player player){//genero l'evento per il player
        resStep r=mappa.generaSteps(player.getPosizione());
        player.setStep(r);
    }
    private void updateGPlayer(){
        for(int i =0;i<players.length;i++)
            if(i==iPlayer) {
               ScPlayer.setLabel(i, new String[]{"->Giocatore:" + players[i].nome, players[i].getStep().getDesc()});
                players[iPlayer].getStep().setDesc(players[iPlayer].getStep().getDesc().replace("'n'", ""+(dado.faccia-1)));

            }else {
                ScPlayer.setLabel(i, new String[]{"Giocatore:" + players[i].nome, players[i].getStep().getDesc()});
            }
    }
    private void movAntOrario() {
        List<Player> playersList = Arrays.asList(players.clone());
        Collections.sort(playersList);

        int pFin = playersList.get(playersList.size()-1).getPosizione();
        for (int i = playersList.size()-1; i > 0; i--) {
            playersList.get(i).setPosizione(playersList.get(i - 1).getPosizione());

        }
        playersList.get(0).setPosizione(pFin);
    }
    private boolean loopEvento(Player player){//true->continua;false->interrompi turno

          String steps=player.getCurrentStep();
          if(steps.equals("null")){
              player.movimento(dado.faccia);
              return true;
           }
          String [] step=steps.split(",");//prendo step con i sui parametri da eseguire
          int nTurni;
          //controllo quante volte eseguire un evento
          if(step[1].equals("n"))//dipende dal dado
              nTurni=dado.faccia;
          else
              nTurni= Integer.parseInt(step[1]);//controllo per quante azioni effettuare l'evento step
          List<String> newr=mappa.multiEventi(nTurni-1,player);//nTurni-1 poichè la prima itarazione la sto gia facendo
          player.delStep(player.iSteps);
          player.setStep(newr,player.iSteps);
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

                  }else if(step[2].equals("-1")){
                      movAntOrario();
                  }
                  break;
              case "sceltaPosNeg":
                      StopPLay();
                      ScPosNeg.start(getPlayerName(players[iPlayer].nome));
                      return false;
              case "sceltaTutti":
                  StopPLay();
                  ArrayList <resStep> r=new ArrayList<>();
                  for(int i=0;i<3;i++){
                      r.add(mappa.generaSteps());
                  }
                  ScTot.start(getPlayerName(players[iPlayer].nome),r);
                  return false;
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
        return true;
    }
    public static void StopPLay(){
         stato=!stato;
    }
    public List<String> getPlayerName(String elementR){//ritorno i nomi dei giocatori ,in caso tolgo elementR
        List<String>r=new ArrayList<>();
        for(int i=0;i<players.length;i++)
            if(!players[i].nome.equals(elementR)){
                r.add(players[i].nome);
            }
        return r;
    }
    public void loop(){//turno del giocatore,true->sono arrivato alla fine
        if(stato) {
            Player p = players[iPlayer];//player attivo
            if(!loopEvento(p))
                return;
            if (p.getPosizione() >= mappa.size) {
                StopPLay();
                JFrame fine=new JFrame();
                fine.setSize(50,70);
                fine.add(new JLabel("Vincitore:"+players[iPlayer].nome));
                fine.setVisible(true);
                return;
            }
            ScPlayer.setLabel(4, new String[]{"Dado:", String.valueOf(dado.faccia)});
            boolean prosStep = p.incrIstep();
            if (!p.getCurrentStep().equals("stop,1")) {
                if (!prosStep)
                    p.setStep(mappa.getDefaultSteps());
                if (mappa.getStatusCasellaVuota(p.getPosizione())== TypeCasella.piena) {
                    newStepPlayer(p);//genera altro step
                }
            }
            if (!p.getCurrentStep().equals("tiroDado,1")) {
               upPlayer();
            }
        }
        int outPN=ScPosNeg.getOutcome();
        int outT=ScTot.getOutcome();
        if(outPN!=-1){//gestione eventi scelta
                players[iPlayer].setStep(mappa.getStepPosNeg(outPN==0?"+":"-"));//setto step positivo/negativo
                StopPLay();//faccio ripartire la partita
                upPlayer();
        }else if(outT!=-1){
            players[iPlayer].setStep(ScTot.getStep(outT));
            StopPLay();//faccio ripartire la partita
            upPlayer();
        }
      guiUpdate();
    }
    private void upPlayer(){
        iPlayer = incrIplayer();
        if(players[iPlayer].getCurrentStep().equals("stop,1")) {
            loop();
        }
    }
    private void guiUpdate(){
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
