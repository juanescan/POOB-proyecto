
/**
 * Write a description of class Bridge here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bridge
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private  int x2;
    private int y2;
    private String color;
    private boolean isVisible;
    
    /**
     * Constructor for objects of class Bridge
     */
    public Bridge(int x, int y, int x2, int y2, String color)
    {
        // initialise instance variables
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
        isVisible = false;
        this.color = color;
    
    }
    
    private void draw(){
        lines line = new lines(x,y,x2,y2,color);
        line.makeVisible();
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
}
