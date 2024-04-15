package Pruebas;
import spiderweb.*;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class spiderwebc4test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class spiderwebc4test
{
   private spiderWeb web;
    @BeforeEach
    public void setUp() {
        web = spiderWeb.getInstance(5,150);
    } 

   @Test
    public void testAddBridgeInTheHashMap() {
        web = spiderWeb.getInstance();
        web.addBridge("red", 50, 1); 
        assertNotNull(web.getBridgesByColor().get("red"));
    }
}
