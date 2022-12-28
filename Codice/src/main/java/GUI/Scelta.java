package GUI;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Scelta extends JFrame{
    protected List<String> otherT;
    protected List<String> nomi;
    protected JLabel label[];
    protected JButton buttons[];
    protected int iPlayerVoting;
    protected float voti;
    private int outCome=-1;

     Scelta(int nL,int nB){
        voti=0;
        label=new JLabel[nL];
        setLayout(null);
        buttons=new JButton[nB];
        for(int i=0;i<nB;i++) {
            buttons[i] = new JButton();
            buttons[i].setSize(100, 50);
            add((buttons[i]));
        }
         for (int i =0;i<nL;i++){
             label[i]=new JLabel();
             label[i].setSize(400,20);
             add(label[i]);
         }
    }
    public void start(List<String> nomi){
        outCome=-1;
        iPlayerVoting=0;
        this.nomi =nomi;
        setButtonsPosition(0);
        setVisible(true);
        setEnabled(true);
        voti=0;
    }

    protected void makeGui(List<String>testi,int py,String tipo){
         if(tipo.equals("player")) {
             for (int i = 0; i < testi.size(); i++) {
                 label[i].setText(testi.get(i));
                 label[i].setLocation(0, i * 60 + 20 + py);

             }
         }else
             for (int i = nomi.size(); i < nomi.size()+testi.size(); i++) {
                 System.out.println(testi.get(i- nomi.size()));
                 label[i].setText(testi.get(i-nomi.size()));
                 label[i].setLocation(0, i * 60 + 20 + py);

             }

    }
    protected void setButtonsPosition(int iP){
        for(int i=0;i<buttons.length;i++){
            buttons[i].setLocation(100+i*110,iP*60+10);

        }
        revalidate();
        repaint();

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
         System.out.println(iPlayerVoting);
        if(iPlayerVoting< nomi.size()) {
            voti=voti+voto;
            iPlayerVoting++;
            setButtonsPosition(iPlayerVoting);
        }
        if(iPlayerVoting== nomi.size()){
            outCome=(int)Math.floor(voti/ nomi.size());
            setVisible(false);
            setEnabled(false);
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
