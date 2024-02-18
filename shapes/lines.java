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
    public lines(int x1,int y1,int x2,int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.y2 = y2;
        this.x2 = x2;
        isVisible = false;
        color = "black";
    }
    
    public lines(int x1,int y1,int x2,int y2,String c){
        this.x1 = x1;
        this.y1 = y1;
        this.y2 = y2;
        this.x2 = x2;
        isVisible = false;
        color = c;
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
    
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    public void moveRight(int distance){
        x1 += distance;
        x2 += distance;
        redraw();
    }
    
    private void redraw(){
        erase();
        draw();
    }
    
    public void moveLeft(int distance){
        x1 -= distance;
        x2 -= distance;
        redraw();
    }
    
    public void up(int distance){
        y1 -= distance;
        y2 -= distance;
        redraw();
    }
    
    public void down(int distance){
        y1 += distance;
        y2 += distance;
        redraw();
    }
    
    public void center(int xCenter, int yCenter) {
        int lineCenterX = (x1 + x2) / 2;
        int lineCenterY = (y1 + y2) / 2;

 
        int dx = xCenter - lineCenterX;
        int dy = yCenter - lineCenterY;

        x1 += dx;
        x2 += dx;
        y1 += dy;
        y2 += dy;

        redraw();
    }
}
