import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

public class nicknameTest {
    nickname nik;
    @Test
    public void testPlayedClassic() throws IOException {
        nik=new nickname();
        nik.name= "Loukritia";
        boolean expR=false;
        boolean result=nik.playedClassic(1);
        assertEquals( expR,result );
    }
    @Test
    public void testPlayedKiller() throws IOException{
        nik=new nickname("Loukritia");
        nik.name= "Loukritia";
        boolean expR=true;
        boolean result=nik.playedKiller(19);
        assertEquals(expR,result);
    }
    @Test
    public void testGiveScores(){
        nik=new nickname("Loukritia");
        int[] expResult=new int[2];
        expResult[0]=1;
        expResult[1]=2;
        int[] result=nik.giveScore("Loukritia");
        assertArrayEquals(expResult,result);

    }


}
