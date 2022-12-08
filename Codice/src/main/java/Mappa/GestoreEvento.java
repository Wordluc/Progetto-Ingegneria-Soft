package Mappa;

import Entita.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class GestoreEvento {
    protected int mixCaselle;//mixCaselle>>0 ->piu caselle con eventi ho
    protected int Nrighe ;
    protected List<String> lines;
    public GestoreEvento(String urlEventi) throws IOException {
        lines = Files.readAllLines(Paths.get(urlEventi));
        Nrighe = lines.size();
    }
    public List<String> multiEventi(int n, Player player){//duplicazione evento,es:muoviti più veloce per due turni=[muovitiVeloce,2]->[muovitiVeloce;muovitiVeloce]

        List<String> step= Arrays.asList(player.getStep().split(","));
        step.set(1,"1");
        Stream<String> stream = Stream.of();
        stream = Stream.concat(stream, player.getSteps().stream());
        for (int i=0;i<n;i++) {
            stream = Stream.concat(stream, step.stream());
        }

        return stream.collect(Collectors.toList());

    }
    protected boolean vuotaSiNo(){//mixCaselle>>0 ->piu caselle con eventi ho
        Random random=new Random();
        if(random.nextInt(mixCaselle) != 0) {
            return false;
        }
        return true;
    }
    public List<String> getDefaultSteps(){
        return List.of(new String[]{"movimento,1"});
    }
    public List<String> generaSteps(int pCasella){//genero un evento
        Random random = new Random();
        int i = random.nextInt(Nrighe);
        i = i % 2 == 0 ? i : i - 1;
        return Arrays.asList(lines.get(i + 1).split(";"));
    }
}
