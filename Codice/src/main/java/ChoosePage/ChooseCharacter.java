package ChoosePage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ChooseCharacter {
    String urlSprite;
    private JButton bButton=new JButton("<");
    private JButton fButton=new JButton(">");
    private Canvas image=new Canvas();

    private JPanel panel;
    int iSprite=0;
    BufferedImage urlSprites[];
    private boolean status=false;

    public ChooseCharacter(int i) {
makePanel();
      iSprite=i;
    }
    public void setUrl(  BufferedImage urlSprites[]){
        this.urlSprites=urlSprites;
    }
    private JPanel makePanel(){
        panel=new JPanel();
        bButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSprite("<");
            }
        });

        fButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changeSprite(">");
            }
        });
        image.setSize(50,50);
        panel.add(bButton);
        panel.add(image);
        panel.add(fButton);
        panel.setSize(300,50);
        return panel;
    }
    public JPanel getPanel(){
        return panel;
    }
    private void changeSprite(String type){
        if(!status)return;
        if(type.equals(">")) {
            if (iSprite < urlSprites.length - 1) {
                iSprite++;
            } else{
                iSprite = 0;}
        } else if(type.equals("<")){
            if(iSprite>0){
                iSprite--;
            }else
                iSprite=urlSprites.length-1;
        }
            System.out.println(type+iSprite);
        image.getGraphics().drawImage(urlSprites[iSprite],0,0,null );
    }
    public void setOffOn(boolean status){
        this.status=status;
    }

}
