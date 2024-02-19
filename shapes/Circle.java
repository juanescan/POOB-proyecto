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

public class Circle{

    public static final double PI=3.1416;
    
    private int diameter;
    private int xPosition;
    private int yPosition;
    private String color;
    private boolean isVisible;
    

    public Circle(){
        diameter = 30;
        xPosition = 20;
        yPosition = 15;
        color = "blue";
        isVisible = false;
    }
    
    public Circle(int x,int y){
        diameter = 50;
        xPosition = x;
        yPosition = y;
        color = "black";
        isVisible = false;
    }
    
    public Circle(int tamaño,int x,int y,String c){
        diameter = tamaño;
        xPosition = x;
        yPosition = y;
        color = c;
        isVisible = false;
    }
    
    public void moveToCoordinates(int x, int y) {
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }
    
    public void moveTo(int x,int y){
        erase();
        xPosition += x;
        yPosition += y;
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
    
    /**
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }
    
    /**
     * mover en diagonal
     */
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
    
    
    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw();
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw();
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw();
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw();
    }

    /**
     * Change the color. 
     * @param color the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /**
     * Calculate Circle area
     */
    public double area(){
        double area;
        double radio = diameter/2;
        area= PI * (radio*radio);
        return area;
        
    }
    
    /**
     *  Duplicate Circle area
     */
    public void duplicate(){
        erase();
        double area = area();
        diameter = (int) (Math.sqrt(2 * area / (PI / 4))); 
        draw();
    }
    
    /**
     *  Circle bounce
     */
    public void bounce(int times, int height){
        Random random = new Random();
        for(int i = 0; i < times; i++){
            int numeroAleatorio = random.nextInt(height);
            slowMoveVertical(numeroAleatorio);
            slowMoveVertical(-numeroAleatorio);
        }
        
    }
    
    public void center(int x,int y){
        erase();
        xPosition = x;
        yPosition = y;
        draw();
    }
    
    public void moveRight(int x){
        erase();
        xPosition += x;
        draw();
    }
    
    public void moveLeft(int x){
        erase();
        xPosition -= x;
        draw();
    }
    
    public void moveUp(int y){
        erase();
        yPosition -= y;
        draw();
    }
    
    public void moveDown(int y){
        erase();
        yPosition += y;
        draw();
    }

}
