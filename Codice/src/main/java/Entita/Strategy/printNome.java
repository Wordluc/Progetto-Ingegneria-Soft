package Entita.Strategy;

import Entita.Player;

public class printNome implements print{
    Player p=null;
    @Override
    public void setPLayer(Player p) {
        this.p=p;
    }

    @Override
    public String print() {
        return p.nome;
    }
}
