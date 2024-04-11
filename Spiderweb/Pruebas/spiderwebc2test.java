package Pruebas;


import spiderweb.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.Test;

/**
 * The test class spiderwebc2test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class spiderwebc2test
{
   

    @Test
    public void testAddBridge() {
        spiderWeb web = new spiderWeb(5, 150);
        web.addBridge("red", 50, 1); // Example parameters
        assertTrue(web.ok());
    }
    
    @Test
    public void testAddSpot() {
        spiderWeb web = new spiderWeb(5, 150);
        web.addSpot("blue", 2); // Example parameters
        assertTrue(web.ok());
    }
    
    @Test
    public void testDelBridge() {
        spiderWeb web = new spiderWeb(5, 150);
        web.addBridge("blue", 100, 1);
        web.delBridge("blue");
        assertTrue(web.ok());
    }
    
    @Test
    public void testDelSpot() {
        spiderWeb web = new spiderWeb(5, 150);
        web.addSpot("red", 4);
        web.delSpot("red");
        assertTrue(web.ok());
    }
    
    @Test
    public void testSpiderWalk() {
        spiderWeb web = new spiderWeb(5, 150);
        web.spiderSit(1);
        web.spiderWalk(true); // Example parameter
        assertTrue(web.ok());
    }
    
    @Test
    public void testMakeVisible() {
        spiderWeb web = new spiderWeb(5, 150);
        web.makeVisible();
        assertTrue(web.ok());
    }
    
    @Test
    public void testMakeInvisible() {
        spiderWeb web = new spiderWeb(5, 150);
        web.makeInvisible();
        assertTrue(web.ok());
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
