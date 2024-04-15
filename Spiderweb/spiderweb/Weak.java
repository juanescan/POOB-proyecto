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
    
    public spiderWeb getInstanceofSpiderWeb(){
        return spiderWeb.getInstance();
    }
    
    public void delete(Map<String,Bridge>bridgesByColor,Map<String,Integer>colorAndStrand,Map<String,Bridge>unusedBridges){
        makeInvisible();
    }
    
    public void act(){
        spiderWeb spiderweb = getInstanceofSpiderWeb();
        Map<String,Bridge> bridges = spiderweb.getBridgesByColor();
        Map<String,Bridge> unused = spiderweb.getUnusedBridges();
        Map<String,Integer> colorAndStrand = spiderweb.getColorAndStrand();
        delete(bridges,colorAndStrand,unused);
    }
}
