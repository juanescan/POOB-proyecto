package shapes;
import java.awt.*;
import java.awt.geom.*;
import java.util.Random;
import javax.swing.*;
import java.util.Timer;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle {

    public static final double PI=3.1416;
    
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    
    public Circle(int tamaño,int x,int y,String c){
        diameter = tamaño;
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
     * hace visible el objeto
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * hace invisible el objeto
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, color, 
                new Ellipse2D.Double(xPosition, yPosition, 
                diameter, diameter));
            canvas.wait(10);
        }
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
    
    public int getXPosition(){
        return xPosition;
    }
    
    public int getYPosition(){
        return yPosition;
    }

    public int getDiameter(){
        return diameter;
    }
}
