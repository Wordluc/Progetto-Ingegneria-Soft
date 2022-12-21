package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class SceltaTutti extends Scelta{
    private List<String> steps;
    public SceltaTutti(int n){
        super(n,3);
    }

    public void start(List<String>nomi,List<String>steps){
        this.start(nomi);
        this.steps=steps;
        setButtonText(0,"1");
        setButtonText(1,"2");
        setButtonText(3,"3");
    }
}
