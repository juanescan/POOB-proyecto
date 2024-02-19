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
    private int radius;
    private Map<Integer, Point> linesAndCoordenates;
    private int distance;
    private int len;
    
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
    
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
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
        
        public void setDistance(int distance) {
            this.distance = distance;
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
    
    public double getAngleByIndex(int index){
        Point punto = linesAndCoordenates.get(index);
        if(punto != null){
            return punto.getCurrentAngle();
        } else{
            throw new IllegalArgumentException("El indice " + index + " no existe en el mapa");
        }
    }
    
    public int findCoordenateX(int distance,int firstStrand){ 
        int x2 = (int)(centerX + distance * Math.cos(getAngleByIndex(firstStrand)));
        return x2;
    }
    
    public int findCoordenateY(int distance,int firstStrand){ 
        int y2 = (int)(centerY + distance * Math.sin(getAngleByIndex(firstStrand)));
        return y2;
    }
    
        public Map<Integer, Point> getLinesAndCoordinates() {
        return linesAndCoordenates;
    }

    public void moveCircleAlongWeb(Circle circle, int webIndex) {
    Map<Integer, Point> coordinates = getLinesAndCoordinates();
    if (webIndex >= 1 && webIndex <= coordinates.size()) {
        Point point = coordinates.get(webIndex);
        circle.moveToCoordinates(point.getX(), point.getY());
    } else {
        System.out.println("Índice de telaraña fuera de rango.");
    }
    }
    
    public void moveRectangleAlongWeb(Rectangle rectangle, int webIndex) {
    Map<Integer, Point> coordinates = getLinesAndCoordinates();
    if (webIndex >= 1 && webIndex <= coordinates.size()) {
        Point point = coordinates.get(webIndex);
        rectangle.moveToCoordinates(point.getX(), point.getY());
    } else {
        System.out.println("Índice de telaraña fuera de rango.");
    }
    }
    
    //chatgpt
    public void movelinesAlongWeb(lines line, int webIndex) {
    Map<Integer, Point> coordinates = getLinesAndCoordinates();
    if (webIndex >= 1 && webIndex <= coordinates.size()) {
        Point point = coordinates.get(webIndex);
        line.center(point.getX(), point.getY());
    } else {
        System.out.println("Índice de telaraña fuera de rango.");
    }
    } 
    
    
    

}

