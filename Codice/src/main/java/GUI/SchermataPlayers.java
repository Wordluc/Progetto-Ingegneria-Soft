package GUI;

import javax.swing.*;

public class SchermataPlayers extends JFrame  {
    JLabel label[][];

    public SchermataPlayers(int n){
        label=new JLabel[n+1][2];//n per player ,+1 per il dado
        setLayout(null);
        setSize(400,400);
        makeLabel(n);
        setVisible(true);
    }
    private void makeLabel(int n){
        for(int i=0;i<n+1;i++){
            label[i][0]=new JLabel();
            label[i][0].setSize(400,20);
            label[i][0].setLocation(0,i*40);
            add(label[i][0]);

            label[i][1]=new JLabel();
            label[i][1].setSize(400,20);
            label[i][1].setLocation(0,i*40+20);
            add(label[i][1]);
        }
    }
    public void setLabel(int i,String info[]){
        label[i][0].setText(info[0]);
        label[i][1].setText(info[1]);
    }
}
