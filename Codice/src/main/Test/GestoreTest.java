package Test;

import static org.junit.Assert.*;

import Entita.Player;
import Gestione.Gestore;
import Mappa.GestoreMappa;
import org.junit.Test;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class GestoreTest {
    @Test
    public void testGetInstant() throws IOException {//singleton
        Gestore g;
        GestoreMappa m=GestoreMappa.getInstance(0,"resource\\EventiT.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");
        g=Gestore.getInstance(m,new String[]{"1","2","3","4"},new BufferedImage[]{null,null,null,null});
        assertNotNull(g);
    }
    @Test
    public void testPlayer() throws IOException {
        Gestore g=null;
        GestoreMappa m=GestoreMappa.getInstance(0,"resource\\EventiT.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");
        Player p=new Player("",null,0,0);
        g=Gestore.getInstance(m,new String[]{"1","2","3","4"},new BufferedImage[]{null,null,null,null});
        for(int i=0;i<4;i++) {
            assertNotNull(g.getPlayer(i));

        }

        List<String> l=g.getPlayerName("");
        List<String> oracol=new LinkedList<>();
        oracol.add("1");
        oracol.add("2");
        oracol.add("3");
        oracol.add("4");
        assertEquals(l,oracol);
    }

}
