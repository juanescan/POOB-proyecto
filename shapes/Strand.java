
/**
 * Write a description of class Strand here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Strand
{
    // instance variables - replace the example below with your own
    private int x;
    private int y;
    private int centerX;
    private int centerY;
    private double currentAngle;
    private boolean isVisible;
    private int radius;
    private int strands;

    /**
     * Constructor for objects of class Strand
     */

    public Strand(int x, int y, double currentAngle) {
        this.x = x;
        this.y = y;
        this.currentAngle = currentAngle;
        centerX = 400;
        centerY = 400;
    }
    
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
    }
    
    private void draw(){
        lines line = new lines(centerX,centerY,x,y);
        line.makeVisible();
    }
    
    /**
     * Borra la representación visual del Strand.
     */
    private void erase() {
        lines line = new lines(centerX, centerY, x, y);
        line.makeInvisible();   
    }
    
    public void makeInvisible(){
        isVisible = false;
        erase();
    }
    
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

