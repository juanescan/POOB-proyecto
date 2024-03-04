import java.lang.Math;
import java.util.*;
import java.util.ArrayList;

/**
 * SpiderWeb.
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
    private Map<String,Spot> spots;
    private int count;
    private int radius;
    private Map<String, Bridge> bridges;
    private Spider spider;
    private int strand;
    private Map<String,Integer> colorAndStrand;
    
    /**
     * Constructor for objects of class spiderWeb
     * @param strands number of the strands of the SpiderWeb
     * @param radius the size of the SpiderWeb
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
        spots = new HashMap<>();
        int count = 0;
        bridges = new HashMap<>();
        colorAndStrand = new HashMap<>();
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
     * Add Bridge
     * @param color, the color of the bridge(the color can´t repeat)
     * @param distance is the distance to the center of the SpiderWeb
     * @param firstStrand is the initial strand where begin the bridge
     */
    public void addBridge(String color, int distance, int firstStrand){
        if (distance <= 0 || distance > radius) {
        throw new IllegalArgumentException("La distancia debe ser positiva y no debe ser mayor al radio");
        }
        if(firstStrand<strands){
            Bridge bridge = new Bridge(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                        ,findCoordenateX(distance,firstStrand+1),findCoordenateY(distance,firstStrand+1),color);
            bridge.makeVisible();
            bridges.put(color,bridge);
            colorAndStrand.put(color,firstStrand);
        }
        else if(firstStrand == strands){
            Bridge bridge = new Bridge(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                        ,findCoordenateX(distance,1),findCoordenateY(distance,1),color);
            bridge.makeVisible();
            bridges.put(color,bridge);
            colorAndStrand.put(color,firstStrand);
        }
    }
    
    /**
     * relocate the bridge of a specific color
     * @param color is to select which color bridge we will move
     * @param distance is the new distance to the center of the SpiderWeb
     */
    public void relocateBridge(String color, int distance){
        if (bridges.containsKey(color)){
            Bridge bridge = bridges.get(color);
            delBridge(color);
            int saveStrand = colorAndStrand.get(color);
            addBridge(color,distance,saveStrand);
        }
    }
    
    /**
     * remove the bridge of a specific color
     * @param color is to select which color bridge we will remove
     */
    public void delBridge(String color) { 
        Bridge bridge = bridges.get(color);
        if (bridge.getColor().equals(color)) {
            bridge.makeInvisible(); 
            bridges.remove(color); 
        }
    }

    /**
     * Add a spot with a specific color and specific strand
     * @param color is the color of the new spot (the color can´t repeat)
     * @param strand is to select in which strand the spot going to be created
     */
    public void addSpot(String color,int strand){
        int size = (int)(radius/4);
        int xPos = findCoordenateX(radius,strand)-radius/8;
        int yPos = findCoordenateY(radius,strand)- radius/8;
        Spot spot = new Spot(size,xPos,yPos,color);
        spot.makeVisible();
        spots.put(color,spot);
    }
    
    /**
     * Delete spot of a selected color
     * @param color is to select which spot remove
     */
    public void delSpot(String color) {
         
        Spot spot = spots.get(color);
        if (spot.getColor().equals(color)) {
            spot.makeInvisible(); 
            spots.remove(color); 
        }
            
    }

    /**
     *  Sit the spider in the center of SpiderWeb 
     *  @strand is to select in which strand the spider going to walk
     */
    public void spiderSit(int strand) {
        this.strand = strand;
        if(count == 0){
            spider = new Spider(radius-(radius/5),centerX-radius/9,centerY-radius/6);
            spider.makeVisible();
            count += 1;
        }else{
            spider.moveToCoordinates(centerX-radius/9,centerY-radius/6);   
        }
    }
    
    /**
     * walk through the spiderWeb
     * @advance is to determinate if the spider advance through the SpiderWeb or if retrocedate in some cases
     */
    public void spiderWalk(boolean advance){
        if (advance == true){
            int xPos = findCoordenateX(radius,strand)-radius/8;
            int yPos = findCoordenateY(radius,strand)- radius/8;
            spider.moveSlowlyToCoordinates(xPos,yPos,8);
        }else if(advance == false ){ 
            spider.moveSlowlyToCoordinates(centerX-radius/9,centerY-radius/6,8);
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
     * @param index is the number of the strand
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
     * @param index is the number of the strand
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
     * @param index is the number of the strand
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
     * @param distance is the distance of the new point 
     * @param firstStrand is to select in what strand calculare the value of coordenate x
     */
    private int findCoordenateX(int distance,int firstStrand){ 
        int x2 = (int)(centerX + distance * Math.cos(getAngleByIndex(firstStrand)));
        return x2;
    }
    
    /**
     * Find the new coordenate y
     * @param distance is the distance of the new point 
     * @param firstStrand is to select in what strand calculare the value of coordenate x
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
    
  
    public void addStrand() {
    double angleIncrement = 2 * Math.PI / strands;
    double angle = 0; 
    Set<Double> existingAngles = new HashSet<>();
    for (Strand existingStrand : strandsAndCoordenates.values()) {
        existingAngles.add(existingStrand.getCurrentAngle());
    }
    while (existingAngles.contains(angle)) {
        angle += angleIncrement;
    }
    int newX = (int)(centerX + radius * Math.cos(angle));
    int newY = (int)(centerY + radius * Math.sin(angle));
    Strand newStrand = new Strand(newX, newY, angle);
    strandsAndCoordenates.put(strandsAndCoordenates.size() + 1, newStrand);
    newStrand.makeVisible();
    strands++;
    }

    public ArrayList<Integer> countBridgesByColor(String color) {
        ArrayList<Integer> bridgeCounts = new ArrayList<>();
        for (Bridge bridge : bridges.values()) {
            if (bridge.getColor().equals(color)) {
                bridgeCounts.add(1); 
            }
        }
    return bridgeCounts;
    }

}

