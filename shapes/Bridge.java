
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
    private int firstStrand;
    
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
    
    public void makeInvisible(){
        isVisible = false;
        draw();
    }
    
    public String getColor(){
        return color;
    }
    
    public void setDistance(int distance){
        double angle = Math.atan2(y2-y,x2-x);
        x2 = x + (int) (distance * Math.cos(angle));
        y2 = y + (int) (distance * Math.sin(angle));
        draw();
        
    }
    
    public void setCoordinate(int x2,int y2){
        this.x2 = x2;
        this.y2 = y2;
        draw();
    }
    
    
    
    
    
}
