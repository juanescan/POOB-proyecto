package spiderweb;
import java.util.Map;


/**
 * Write a description of class Fixed here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Fixed extends Bridge
{
    /**
     * Constructor for objects of class Fixed
     */
    public Fixed(int x1, int y1, int x2, int y2, String color, int strand, int secondStrand,int distance){
        super(x1,y1,x2,y2,color,strand,secondStrand,distance);
    }
    
    public void delete(Map<String,Bridge>bridgesByColor,Map<String,Integer>colorAndStrand,Map<String,Bridge>unusedBridges){
        
    }
    
    public void act(){
        
    }
}    