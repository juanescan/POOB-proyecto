
/**
 * Write a description of class Bridge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bridge
{
    // instance variables - replace the example below with your own
    private lines bridge;
    private boolean isVisible;
    
    /**
     * Constructor for objects of class Bridge
     */
    public Bridge(int x1, int y1, int x2, int y2, String color)
    {
        // initialise instance variables
        bridge = new lines(x1,y1,x2,y2,color);
        isVisible = false;
    }
    
    
    public void makeVisible(){
        bridge.makeVisible();
        isVisible = true;
    }
    
    public void makeInvisible(){
        bridge.makeInvisible();
        isVisible = false;
    }
    
    public String getColor(){
        return bridge.getColor();
    }
    
    public void setDistance(int distance){
        bridge.setDistance(distance);
    }
    
}
