package Gestione;

import Entita.Player;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Mappa {
    public Casella caselle[];
    private JPanel canvas;
    private String urlEventi;
    private int mixCaselle;
    public  int size;
    private int Nrighe ;
    private List<String> lines;

    public Mappa(int size,String urlEventi,int mixCaselle) throws IOException {
        lines = Files.readAllLines(Paths.get(urlEventi));
        Nrighe = lines.size();
        this.mixCaselle=mixCaselle;//probabilità caselle nulle
        this.size=size;
        caselle=new Casella[size];//creo la matrice di caselle
        this.urlEventi=urlEventi;
        for (int i=0;i<size;i++) {
            boolean stato = eventoSiNo();
            caselle[i] = new Casella(i,stato);
        }
    }
    public List<String> multiEventi(int n, Player player){//duplicazione evento,es:muoviti più veloce per due turni=[muovitiVeloce,2]->[muovitiVeloce;muovitiVeloce]
        System.out.println(player.getStep());
        List<String> step= Arrays.asList(player.getStep().split(","));
        step.set(1,"1");
        Stream<String> stream = Stream.of();
        stream = Stream.concat(stream, player.getSteps().stream());
        for (int i=0;i<n;i++) {
            stream = Stream.concat(stream, step.stream());
        }

        return stream.collect(Collectors.toList());

    }
    public List<String> generaSteps(int pCasella){
        if(pCasella<size) {
            if(!caselle[pCasella].vuoto){
                ;//prendo il file e lo leggo
                Random random = new Random();
                int i = random.nextInt(Nrighe);
                i = i % 2 == 0 ? i : i - 1;
                return Arrays.asList(lines.get(i + 1).split(";"));
            } else {//casella vuota
                return Arrays.asList(new String[]{"movimento,1"});
            }
        }
        System.err.println("fuori mappa");
        return Arrays.asList(new String[]{"errore"});
    }
    public boolean getStatusCasellaVuota(int p){
        return caselle[p].vuoto;
    }
    private boolean eventoSiNo(){
        Random random=new Random();
        if(random.nextInt(mixCaselle) != 0) {//[nextInt->0..mixCaselle),scelgo se inserire evento
            return false;
        }
        return true;
    }
    public void generaMappa() throws IOException {//assegnazione degli eventi ad ogni casella
         for (int i=0;i<caselle.length;i++){
             caselle[i].vuoto=eventoSiNo();//decido se dove mettere un evento o no
    }



    }
}
