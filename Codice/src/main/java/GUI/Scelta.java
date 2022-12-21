package GUI;

import Gestione.Gestore;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Scelta extends JFrame{
    protected List<String> nomi;
    protected JLabel label[];
    protected JButton buttons[];
    protected int iPlayerVoting;
    protected float voti;

    public Scelta(int n,int nB){
        voti=0;
        label=new JLabel[n];
        setLayout(null);
        buttons=new JButton[nB];
        for(int i=0;i<nB;i++) {
            buttons[i] = new JButton();
            buttons[i].setSize(100, 50);
        }
    }
    public void start(List<String> nomi){
        revalidate();
        repaint();
        iPlayerVoting=0;
        this.nomi=nomi;
        makeGui();
        setButtonPosition(0);
        setVisible(true);
    }

    protected void makeGui(){
        for (int i =0;i<nomi.size();i++){
            label[i]=new JLabel(nomi.get(i));
            label[i].setSize(400,20);
            label[i].setLocation(0,i*60+20);
            add(label[i]);
        }
    }
    protected void setButtonPosition(int iP){
        for(int i=0;i<buttons.length;i++){
            buttons[i].setLocation(100+i*110,iP*60+10);
            add((buttons[i]));
        }
    }
    protected void setButtonText(int i,String name){
        buttons[i].setText(name);

        buttons[i].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                poll(i);
            }
        });
    }

    private void poll(int voto){
        if(iPlayerVoting<nomi.size()) {
            voti=+voto;//voto va da 0 a ..
            iPlayerVoting++;
            setButtonPosition(iPlayerVoting);
        }
        if(iPlayerVoting==nomi.size()){
            Gestore.setPoll("Pos:"+(int)Math.floor(voti/nomi.size()));
            setVisible(false);
        }
    }
}
