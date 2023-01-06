package OpenPage;

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


        this.bstart = new JButton("Start");
        bstart.setSize(180,80);
        bstart.setLocation(70,100);
        add(bstart);

        this.binfo = new JButton("Info");
        binfo.setSize(150,60);
        binfo.setLocation(320,170);
        add(binfo);

        this.besci = new JButton("Esci");
        besci.setSize(100,50);
        besci.setLocation(30,270);
        add(besci);
        add(title);
        setVisible(true);
        setListener();
    }
    private void setListener(){
        bstart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
