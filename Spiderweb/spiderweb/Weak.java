package spiderweb;
import java.util.Map;


/**
 * Write a description of class weak here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Weak extends Bridge{
    /**
     * Constructor for objects of class weak
     */
    public Weak(int x1, int y1, int x2, int y2, String color, int strand, int secondStrand,int distance){
        super(x1,y1,x2,y2,color,strand,secondStrand,distance);
    }
    
    /**
     * Destroy/delete the bridge if the spider cross it 
     * @param the bridge to destroy
     * @param Map bridgesByColor and Map colorAndStrand to eliminate from it 
     */
    public void destroy(Bridge bridge,Map<String,Bridge>bridgesByColor,Map<String,Integer>colorAndStrand){
        bridge.makeInvisible();
        String bridgeColor = bridge.getColor();
        bridgesByColor.remove(bridgeColor);
        colorAndStrand.remove(bridgeColor);
        bridge = null;
    }
}
