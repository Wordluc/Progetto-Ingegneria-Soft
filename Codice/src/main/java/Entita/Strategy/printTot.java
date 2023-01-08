package Entita.Strategy;

import Entita.Player;

public class printTot implements print {
    private Player p;

    public printTot(){
    }

    @Override
    public String print() {
        if(p.getSteps()==null)
            return "nome " + p.nome + ",posizione " + p.getPosizione();
        return "nome " + p.nome + ",posizione " + p.getPosizione() + ":" + p.getSteps() + "i-esimostep" + p.iSteps;

    }
    @Override
    public void setPlayer(Player p) {
        this.p=p;
    }
}
