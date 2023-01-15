package GUI;

import Gestione.resStep;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SceltaTutti extends Scelta{
    private ArrayList <resStep> steps;
    public SceltaTutti(int n){
        super(n+5,3);
        setSize(600,600);
        for(int i=0;i<buttons.length;i++) {
            setButton(i, "" + i);
        }
    }
    public resStep getStep(int i){
        return steps.get(i);
    }

    public void start(List<String>nomi,ArrayList <resStep> steps){
        super.start(nomi);
        this.steps=steps;
        makeGui(nomi,10,"player");
        setButtonsPosition(0);
        List<String>testi=new LinkedList<>();
        testi.add("<-------------------------->");
        testi.add("Le scelte sono:");

        for(int i=0;i<steps.size();i++)
           testi.add(i+":"+steps.get(i).getDesc());
        makeGui(testi,10,"other");

    }
}
