package Test;

import Mappa.GestoreMappa;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.IOException;


public class MappaTest {
    @Test
    public void testGetInstant() throws IOException {
        GestoreMappa m=GestoreMappa.getInstance(40,"resource\\Eventi.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");
        assertNotNull(m);
    }
    @Test
    public void testStep() throws IOException {
        GestoreMappa m=GestoreMappa.getInstance(40,"resource\\Eventi.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");

        assertNotEquals(m.generaSteps(1).getDesc(),"finito");//dentro mappa
        assertEquals(m.generaSteps(41).getDesc(),"finito");//fuori mappa
    }
    @Test
    public void testCaselle() throws IOException {
        GestoreMappa m=GestoreMappa.getInstance(40,"resource\\Eventi.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");
        assertNotNull(m.getCaselle(1));
        assertNull(m.getCaselle(41));


    }
    @Test
    public void testMakeMappa()throws IOException {
        GestoreMappa m=GestoreMappa.getInstance(40,"resource\\Eventi.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");
        assertEquals(m.genMatrix(2,2),new String[]{"0,0","1,0","1,1","0,1"});

    }
    @Test
    public void testDefaultStep()throws IOException {
        GestoreMappa m=GestoreMappa.getInstance(40,"resource\\Eventi.txt",20,"resource\\sprite\\vuoto.png","resource\\sprite\\pieno.png");
        assertEquals(m.getDefaultSteps().getDesc(),"movimento");

    }


}
