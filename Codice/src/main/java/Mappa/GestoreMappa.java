package Mappa;

import GUI.GestoreGui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;


public class GestoreMappa extends GestoreEvento {
    public Casella caselle[];


    public static int size;
    public void draw(Graphics g){
        for (int i=0;i<size;i++)
            caselle[i].paint(g);
    }
    public List<String> generaSteps(int pCasella){
        if(pCasella<size) {
            if (!caselle[pCasella].vuota) {//casella non vuoto,genero evento
                return super.generaSteps(pCasella);
            } else//evento movimento
            {
                return Arrays.asList(new String[]{"movimento,1"});
            }
        }
        System.err.println("fuori mappa");
        return Arrays.asList(new String[]{"finito"});
    }
    public GestoreMappa(int size, String urlEventi, int mixCaselle) throws IOException {
        super(urlEventi);
        this.mixCaselle=mixCaselle;//probabilità caselle nulle
        this.size=size;
        caselle=new Casella[size];//creo la matrice di caselle
        generaMappa();

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
        return caselle[p].vuota;
    }

    public void generaMappa() throws IOException {//assegnazione degli eventi ad ogni casella
        String[]s=genMatrix(size/5,5);
        BufferedImage vuoto=GestoreGui.getImage("C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\src\\main\\java\\vuota.png");
        BufferedImage piena=GestoreGui.getImage("C:\\Users\\frang\\Desktop\\Java\\Progetto-Ingegneria-Soft\\Codice\\src\\main\\java\\piena.png");
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
