package PagineSecondarie.ChoosePage;

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


        try {
            this.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("codice\\resource\\homepagebk.jpg")))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setLayout(null);
        image=new Canvas();
        image.setSize(50,250);
        setVisible(true);
        add(image);


        this.n=n;

           urlFinal=new BufferedImage[n];
           nameFinal=new String[n];
          choose=new ChooseCharacter[n]; //

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
        if(!choose[i].getName().isEmpty()) {
            i++;
            if(i>=n){
                for(int j=0;j<n;j++)
                    nameFinal[j]=choose[j].getName();
                Duck.start(urlFinal,nameFinal);
                setVisible(false);
                return;
            }
            //--- Barra seleziona
            bSelect.setLocation((this.getWidth()-bSelect.getWidth())/2, 137 + 60 * i);

            choose[i].setOffOn(true);
            choose[i - 1].setOffOn(false);
        }




    }
    public void makeChoose(){
        Font edgeFont = null;
        try {
            edgeFont = Font.createFont(Font.TRUETYPE_FONT, new File("codice\\resource\\edgefont.ttf"));
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        bSelect=new JButton("SELEZIONA");
        bSelect.setFont(edgeFont.deriveFont(20f));
        bSelect.setForeground(new Color(154,252,151));


        bSelect.setSize(153,20);
        bSelect.setLocation((this.getWidth()-bSelect.getWidth())/2,137);
        bSelect.setBorderPainted(false);
        bSelect.setContentAreaFilled(false);
        bSelect.setFocusPainted(false);
        bSelect.setOpaque(false);
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
        //--- posizione
        for(int i=0;i<n;i++){
              choose[i]=new ChooseCharacter(i,image,i*60);
              choose[i].getPanel().setLocation((this.getWidth()-choose[i].getPanel().getWidth())/2,i*60+100);
              choose[i].setUrl(spritesStandard);

              add(choose[i].getPanel());

        }
        choose[0].setOffOn(true);
      }

}
