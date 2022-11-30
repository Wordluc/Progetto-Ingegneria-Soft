import Caselle.*;

import javax.swing.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Mappa {
    public Casella caselle[][];
    public int size[]=new int[2];
    private JPanel canvas;
    public Mappa(int Sx,int Sy,JPanel canvas){
        caselle=new Casella[Sx][Sy];//creo la matrice di caselle
        size[0]=Sx;
        size[1]=Sy;
        this.canvas=canvas;
    }
    public void genera(String urlEventi,int mixCaselle) throws Exception {//assegnazione degli eventi ad ogni casella
        List<String> lines= Files.readAllLines(Paths.get(urlEventi));//prendo il file e lo leggo
        int Nrighe=lines.size();
        for(int x=0;x<size[0];x++)
            for(int y=0;y<size[1];y++) {
                //per ogni casella :
                //scelgo se inserire una casella con evento o libera
                Random random = new Random();
                if (random.nextInt(mixCaselle) != 0) {//[nextInt->0..mixCaselle),scelgo se inserire evento
                    //estraggo un evento casuale dal file
                    random = new Random();
                    int i = random.nextInt(Nrighe);
                    i = i % 2 == 0 ? i : i - 1;//prendo una 'i' pari,i pari =descrizione,i dispari=steps
                    caselle[x][y] = new Casella(lines.get(i+1), lines.get(i));//(i+1)->steps,(i)->descrizione)
                } else//casella vuota
                    caselle[x][y] = new Casella();
            }
    }
    public String[] getSteps(int x,int y){
       return caselle[x][y].getSteps();
    }
    public String getStep(int x,int y,int p){
        try {//x,y non sono al interno dal range size?
            return caselle[x][y].getSteps(p);
        }catch (IndexOutOfBoundsException e){
            System.err.println("casella non esistente ,"+size[0]+"x"+size[1]);
        }
        return "error";
    }
    public String toString(){//stampo tutte le caselle della mappa
        String all = "";
        for(int x=0;x<size[0];x++)
            for(int y=0;y<size[1];y++){
                all=all+x+"+"+y+caselle[x][y]+"\n";
            }
        return all;
    }
    public String toString(int x,int y){
        return caselle[x][y]+"\n";
    }
}
