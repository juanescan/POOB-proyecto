package spiderweb;
import java.util.Map;


/**
 * Write a description of class NormalB here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class NormalB extends Bridge
{


    /**
     * Constructor for objects of class NormalB
     */
    public NormalB(int x1, int y1, int x2, int y2, String color, int strand, int secondStrand,int distance){
        super(x1,y1,x2,y2,color,strand,secondStrand,distance);
    }
    
    public void delete(Map<String,Bridge>bridgesByColor,Map<String,Integer>colorAndStrand,Map<String,Bridge>unusedBridges){
        makeInvisible();  
        bridgesByColor.remove(color);
        unusedBridges.remove(color);
        colorAndStrand.remove(color);
    }
    
    public void act(){
        
    }

}
