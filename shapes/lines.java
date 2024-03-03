import java.awt.*;
/**
 * lines.
 * 
 * @author (Juan Cancelado y Santiago Córdoba) 
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
     * Constructor for objects of class lines (use to create strands)
     * @param x1 the x part of the first Coordenate of the bridge
     * @param y1 the y part of the first Coordenate of the bridge
     * @param x2 the x part of the second Coordenate of the bridge
     * @param y1 the y part of the seconf Coordenate of the bridge
     */
    public lines(int x1,int y1,int x2,int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.y2 = y2;
        this.x2 = x2;
        isVisible = false;
        color = "black";
    }

    /**
     * Constructor for objects of class lines (use to create bridges)
     * @param x1 the x part of the first Coordenate of the bridge
     * @param y1 the y part of the first Coordenate of the bridge
     * @param x2 the x part of the second Coordenate of the bridge
     * @param y1 the y part of the seconf Coordenate of the bridge
     * color of the line
     */
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
     * makes the object invisible
     */
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
    
    public String getColor(){
        return color;
    }
    
}
