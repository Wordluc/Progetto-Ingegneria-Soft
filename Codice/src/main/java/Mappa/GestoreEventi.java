package Mappa;

import Entita.Player;
import Gestione.resStep;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public abstract class GestoreEventi {

    protected int ProbPiena;//mixCaselle>>0 ->piu caselle con eventi ho
    protected int Nrighe ;
    protected List<String> lines;
    public GestoreEventi(String urlEventi) throws IOException {
        lines = Files.readAllLines(Paths.get(urlEventi));
        Nrighe = lines.size();
    }
    public List<String> multiEventi(int n, Player player){//duplicazione evento,es:[muovitiVeloce,2]->[muovitiVeloce;muovitiVeloce]

        List<String> step= Arrays.asList(player.getCurrentStep().split(","));

        step.set(1,"1");

        List<String> r=new ArrayList<>();
        String s=step.toString().replace("[","");
        s=s.replace("]","");

        s=s.replace(" ","");
        for (int i=0;i<n;i++) {
              r.add(s);
        }
        return r;

    }
    public resStep getStepPosNeg(String t){
        String type="";
        String step="";
        int i;
        do{
            Random random = new Random();
            i = random.nextInt(Nrighe);
            i = i % 2 == 0 ? i : i - 1;
            step=lines.get(i + 1).substring(1);
            type=lines.get(i + 1).substring(0,1);
        }while(!type.equals(t));//continuo a cercare un step con il tipo che voglio
        resStep r=new resStep();
        r.setSteps(new LinkedList<String>(List.of(step.split(";"))));
        r.setDesc(lines.get(i));
        return r;
    }
    protected TypeCasella GetTypeCasella(){//mixCaselle>>0 ->piu caselle con eventi
        Random random=new Random();
        if(random.nextInt(100+1)< ProbPiena) {//se esce minore di mixCaselle ->casella piena
            return TypeCasella.piena;
        }
        return TypeCasella.vuota;
    }
    public resStep getDefaultSteps(){
        return resStep.getResInstant("movimento,1","movimento");
    }
    public resStep generaSteps(){//genero un evento
        Random random = new Random();
        int i = random.nextInt(Nrighe);
        i = i % 2 == 0 ? i : i - 1;
        resStep r=new resStep();
        String step=lines.get(i + 1).substring(1,lines.get(i + 1).length());
        r.setSteps(new LinkedList<String>(List.of(step.split(";"))));
        r.setDesc(List.of(lines.get(i)).toString());


        return r;
    }
}
