import java.lang.Math;
/**
 * Write a description of class spiderWeb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class spiderWeb
{
    // instance variables - replace the example below with your own
    private int threads;
    private int x;
    private int y;
    private int centerX;
    private int centerY;
    private int radians;
    private boolean isVisible;
    private lines line;
    private int lon;
    

    /**
     * Constructor for objects of class spiderWeb
     */
    public spiderWeb(int threads)
    {
        this.threads = threads;
        x = 50;
        y = 50;
        centerX = 150;
        centerY = 150;
        radians = (int)((360/threads)*Math.PI/180);
        isVisible = false;
        lon = (int)(Math.sqrt((Math.pow((x-centerX),2)+Math.pow((y-centerY),2))));
        
        
    }
    
    private void draw(){
        if(isVisible) {
            for(int i=0;i < threads;i++){
                lines line = new lines(x,y,40,40);
                line.makeVisible();
                x += 150;
                y += 150;
            }
         }
    }
    
    public int getLon(){
        return lon;
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
}

