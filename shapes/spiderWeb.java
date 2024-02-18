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
    private boolean isVisible;
    private lines line;
    private int radiansBetween;
    private int radius;
    

    /**
     * Constructor for objects of class spiderWeb
     */
    public spiderWeb(int threads,int radius)
    {
        this.threads = threads;
        this.radius = radius;
        x = 50;
        y = 50;
        centerX = 150;
        centerY = 150;
        isVisible = false;  
    }
    
    private void draw(){
        if(isVisible) {
            double radiansBetween = 2*Math.PI/threads;
            double currentAngle = 0;
            for(int i=0;i < threads;i++){
                x = (int)(centerX + radius * Math.cos(currentAngle));
                y = (int)(centerY + radius * Math.sin(currentAngle));
                lines line = new lines(centerX,centerY,x,y);
                line.makeVisible();
                currentAngle += radiansBetween;
            }
         }
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
}

