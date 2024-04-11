package spiderweb;


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
    
    /**
     * Transform to the spot in the same strand if the bridge is delete
     */
    public void transform(spiderWeb spiderweb){
           spiderweb.addSpot(color,strand); 
    }
}


