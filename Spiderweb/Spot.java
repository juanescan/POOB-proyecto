 


/**
 * Write a description of class Spot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spot
{
    // instance variables - replace the example below with your own
    private Circle spot;
    private boolean isVisible;

    /**
     * Constructor for objects of class Spot
     */
    public Spot(int size, int xPos, int yPos, String color)
    {
        spot = new Circle(size,xPos,yPos,color);
        isVisible = false;
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
    
}
