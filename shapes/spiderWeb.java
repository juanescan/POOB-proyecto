import java.lang.Math;
import java.util.*;

/**
 * Write a description of class spiderWeb here.
 * 
 * @author (Juan Cancelado y Santiago Córdoba) 
 * @version (1.0)
 */
public class spiderWeb
{
    // instance variables - replace the example below with your own
    private int strands;
    private int x;
    private int y;
    private int centerX;
    private int centerY;
    private boolean isVisible;
    private lines line;
    private int radiansBetween;
    private int radius;
    private Map<Integer, Point> linesAndCoordenates;
    
    /**
     * Constructor for objects of class spiderWeb
     */
    public spiderWeb(int strands,int radius)
    {
        this.strands = strands;
        this.radius = radius;
        x = 50;
        y = 50;
        centerX = 150;
        centerY = 150;
        isVisible = false;
        linesAndCoordenates = new HashMap<>();
    }
    
    private void draw(){
        if(isVisible) {
            double radiansBetween = 2*Math.PI/strands;
            double currentAngle = 0;
            for(int i=0;i < strands;i++){
                int[] list = new int[3];
                x = (int)(centerX + radius * Math.cos(currentAngle));
                y = (int)(centerY + radius * Math.sin(currentAngle));
                lines line = new lines(centerX,centerY,x,y);
                line.makeVisible();
                currentAngle += radiansBetween;
                linesAndCoordenates.put(i + 1, new Point(x, y));
            }
         }
    }
    
    public void addBridge(String color, int distance, int firstStrand){
        
    }
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
    
    public int getXByIndex(int index) {
        Point punto = linesAndCoordenates.get(index);
        if (punto != null) {
            return punto.getX();
        } else {
            throw new IllegalArgumentException("El indice " + index + " no existe en el mapa");
        }
    }
    
    public int getYByIndex(int index){
        Point punto = linesAndCoordenates.get(index);
        if (punto != null){
            return punto.getY();
        } else{
            throw new IllegalArgumentException("El indice " + index + " no existe en el mapa");
        }
    }
    
    

}

