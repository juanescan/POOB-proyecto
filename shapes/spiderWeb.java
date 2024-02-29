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
    private Map<Integer, Strand> strandsAndCoordenates;
    private int distance;
    private Circle spot;
    private ArrayList<Circle> spots = new ArrayList<>();
    private int count;
    private int radius;
    private Map<String, Bridge> bridges;
    
    /**
     * Constructor for objects of class spiderWeb
     */
    public spiderWeb(int strands,int radius)
    {
        this.strands = strands;
        this.radius = radius;
        x = 50;
        y = 50;
        centerX = 400;
        centerY = 400;
        isVisible = false;
        strandsAndCoordenates = new HashMap<>();
        this.spots = new ArrayList<>();
        int count = 0;
        bridges = new HashMap<>();
    }
    
    //draw the spiderweb
    private void draw(){
        double radiansBetween = 2*Math.PI/strands;
        double currentAngle = 0;
        for(int i=0;i < strands;i++){
            x = (int)(centerX + radius * Math.cos(currentAngle));
            y = (int)(centerY + radius * Math.sin(currentAngle));
            Strand strand = new Strand(x,y,currentAngle,strands,radius);
            strandsAndCoordenates.put(i + 1, new Strand(x, y, currentAngle));
            strand.makeVisible();
            currentAngle += radiansBetween;

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
            constructBridgeCase1(color, distance, firstStrand);
            bridges.put(color,new Bridge(x, y, getX2InCase1(firstStrand), getY2InCase1(firstStrand), color));
        }
        else if(firstStrand == strands){
            constructBridgeCase2(color, distance, firstStrand);
            bridges.put(color, new Bridge(x, y, getX2InCase2(), getY2InCase2(), color));
        }
    }
    
    /**
     * relocate the bridge
     */
    public void relocateBridge(String color, int distance){
        if (bridges.containsKey(color)){
            Bridge puente = bridges.get(color);
            puente.setDistance(distance);
            delBridge(color);
        }
        }
    
    /**
     * remove the bridge
     */
    public void delBridge(String color){ 
            Bridge puente = bridges.get(color);
            if (puente.getColor().equals(color)) {
                puente.makeInvisible(); 
                bridges.remove(color); 
            }
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
     *  Sit the spider in a specific strand
     */
    public void spiderSit(int strand) {
        int size = (int)(radius/4);
        int xStrand = findCoordenateX(radius,strand)-radius/8;
        int yStrand = findCoordenateY(radius,strand)-radius/8;
        if(count == 0){
            Spider spider = new Spider(radius,xStrand,yStrand);
            spider.makeVisible();
            count += 1;
        }
        else{
            throw new IllegalArgumentException("Solo se puede posicionar una araña en un strand.");
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
     * Get coordenate x in the hashmap
     */
    private int getXByIndex(int index) {
        Strand punto = strandsAndCoordenates.get(index);
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
        Strand punto = strandsAndCoordenates.get(index);
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
        Strand punto = strandsAndCoordenates.get(index);
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
    private Map<Integer, Strand> getStrandsAndCoordenates() {
        return strandsAndCoordenates;
    }
    
    private void constructBridgeCase1(String color, int distance, int firstStrand){
        Bridge bridge = new Bridge(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                        ,findCoordenateX(distance,firstStrand+1),findCoordenateY(distance,firstStrand+1),color);
        bridge.makeVisible();
    }
    
    private void constructBridgeCase2(String color, int distance, int firstStrand){
        Bridge bridge = new Bridge(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                        ,findCoordenateX(distance,1),findCoordenateY(distance,1),color);
        bridge.makeVisible();
    }
    
    private int getX2InCase1(int firstStrand){
        return findCoordenateX(distance,firstStrand+1);
    }
    
    private int getY2InCase1(int firstStrand){
        return findCoordenateY(distance,firstStrand+1);
    }
    
    private int getX2InCase2(){
        return findCoordenateX(distance,1);
    }
    
    private int getY2InCase2(){
        return findCoordenateY(distance,1);
    }
}

