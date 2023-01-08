package Entita.Strategy;

import Entita.Player;

public class printNome implements print {
    private Player p;

    public printNome() {
    }

    public String print() {
        return p.nome;

    }

    @Override
    public void setPlayer(Player p) {
        this.p=p;
    }
}
