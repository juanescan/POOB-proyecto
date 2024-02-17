import java.awt.*;
/**
 * Write a description of class lines here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class lines
{
    private int x1;
    private int y1;
    private int y2;
    private int x2;
    private String color;
    private boolean isVisible;

    /**
     * Constructor for objects of class lines
     */
    public lines(int x1,int y1,int y2,int x2){
        this.x1 = x1;
        this.y1 = y1;
        this.y2 = y2;
        this.x2 = x2;
        isVisible = false;
        color = "black";
    }
    
    private void draw(){
        Canvas canvas = Canvas.getCanvas();
        int[] xpoints = { x1, x2};
        int[] ypoints = { y1, y2};
        canvas.draw(this, color, new Polygon(xpoints, ypoints, 2));
        canvas.wait(10);
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
}
