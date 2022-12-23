package GUI;

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
    private int outCome=-1;

     Scelta(int n,int nB){
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
        outCome=-1;
        iPlayerVoting=0;
        this.nomi=nomi;
        makeGui(nomi,0);
        setButtonPosition(0);
        setVisible(true);
        revalidate();
        repaint();
    }

    protected void makeGui(List<String>nomi,int py){
        for (int i =0;i<nomi.size();i++){
            label[i]=new JLabel(nomi.get(i));
            label[i].setSize(400,20);
            label[i].setLocation(0,i*60+20+py);
            add(label[i]);
        }
    }
    protected void setButtonPosition(int iP){
        for(int i=0;i<buttons.length;i++){
            buttons[i].setLocation(100+i*110,iP*60+10);
            add((buttons[i]));
        }

    }
    protected void setButton(int i, String name){
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
            voti=voti+voto;
            iPlayerVoting++;
            setButtonPosition(iPlayerVoting);
        }
        if(iPlayerVoting==nomi.size()){
            outCome=(int)Math.floor(voti/nomi.size());
            setVisible(false);
        }
    }
    public int getOutcome(){
        try {
            return outCome;
        } finally {
            outCome=-1;//dopo aver ritornato il valore setto outCome al suo stato iniziale
        }
    }
}
