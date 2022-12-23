package GUI;

import java.util.ArrayList;
import java.util.List;

public class SceltaTutti extends Scelta{
    private List<String> steps;
    public SceltaTutti(int n){
        super(n+4,3);
    }

    public void start(List<String>nomi, ArrayList<String> steps){
        this.start(nomi);
        this.steps=steps;
        for(int i=0;i<nomi.size();i++)
           setButton(i,""+i);

        steps.add(0,"");
        steps.add(1,"Le scelte sono:");
        makeGui(steps,200);
    }
}
