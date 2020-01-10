package i18n;
import org.junit.Test;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
public class InterTest {
    Internationalization inna;

    @Test
    public void testGetTranslatedMessage() {
        inna = new Internationalization( "el", "GR" );
        String expResult = "Νέο Παιχνίδι";
        String result = inna.getTranslatedMessage( "New" );
        assertEquals( expResult, result );
    }
}


