package spiderweb;
import java.util.Map;


/**
 * Write a description of class Transformer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Transformer extends Bridge
{
    
    
    /**
     * Constructor for objects of class Transformer
     */
    public Transformer(int x1, int y1, int x2, int y2, String color, int strand, int secondStrand,int distance){
        super(x1,y1,x2,y2,color,strand,secondStrand,distance);
        this.strand = strand;
        this.color = color;
    }
    
    public spiderWeb getInstanceofSpiderWeb(){
        return spiderWeb.getInstance();
    }
    
    public void delete(Map<String,Bridge>bridgesByColor,Map<String,Integer>colorAndStrand,Map<String,Bridge>unusedBridges){
        spiderWeb spiderweb = getInstanceofSpiderWeb();
        makeInvisible();
        bridgesByColor.remove(color);
        unusedBridges.remove(color);
        colorAndStrand.remove(color);
        spiderweb.addSpot(color,strand);
    }
    
    public void act(){
        
    }
}


