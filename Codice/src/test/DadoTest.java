import Gestione.Dado;
import org.junit.Test;
import static org.junit.Assert.*;
public class DadoTest {
    @Test
    public void DadoTest(){
        Dado d=new Dado();
        int a;
        for(int i=0;i<100;i++){
            a=d.lanciaDado();
            assertTrue(a>0 && a<7);
        }
    }
}
