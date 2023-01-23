package PagineSecondarie.OpenPage.ChoosePage;

import Gestione.Duck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WindowCharacter extends JFrame  {
    private final BufferedImage[] spritesStandard;
    private BufferedImage urlFinal[];
    ChooseCharacter choose[];
    private JButton bSelect;
    int i=0;
    int n;
    Canvas image;
    String[] nameFinal;


    public WindowCharacter(int n, String []urlSprites) {
        setLayout(null);
        image=new Canvas();
        image.setSize(50,250);
        setVisible(true);
        add(image);


        this.n=n;

           urlFinal=new BufferedImage[n];
           nameFinal=new String[n];
          choose=new ChooseCharacter[n];

          this.spritesStandard = new BufferedImage[urlSprites.length];
          for(int i=0;i<urlSprites.length;i++)
              try {
                  this.spritesStandard[i] = ImageIO.read(new File(urlSprites[i]));

              }catch (Exception e){
                  System.out.println("immagine non trovata");
              };
          setSize(500,500);


      }

    private void poll() throws IOException {
        urlFinal[i]=choose[i].getUrl();
        i++;
        if(i>=n){
            for(int j=0;j<n;j++)
                nameFinal[j]=choose[j].getName();
            Duck.start(urlFinal,nameFinal);
            setVisible(false);
            return;
        }
        bSelect.setLocation(230,17+60*i);
        choose[i].setOffOn(true);
        choose[i-1].setOffOn(false);


    }
    public void makeChoose(){
        bSelect=new JButton("Select");
        bSelect.setLocation(230,17);
        bSelect.setSize(80,20);
        bSelect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    poll();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        add(bSelect);
        for(int i=0;i<n;i++){
              choose[i]=new ChooseCharacter(i,image,i*60);
              choose[i].getPanel().setLocation(0,i*60+10);
              choose[i].setUrl(spritesStandard);

              add(choose[i].getPanel());

        }
        choose[0].setOffOn(true);
      }

}
