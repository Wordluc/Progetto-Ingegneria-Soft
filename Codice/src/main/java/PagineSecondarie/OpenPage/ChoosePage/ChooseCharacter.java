package PagineSecondarie.OpenPage.ChoosePage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class ChooseCharacter {
    private JButton bButton=new JButton("<");
    private JButton fButton=new JButton(">");
    private Canvas image;

    private JPanel panel;
    int iSprite=0;
    BufferedImage urlSprites[];
    JTextField name;
    private boolean status=false;
    private int y;

    public ChooseCharacter(int i, Canvas image, int y) {
        makePanel();
        iSprite=i;
        this.y=y;
        this.image=image;
        panel=new JPanel();
        name=new JTextField();
        name.setColumns(5);
        name.setVisible(true);
        panel.add(bButton);
        panel.add(fButton);
        panel.add(name);
        panel.setSize(300,50);

    }

    public void setUrl(  BufferedImage urlSprites[]){
        this.urlSprites=urlSprites;
        draw();
    }
    public BufferedImage getUrl(){
        return urlSprites[iSprite];
    }
    private JPanel makePanel(){
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



        return panel;
    }
    public String getName(){
        return name.getText();
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

       draw();
    }
    public void draw(){
            image.getGraphics().drawImage(urlSprites[iSprite], 0, y, null);
    }
    public void setOffOn(boolean status){
        this.status=status;
    }

}
