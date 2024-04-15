package Pruebas;


import spiderweb.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.Before;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Map;
import java.util.ArrayList;

/**
 * The test class spiderwebc2test.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class spiderwebc2test
{
    private spiderWeb web;
    @BeforeEach
    public void setUp() {
        web = spiderWeb.getInstance(5,150);
    }    
    
    
    //Test create the Spider
    /**
     * Test if the spider created correctly 
     */
    @Test
    public void testCreateSpiderWeb(){
        web = spiderWeb.getInstance();
        assertNotNull(web);
    }
    
    //Test that the spiderWeb was a singleton
    /**
     * Test if the two methods getInstance return the same instance 
     */
    @Test
    public void testSingletonInstance() {
        spiderWeb instance1 = spiderWeb.getInstance(10, 200);
        spiderWeb instance2 = spiderWeb.getInstance();
        assertSame(instance1, instance2);
    }
    
    /**
     * Test if the two methods getInstance return the same instance 
     */
    @Test
    public void testSameInstance() {
        spiderWeb instance1 = spiderWeb.getInstance();
        spiderWeb instance2 = spiderWeb.getInstance();
        assertSame(instance1, instance2);
    }
    
    /**
     * Try to create two instances but only create one because is a singleton 
     */
    @Test
    public void testCreateDifferentParameters() {
        spiderWeb instance1 = spiderWeb.getInstance();
        spiderWeb instance2 = spiderWeb.getInstance(10, 200);
        assertSame(instance1, instance2);
    }
    
    
    //Test Add, relocate and eliminate bridges
    
    @Test
    public void testAddBridgeInTheHashMap() {
        web = spiderWeb.getInstance();
        web.addBridge("red", 50, 1); 
        assertNotNull(web.getBridgesByColor().get("red"));
    }
    
    @Test
    public void testAddBridgeInTheHashMapTwo() {
        web = spiderWeb.getInstance();
        web.addBridge("red", 50, 1); 
        web.addBridge("blue",60,2);
        assertNotNull(web.getBridgesByColor().get("red"));
        assertNotNull(web.getBridgesByColor().get("blue"));
    }
    
    @Test
    public void testAddBridgeDistanceLessThanO() {
          
        web.addBridge("red", 0, 1); 
        assertFalse(web.ok());
    }
    
    @Test
    public void testAddBridgeDistanceMoreThanRadius() {
          
        web.addBridge("red", 2000, 1); 
        assertFalse(web.ok());
    }
    
    @Test
    public void testAddBridgefirstStrandLessThanOne(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 0); 
        assertFalse(web.ok());
    }
    
    @Test
    public void testAddBridgefirstStrandMoreThanStrands(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 8); 
        assertFalse(web.ok());
    }
    
    @Test
    public void testRelocateBridge(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 1);
        web.relocateBridge("red",30);
        assertTrue(web.ok());
    }
    
    @Test
    public void testRelocateSameDistance(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 1);
        web.relocateBridge("red",70);
        assertFalse(web.ok());

    }
    
    @Test
    public void testRelocateDontHaveThatColor(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 1);
        web.relocateBridge("blue",50);
        assertFalse(web.ok());

    }
    
    @Test
    public void testRelocateDistanceLessThanOne(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 1);
        web.relocateBridge("blue",0);
        assertFalse(web.ok());
 
    }
    
    @Test
    public void testRelocateDistanceMoreThanRadius(){
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("red", 70, 1);
        web.relocateBridge("blue",1000);
        assertFalse(web.ok());

    }
    
     @Test
    public void testDelBridge() {
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("blue", 100, 1);
        web.delBridge("blue");
        assertTrue(web.ok());
    }
    
     @Test
    public void testDelBridgeTwo() {
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("black", 100, 1);
        web.delBridge("black");
        assertTrue(web.ok());
    }
    
     @Test
    public void testDelBridgeThatDontExist() {
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("blue", 100, 1);
        web.delBridge("yellow");
        assertFalse(web.ok());
    }
    
     @Test
    public void testDelBridgeThatDontExistTwo() {
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addBridge("blue", 100, 1);
        web.delBridge("pink");
        assertFalse(web.ok());
    }
    
    
    //adicionar y eliminar spots
    @Test
    public void testAddSpot() {
        spiderWeb web = spiderWeb.getInstance(5,150);
        web.addSpot("blue", 2); 
        assertTrue(web.ok());
    }
    
    @Test
    public void testAddSpotInTheHashMap() {
        int strands = 5;
        int radius = 150;
        spiderWeb web = spiderWeb.getInstance(strands, radius);
        web.addSpot("red", 3);
        assertNotNull(web.getSpots().get("red"));
    }
    
    @Test
    public void testAddSpotColorAlreadyExists() {
        web.addSpot("red", 3); 
        web.addSpot("red", 2); 
        assertFalse(web.ok());
    }
    
    @Test
    public void testAddSpotAlreadyExistsInStrand() {
        web.addSpot("red", 3);
        web.addSpot("blue", 3); 
        assertFalse(web.ok());
    }
    
    @Test
    public void testDelSpot() {
          
        web.addSpot("red", 4);
        web.delSpot("red");
        assertTrue(web.ok());
    }
    
    @Test
    public void testDelSpotTwo(){
          
        web.addSpot("blue", 2);
        web.delSpot("blue");
        assertTrue(web.ok());
    }
    
    @Test
    public void testDelSpotColorNoExist(){
          
        web.delSpot("yellow");
        assertFalse(web.ok());
    }
    
    @Test
    public void testDelSpotColorNoExistTwo(){
          
        web.delSpot("magenta");
        assertFalse(web.ok());
    }
    
    
    //test spider sit
    
    @Test
    public void testSpiderSit(){
          
        web.spiderSit(1);
        assertNotNull(web.getSpider());
    }
    
    @Test
    public void testSpiderSitStrandLessThanZero(){
          
        web.spiderSit(0);
        assertFalse(web.ok());
    }
    
    @Test
    public void testSpiderSitStrandMoreThanStrands(){
          
        web.spiderSit(7);
        assertFalse(web.ok());
    }
    
    
    //spiderWalk
    
    @Test
    public void testSpiderWalkTrue() {
          
        web.spiderSit(1);
        web.spiderWalk(true); 
        assertTrue(web.ok());
    }
    
    @Test
    public void testSpiderWalkFalse(){
          
        web.spiderSit(1);
        web.spiderWalk(false); 
        assertTrue(web.ok());
    }
    
    @Test
    public void testSpiderWalkWithBridgesCrossBridge(){
          
        web.spiderSit(3); 
        web.addBridge("Normal", "black", 50, 3); 
        web.spiderWalk(true);
        assertTrue(web.ok()); 
    }
    
    @Test
    public void testSpiderWalkWithBridgesCrossBridgeRetrocedate(){
          
        web.spiderSit(4); 
        web.addBridge("Normal", "red", 70, 3); 
        web.spiderWalk(true); 
        assertTrue(web.ok()); 
    }
    
    @Test
    public void testSpiderWalkWithBridgesCrossBridgeBackToStart(){
          
        web.spiderSit(5); 
        web.addBridge("Normal", "pink", 70, 5); 
        web.spiderWalk(true); 
        assertTrue(web.ok()); 
    }
    
    @Test
    public void testSpiderWalkWithBridgesCrossTwoBridge(){
        web.spiderSit(1); 
        web.addBridge("Normal", "orange", 30, 1);
        web.addBridge("Normal", "cyan", 70, 2);
        web.spiderWalk(true); 
        assertTrue(web.ok()); 
    }
    
    
    //Consultar informacion de la telaraña
    @Test
    public void testSpiderLastPath() {
        web.spiderSit(4);
        web.spiderWalk(true); 
        ArrayList<Integer> lastPath = web.spiderLastPath();
        assertEquals(0, lastPath.size()); 
    }
    
    @Test
    public void testSpiderLastPathOk(){
        web.spiderSit(4);
        web.spiderWalk(true); 
        assertTrue(web.ok());
    }
    
    @Test
    public void testBridges(){
        web.addBridge("azul",40,1);
        ArrayList<String> b = web.bridges();
        assertNotNull(b);
    }
    
    @Test
    public void testBridgesOK(){
        web.bridges();
        assertTrue(web.ok());
    }
    
    @Test
    public void testBridgesSize(){
        ArrayList<String> b = web.bridges();
        assertEquals(2,b.size());
    }
    
    @Test
    public void testBridge(){
        web.bridge("azul");
        assertTrue(web.ok());
    }
    
    @Test
    public void testBridgeSize(){
        ArrayList<Integer> b = web.bridge("azul");
        assertEquals(2,b.size());
    }
    
    @Test
    public void testSpots(){
        web.addSpot("azul",3);
        ArrayList<String> s = web.spots();
        assertNotNull(s);
    }
    
     @Test
    public void testSpotsOK(){
        web.spots();
        assertTrue(web.ok());
    }
    
    @Test
    public void testSpotsSize(){
        ArrayList<String> s = web.spots();
        assertEquals(2,s.size());
    }
    
    @Test
    public void testSpot(){
        web.spot("red");
        assertTrue(web.ok());
    }
    
    @Test
    public void testSpotSize(){
        ArrayList<Integer> b = web.spot("red");
        assertEquals(1,b.size());
    }
    
    
    //hacer invisible y visible
    @Test
    public void testMakeVisible() {
        web.makeVisible();
        assertTrue(web.ok());
    }
    
    @Test
    public void testMakeInvisible() {
        web.makeInvisible();
        assertTrue(web.ok());
    }
    
     @Test
    public void testMakeVisibleAndInvisble() {
        web.makeVisible();
        web.makeInvisible();
        assertTrue(web.ok());
    }
    
    @Test
    public void testMakeInvisibleAndVisible() {
        web.makeInvisible();
        web.makeVisible();
        assertTrue(web.ok());
    }
    
    
    //SpiderWeb con la entrada del problema
    @Test
    public void testCreateSpiderWebProblem(){
        int[][] l = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        spiderWeb test =  new spiderWeb(7,6,l);
        assertFalse(web.ok());
    }
    
    @Test
    public void testCreateSpiderWebProblemNull(){
        int[][] l = {{2,1},{4,3},{6,3},{8,7},{10,5}};
        spiderWeb test =  new spiderWeb(7,6,l);
        assertNotNull(test);
    }
    
    //Adicionar una hebra
    @Test
    public void testAddStrand(){
        web.addStrand();
        assertTrue(web.ok());
    }
    
    @Test
    public void testAddStrandNumber(){
        assertEquals(6,web.getStrands());
    }
    
    
    //Ampliar una telaraña
    @Test
    public void testEnlarge(){
        web.enlarge(50);
        assertTrue(web.ok());
    }
    
    @Test
    public void testEnlargeTwoTimes(){
        web.enlarge(50);
        web.enlarge(100);
        assertTrue(web.ok());
    }
    
    //Puentes no visitados
    @Test
    public void testUnusedBridges(){
        ArrayList<String> u = web.unusedBridges();
        assertTrue(web.ok());
    }
    
    @Test
    public void testUnusedBridgesSize(){
        web.addBridge("pink",50,4);
        ArrayList<String> u = web.unusedBridges();
        assertEquals(3,u.size());
    }
}
