import java.lang.Math;
import java.util.*;
import java.util.ArrayList;

/**
 * Write a description of class spiderWeb here.
 * 
 * @author (Juan Cancelado y Santiago Córdoba) 
 * @version (1.0)
 */
public class spiderWeb
{
    private int strands;
    private int x;
    private int y;
    private int centerX;
    private int centerY;
    private boolean isVisible;
    private lines line;
    private int radius;
    private Map<Integer, Point> linesAndCoordenates;
    private int distance;
    private Circle spot;
    private ArrayList<Circle> spots = new ArrayList<>();

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
        this.spots = new ArrayList<>();
    }
    
    //draw the spiderweb
    private void draw(){
        double radiansBetween = 2*Math.PI/strands;
        double currentAngle = 0;
        for(int i=0;i < strands;i++){
            int[] list = new int[3];
            x = (int)(centerX + radius * Math.cos(currentAngle));
            y = (int)(centerY + radius * Math.sin(currentAngle));
            lines line = new lines(centerX,centerY,x,y);
            line.makeVisible();
            currentAngle += radiansBetween;
            linesAndCoordenates.put(i + 1, new Point(x, y, currentAngle));
        }
    }
    
    /**
     * Add Bridge with color, distances and a specific strand
     */
    public void addBridge(String color, int distance, int firstStrand){
        if (distance <= 0 || distance > radius) {
        throw new IllegalArgumentException("La distancia debe ser positiva y no debe ser mayor al radio");
        }
        if(firstStrand<strands){
            lines bridge = new lines(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                           ,findCoordenateX(distance,firstStrand+1),findCoordenateY(distance,firstStrand+1),color);
            bridge.makeVisible();
        }
        else if(firstStrand == strands){
            lines bridge = new lines(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                           ,findCoordenateX(distance,1),findCoordenateY(distance,1),color);
            bridge.makeVisible();
        }
    }
    
    public void relocateBridge(String color, int distance){
        
    }
    
    public void delBridge(String color){
        
    }
    
    /**
     * Add a spot with a specific color and specific strand
     */
    public void addSpot(String color,int strand){
        int size = (int)(radius/4);
        int xPos = findCoordenateX(radius,strand)-radius/8;
        int yPos = findCoordenateY(radius,strand)- radius/8;
        Circle spot = new Circle(size,xPos,yPos,color);
        spot.makeVisible();
        spots.add(spot);
    }
    //revisar
    public void spiderSitOnStrand(int strand) {
        int xStrand = getXByIndex(strand);
        int yStrand = getYByIndex(strand);
        Spider x = new Spider();
        x.moveTo(xStrand, yStrand);
    }
    
    /**
     * Delete spot of a selected color
     */
    public void delSpot(String color) {
        for (int i = 0 ; i < spots.size() ; i++) {
            Circle spot = spots.get(i);
            if (spot.getColor().equals(color)) {
                spot.makeInvisible(); 
                spots.remove(i); 
                }
            }
    }

    
    /**
     * Make visible the spiderweb
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Static class point, save the coordenate x,y and current angle of a line
     */
    public static class Point {
        private int x;
        private int y;
        private double currentAngle;

        public Point(int x, int y, double currentAngle) {
            this.x = x;
            this.y = y;
            this.currentAngle = currentAngle;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
        
        public double getCurrentAngle(){
            return currentAngle;
        }
    }
    
    /**
     * Static class point, save the coordenate x,y and current angle of a line
     */
    public static class Bridge {
        private String color;
        private int distance;

        public Bridge(String color, int distance) {
            this.color = color;
            this.distance = distance;
        }

        public String getColor() {
            return color;
        }

        public int getDistance() {
            return distance;
        }
    }
    
    /**
     * Get coordenate x in the hashmap
     */
    private int getXByIndex(int index) {
        Point punto = linesAndCoordenates.get(index);
        if (punto != null) {
            return punto.getX();
        } else {
            throw new IllegalArgumentException("El indice " + index + " no existe en el mapa");
        }
    }
    
    /**
     * Get coordenate y in the hashmap
     */
    private int getYByIndex(int index){
        Point punto = linesAndCoordenates.get(index);
        if (punto != null){
            return punto.getY();
        } else{
            throw new IllegalArgumentException("El indice " + index + " no existe en el mapa");
        }
    }
    
    /**
     * Get angle in the hashmap
     */
    private double getAngleByIndex(int index){
        Point punto = linesAndCoordenates.get(index);
        if(punto != null){
            return punto.getCurrentAngle();
        } else{
            throw new IllegalArgumentException("El indice " + index + " no existe en el mapa");
        }
    }
    
    /**
     * Find the new coordenate x
     */
    private int findCoordenateX(int distance,int firstStrand){ 
        int x2 = (int)(centerX + distance * Math.cos(getAngleByIndex(firstStrand)));
        return x2;
    }
    
    /**
     * Find the new coordenate x
     */
    private int findCoordenateY(int distance,int firstStrand){ 
        int y2 = (int)(centerY + distance * Math.sin(getAngleByIndex(firstStrand)));
        return y2;
    }
      
    /**
     * Return the HashMap linesAndCoordenates
     */
    private Map<Integer, Point> getLinesAndCoordenates() {
        return linesAndCoordenates;
    }

    
}

