package Mappa;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import Entita.Player;

import javax.imageio.ImageIO;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class GestoreMappa extends CreazioneEventi {
    private static
    GestoreMappa me=null;
    public Casella caselle[];

    private final String sprite[];
    public static int size;
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
    public LinkedList<String> generaSteps(int pCasella){
        if(pCasella<size) {
            if (!caselle[pCasella].vuota) {//casella non vuoto,genero evento
                return super.generaSteps(pCasella);
            } else//evento movimento
            {
                return new LinkedList<String>(List.of(new String[]{"movimento,1"}));
            }
        }
        System.err.println("fuori mappa");
        return new LinkedList<String>(List.of(new String[]{"finito"}));
    }

    private GestoreMappa(int size, String urlEventi, int mixCaselle,String spriteVuoto,String spritePieno) throws IOException {
        super(urlEventi);
        sprite=new String[]{spriteVuoto,spritePieno};
        this.mixCaselle=mixCaselle;//probabilità caselle nulle
        this.size=size;
        caselle=new Casella[size];//creo la matrice di caselle
        generaMappa();

    }
    public static GestoreMappa getInstance(int size, String urlEventi, int mixCaselle,String spriteVuoto,String spritePieno) throws IOException {
        if(me==null)
            me=new GestoreMappa(size,urlEventi,mixCaselle,spriteVuoto,spritePieno);
        return me;
    }
    public static GestoreMappa getInstance(){
            return me;
    }

    private String[] genMatrix(int xmax,int ymax){
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
    public boolean getStatusCasellaVuota(int p){
        try {
            return caselle[p].vuota;
        }catch (Exception e) {
            return false;
        }
    }
    public void generaMappa() throws IOException {//assegnazione degli eventi ad ogni casella
        String[]s=genMatrix(size/5,5);
        BufferedImage vuoto= ImageIO.read(new File(sprite[0]));
        BufferedImage piena=ImageIO.read(new File(sprite[1]));
        boolean stato;
        for (int i=0;i<size;i++) {
            stato = vuotaSiNo();

            if(stato)
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
