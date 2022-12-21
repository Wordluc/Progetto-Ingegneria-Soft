package Mappa;

import Entita.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class CreazioneEventi {
    protected int mixCaselle;//mixCaselle>>0 ->piu caselle con eventi ho
    protected int Nrighe ;
    protected List<String> lines;
    public CreazioneEventi(String urlEventi) throws IOException {
        lines = Files.readAllLines(Paths.get(urlEventi));
        Nrighe = lines.size();
    }
    public List<String> multiEventi(int n, Player player){//duplicazione evento,es:[muovitiVeloce,2]->[muovitiVeloce;muovitiVeloce]

        List<String> step= Arrays.asList(player.getStep().split(","));

        step.set(1,"1");

        List<String> r=new ArrayList<>();
        String s=step.toString().replace("[","");
        s=s.replace("]","");

        s=s.replace(" ","");
        for (int i=0;i<n;i++) {
              r.add(s);
        }
        player.delStep(player.iSteps);
        player.setStep(r,player.iSteps);
        return r;

    }
    public LinkedList<String> getStepPosNeg(String t){
        String type="";
        String step="";
        do{
            Random random = new Random();
            int i = random.nextInt(Nrighe);
            i = i % 2 == 0 ? i : i - 1;
            step=lines.get(i + 1).substring(1,lines.get(i + 1).length());
            type=lines.get(i + 1).substring(0,1);
        }while(!type.equals(t));
        return new LinkedList<String>(List.of(step.split(";")));
    }
    protected boolean vuotaSiNo(){//mixCaselle>>0 ->piu caselle con eventi
        Random random=new Random();
        if(random.nextInt(mixCaselle) != 0) {
            return false;
        }
        return true;
    }
    public LinkedList<String> getDefaultSteps(){
        return new LinkedList<String>(List.of(new String[]{"movimento,1"}));
    }
    public LinkedList<String> generaSteps(int pCasella){//genero un evento
        Random random = new Random();
        int i = random.nextInt(Nrighe);
        i = i % 2 == 0 ? i : i - 1;
        String step=lines.get(i + 1).substring(1,lines.get(i + 1).length());
        return new LinkedList<String>(List.of(step.split(";")));
    }
}
