package shapes;
import java.awt.*;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified)
 * @version 1.0  (15 July 2000)()
 */


 
public class Rectangle{

    public static byte EDGES = 4;
    
    private int height;
    private int width;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "magenta";
        isVisible = false;
    }
    
    public Rectangle(int altura,int ancho,int x,int y,String c){
        height = altura;
        width = ancho;
        xPosition = x;
        yPosition = y;
        color = c;
        isVisible = false;
        
    }
    
    public void moveToCoordenates(int x, int y) {
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }
    
    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /*
     * Draw the rectangle with current specifications on screen.
     */

    private void draw() {
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height));
            canvas.wait(10);
        }
    }

    /*
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
    
    public int getXPosition(){
        return xPosition;
    }
    
    public int getYPosition(){
        return yPosition;
    }
    
    public int getWidth(){
        return width;
    }
    
    public int getHeight(){
        return height;
    }

    public void Coordenadas(int x,int y){
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }
}

