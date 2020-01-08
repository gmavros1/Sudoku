import org.junit.Test;
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
        boolean result=nik.playedClassic(19);
        assertEquals( expR,result );
    }
    @Test
    public void testPlayedKiller() throws IOException{
        nik=new nickname();
        nik.name= "Loukritia";
        boolean expR=false;
        boolean result=nik.playedKiller(19);
        assertEquals( expR,result );
    }
    @Test
    public void testNewDataClassic(){
        nik=new nickname("Loukritia");


    }
}
