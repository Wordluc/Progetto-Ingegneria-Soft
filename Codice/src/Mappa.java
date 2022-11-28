import Caselle.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;

public class Mappa {
    public Casella caselle[][];
    public int size[]=new int[2];
    public Mappa(int Sx,int Sy){
        caselle=new Casella[Sx][Sy];
        size[0]=Sx;
        size[1]=Sy;
    }
    public void genera(String urlEventi,int mixCaselle) throws Exception {
        List<String> lines= Files.readAllLines(Paths.get(urlEventi));
        int Nrighe=lines.size();
        for(int x=0;x<size[0];x++)
            for(int y=0;y<size[1];y++){
                 Random  random=new Random();
                 int i=random.nextInt(Nrighe);
                 i=i%2==0?i:i-1;
                 if(random.nextInt(mixCaselle)!=0)
                     caselle[x][y]=new Casella(lines.get(i),lines.get(i+1));
                 else
                     caselle[x][y]=new Casella();
            }
    }
    public String toString(){
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
