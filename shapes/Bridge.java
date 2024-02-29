
/**
 * Write a description of class Bridge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bridge
{
    // instance variables - replace the example below with your own
    private lines puente;
    private boolean isVisible;
    /**
     * Constructor for objects of class Bridge
     */
    public Bridge(int x1, int y1, int x2, int y2, String color)
    {
        // initialise instance variables
        puente = new lines(x1,y1,x2,y2,color);
        isVisible = false;
    }
    
    private void draw(){
        puente.makeVisible();
    }
    
    private void erase(){
        puente.makeInvisible();
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    public void makeInvisible(){
        isVisible = false;
        erase();
    }
    
    public String getColor(){
        return puente.getColor();
    }
    
}
