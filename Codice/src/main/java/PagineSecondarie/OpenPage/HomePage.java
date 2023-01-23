package PagineSecondarie.OpenPage;

import Gestione.Duck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage extends JFrame {
    private final JButton bstart;
    private final JButton binfo;
    private final JButton besci;

    public HomePage(){
        setSize(500,500);
        setLayout(null);
        JLabel title=new JLabel("Duck Game");
        title.setFont(new Font("Serif", Font.PLAIN, 30));
        title.setSize(200,50);
        title.setLocation(100,0);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.bstart = addButton(70,100,180,80,"Start");

        this.binfo = addButton(320,170,150,60,"Info");

        this.besci = addButton(30,270,100,50,"esci");
        add(title);
        setVisible(true);
        setListener();
    }
    private JButton addButton(int px,int py,int dx,int dy,String testo){
        JButton b= new JButton(testo);
        b.setSize(dx,dy);
        b.setLocation(px,py);
        add(b);
        return b;
    }
    private void setListener(){
        bstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                   Duck.sceltaPersonaggi();
                   setVisible(false);
            }
        });
        besci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        binfo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
    }

}
