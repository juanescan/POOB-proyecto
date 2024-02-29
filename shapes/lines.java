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
    
    /**
     * draw the object
     */
    private void draw(){
        Canvas canvas = Canvas.getCanvas();
        int[] xpoints = { x1, x2};
        int[] ypoints = { y1, y2};
        canvas.draw(this, color, new Polygon(xpoints, ypoints, 2));
        canvas.wait(10);
    }
    
    /**
     * makes the object visible
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * 
    makes the object invisible
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }
    
    /**
     * 
     */
    public void moveTo(int x,int y){
        erase();
        x1 += x;
        x2 += x;
        y1 += y;
        y2 += y;
        draw();
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
   
    public void moveDiagonally(int x,int y){
        for(int i = 0; i < Math.max(x,y) ; i++){
            if (i < x){
                slowMoveHorizontal(1);
            }
            if (i < y){
                slowMoveVertical(1);
            }
            
        }
        
    }
    
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            y1+= delta;
            y2 += delta;
            draw();
        }
    }
    
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            x1 += delta;
            x2 += delta;
            draw();
        }
    }
    
    public void setDistance(int distance){
        double angle = Math.atan2(y2-y1,x2-x1);
        x2 = x1 + (int) (distance * Math.cos(angle));
        y2 = y1 + (int) (distance * Math.sin(angle));
        draw();
        
    }
    
    public void setCoordinate(int x2,int y2){
        this.x2 = x2;
        this.y2 = y2;
        draw();
    }
    
    public String getColor(){
        return color;
    }
    
}
