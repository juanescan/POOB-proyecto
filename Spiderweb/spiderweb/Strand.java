package spiderweb; 
import shapes.*;

/**
 * Write a description of class Strand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Strand
{
    private int x;
    private int y;
    private int centerX;
    private int centerY;
    private double currentAngle;
    private boolean isVisible;
    private int radius;
    private int strands;
    private lines strand;

    /**
     * Constructor for objects of class Strand
     */

    public Strand(int x, int y, double currentAngle,int strands, int radius) {
        this.x = x;
        this.y = y;
        this.currentAngle = currentAngle;
        this.strands = strands;
        this.radius = radius;
        centerX = 400;
        centerY = 400;
        strand = new lines(centerX,centerY,x,y);
    }
    
    /**
     * Draw the strand 
     */
    private void draw(){
        strand.makeVisible();
    }
    
    /**
     * Make invisible the strand 
     */
    public void makeInvisible(){
        strand.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Make visible the strand 
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Get the value X of one Strand
     * @return x int
     */
    public int getX() {
        return x;
    }

    /**
     * Get the value Y of one Strand
     * @return y int
     */
    public int getY() {
        return y;
    }
    
    /**
     * Get the current angle of one Strand
     * @return currentAngle int
     */
    public double getCurrentAngle(){
        return currentAngle;
    }
}

