package GUI;

import java.util.List;

public class SceltaPosNeg extends  Scelta{
    public SceltaPosNeg(int n){
        super(n,2);
        setLocation(0,0);
        setSize(500,400);
        setButton(0,"buono");
        setButton(1,"cattivo");
    }
    public void start(List<String> nomi){
        super.start(nomi);
        makeGui(nomi,0,"player");
    }
}
