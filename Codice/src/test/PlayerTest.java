import Entita.Player;
import Entita.Strategy.printNome;
import Entita.Strategy.printTot;
import Gestione.resStep;
import Mappa.GestoreMappa;
import org.junit.Test;
import java.io.IOException;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class PlayerTest {
   @Test
    public void testMovimento() throws IOException {
       Player p=new Player("1",null,0,0);
       GestoreMappa.size=3;

       assertEquals(p.movimento(2),true);//non ho finito
       assertEquals(p.getPosizione(),2);
   }
    @Test
    public void testOut() throws IOException {
        Player p=new Player("1",null,0,0);
        GestoreMappa.size=3;

        assertEquals( p.movimento(5),false);//ho finito
    }
    @Test
    public void Teststeps() throws IOException {
        Player p=new Player("1",null,0,0);
        resStep r=new resStep();
        LinkedList<String> l=new LinkedList<>();
        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        r.setDesc("prova");
        r.setSteps(l);
        p.setStep(r);
        assertEquals(l,r.getStep());
        p.setStep(r);
        assertEquals(p.getStep(),r);
    }
    @Test
    public void TestStepMultipli() throws IOException {
        Player p=new Player("1",null,0,0);
        resStep r=new resStep();
        LinkedList<String> l=new LinkedList<>();

        l.add("1");
        l.add("2");
        l.add("3");
        l.add("4");
        r.setDesc("prova");
        r.setSteps(l); //creazione step

        p.setStep(r);
        for(int i=0;i<4;i++){
            assertEquals(p.getCurrentStep(),r.getStep(i));
            p.incrIstep();
        }
    }
    @Test
    public void TestDinamicOut() throws IOException {
        Player p = new Player("1", null, 0, 0);
        p.movimento(2);
        p.movimento(4);
        p.setPrint(new printTot());
        assertEquals(p.toString(),"nome 1,posizione 6,step null,0");
        p.setPrint(new printNome());
        assertEquals(p.toString(),"1");
    }
}
