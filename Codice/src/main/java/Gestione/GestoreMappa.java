package Gestione;

import javax.swing.*;
import java.io.IOException;

import java.util.Arrays;
import java.util.List;


public class GestoreMappa extends  GestoreEvento{
    public Casella caselle[];
    private JPanel canvas;


    public static int size;

    public List<String> generaSteps(int pCasella){
        if(pCasella<size) {
            if(!caselle[pCasella].vuoto) {//casella non vuoto,genero evento
                return super. generaSteps(pCasella);
            }else//evento movimento
                return Arrays.asList(new String[]{"movimento,1"});
        }
        System.err.println("fuori mappa");
        return Arrays.asList(new String[]{"finito"});
    }
    public GestoreMappa(int size, String urlEventi, int mixCaselle) throws IOException {
        super(urlEventi);


        this.mixCaselle=mixCaselle;//probabilità caselle nulle
        this.size=size;
        caselle=new Casella[size];//creo la matrice di caselle

        for (int i=0;i<size;i++) {
            boolean stato = eventoSiNo();
            caselle[i] = new Casella(i,stato);
        }
    }
    public boolean getStatusCasellaVuota(int p){
        return caselle[p].vuoto;
    }
    public void generaMappa() throws IOException {//assegnazione degli eventi ad ogni casella
         for (int i=0;i<caselle.length;i++){
             caselle[i].vuoto=eventoSiNo();//decido se dove mettere un evento o no
    }



    }
}
