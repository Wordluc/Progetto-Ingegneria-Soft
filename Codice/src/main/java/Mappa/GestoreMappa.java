package Mappa;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Entita.Player;
import Gestione.resStep;

import javax.imageio.ImageIO;
import java.util.Arrays;

public class GestoreMappa extends GestoreEventi {
    private static
    GestoreMappa me=null;
    private Casella caselle[];

    private final String sprite[];
    public static int size=9;
    public void draw(Graphics g, Player ps[]){
        for (int ic=0;ic<size;ic++)
            caselle[ic].paint(g);
        for(int ip=0;ip<ps.length;ip++) {
            if (ps[ip].getPosizione() < size){
                int []pos=caselle[ps[ip].getPosizione()].getPos();
                ps[ip].draw(g,pos[0],pos[1]);
            }
        }
    }

    public resStep generaSteps(int pCasella){

        if(pCasella<size) {
            if (caselle[pCasella].getTypeC()== TypeCasella.piena) {//casella non vuoto,genero evento
                return super.generaSteps();
            } else//evento movimento
            {
                return resStep.getResInstant("movimento,1","movimento");
            }
        }
        System.err.println("fuori mappa");

        return resStep.getResInstant("finito","finito");
    }
    public resStep generaSteps(){
        return super.generaSteps();
    }
    public Casella getCaselle(int i){
        if(i<caselle.length)
          return caselle[i];
        return null;
    }
    private GestoreMappa(int size, String urlEventi, int ProbPiena,String spriteVuoto,String spritePieno) throws IOException {

        super(urlEventi);
        sprite=new String[]{spriteVuoto,spritePieno};
        this.ProbPiena =ProbPiena;//probabilità caselle nulle

        this.size=size;

        caselle=new Casella[size];//creo la matrice di caselle
        generaMappa();

    }
    public static GestoreMappa getInstance(int size, String urlEventi, int ProbPiena,String spriteVuoto,String spritePieno) throws IOException {

        if(me==null)
            me=new GestoreMappa(size,urlEventi,ProbPiena,spriteVuoto,spritePieno);
        return me;
    }

    public String[] genMatrix(int xmax,int ymax){
        String []pos=new String[xmax*ymax];
        int i=0;
        int xt=0;
        int ax=1;
        for (int yt=0;yt<ymax;yt++) {

            for(int ix=0;ix<xmax;ix++) {

                pos[i] = xt + "," + yt;
                xt += ax;
                i++;
            }
            if(xt>=xmax) {
                ax = ax * (-1);
                xt=xmax-1;
            }
            if(xt<=0) {
                ax = ax * (-1);
                xt=0;
            }
        }
        return pos;
    }
    public TypeCasella getStatusCasellaVuota(int p){
            return caselle[p].getTypeC();
    }
    private void generaMappa() throws IOException {//assegnazione degli eventi ad ogni casella
        String[]s=genMatrix(size/5,5);
        BufferedImage vuoto= ImageIO.read(new File(sprite[0]));
        BufferedImage piena=ImageIO.read(new File(sprite[1]));
        TypeCasella stato=TypeCasella.piena;

        for (int i=0;i<size;i++) {
            stato =GetTypeCasella();

            if(stato== TypeCasella.vuota)
                caselle[i] = new Casella(i,stato,Integer.parseInt(s[i].split(",")[0]),Integer.parseInt(s[i].split(",")[1]),vuoto);
            else
                caselle[i] = new Casella(i,stato,Integer.parseInt(s[i].split(",")[0]),Integer.parseInt(s[i].split(",")[1]),piena);
        }
    }
    @Override
    public String toString() {
        return "caselle:="+ "caselle=" + Arrays.toString(caselle);
    }
}
