package ChoosePage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowCharacter extends JFrame {
    private final BufferedImage[] spritesStandard;
    ChooseCharacter choose[];
    private JButton bSelect[];
    int i=0;


    public WindowCharacter(int n, String []urlSprites) throws IOException {
        setLayout(null);
        bSelect=new JButton[n];
          choose=new ChooseCharacter[n];
          this.spritesStandard = new BufferedImage[urlSprites.length];
          for(int i=0;i<urlSprites.length;i++)
              this.spritesStandard[i]= ImageIO.read(new File(urlSprites[i]));
          setSize(500,500);
      }


    public void makeChoose(){
          for(int i=0;i<4;i++){
              choose[i]=new ChooseCharacter(i);
              choose[i].getPanel().setLocation(0,i*60);
              choose[i].setUrl(spritesStandard);
              choose[i].getPanel().setFocusable(false);
              bSelect[i]=new JButton("Select");
              bSelect[i].setLocation(230,i*(60)+20);
              bSelect[i].setSize(80,20);
              add(bSelect[i]);
              add(choose[i].getPanel());
          }
      }

}
