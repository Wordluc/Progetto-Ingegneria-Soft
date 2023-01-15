package Entita.Strategy;

import Entita.Player;

public class printTot implements  print{
    Player p;

    @Override
    public void setPLayer(Player p) {
        this.p=p;
    }

    @Override
    public String print() {
        if(p!=null)
          return "nome "+p.nome+",posizione "+p.getPosizione()+",step "+p.getStep()+","+ p.iSteps;
        return null;
    }
}
