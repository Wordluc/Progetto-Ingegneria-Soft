import Caselle.*;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Mappa {
    public Casella caselle[];
    private JPanel canvas;
    private String urlEventi;
    private int mixCaselle;
    private int size;
    private int Nrighe ;
    private List<String> lines;
    private boolean eventoSiNo(){
        Random random=new Random();
        if(random.nextInt(mixCaselle) != 0) {//[nextInt->0..mixCaselle),scelgo se inserire evento
            return false;
        }
        return true;
    }
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

    public String[] genera(int pCasella) throws IOException {//assegnazione degli eventi ad ogni casella
        if(pCasella<size) {
                if(!caselle[pCasella].vuoto){
                    ;//prendo il file e lo leggo
                    Random random = new Random();
                    int i = random.nextInt(Nrighe);
                    i = i % 2 == 0 ? i : i - 1;
                    return lines.get(i + 1).split(";");
            } else//casella vuota
                return new String[]{"vuoto"};
        }
        System.err.println("fuori mappa");
        return new String[]{"errore"};
    }
}
