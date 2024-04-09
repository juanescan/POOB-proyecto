package spiderweb; 
import shapes.*;


/**
 * Write a description of class Spot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spot
{
    // instance variables - replace the example below with your own
    protected Circle spot;
    protected boolean isVisible;
    protected int size;
    protected int xPos;
    protected int yPos;
    protected String color;
    protected int strand;

    /**
     * Constructor for objects of class Spot
     */
    public Spot(int size, int xPos, int yPos, String color, int strand)
    {
        spot = new Circle(size,xPos,yPos,color);
        this.strand = strand;
        isVisible = false;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    /**
     * Make visible the spot
     */
    public void makeVisible(){
        spot.makeVisible();
        isVisible = true;
    }
    
    /**
     * Make invisible the spot
     */
    public void makeInvisible(){
        spot.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Get the color of the spot
     * @return color
     */
    public String getColor(){
        return spot.getColor();
    }
    
    /**
     * Get the x coordenate of the spot
     */
    public int getX(){
        return xPos;
    }
    
     /**
     * Get the Y coordenate of the spot
     */
    public int getY(){
        return yPos;
    }
    
    public int getSize(){
        return size;
    }
}
