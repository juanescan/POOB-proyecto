 

import java.lang.Math;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JFrame;


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
    private int favorite;
    private boolean isVisible;
    private Map<Integer, Strand> strandsAndCoordenates;
    private int distance;
    private Map<String,Spot> spots;
    private int radius;
    private Map<String, Bridge> bridgesByColor;
    private Spider spider;
    private int strand;
    private Map<String,Integer> colorAndStrand;
    private boolean okay;
    private List<Integer> spiderMovements;
    private int countBridges;
    private Map<Integer, lines> spiderpaths;
    private int index;
    private Map<String, Bridge> unusedBridges;
    private int[][] bridges;
    private boolean isProblem;
    
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
        bridgesByColor = new HashMap<>();
        unusedBridges = new HashMap<>();
        colorAndStrand = new HashMap<>();
        okay = true;
        spiderMovements = new ArrayList<>();
        countBridges = 1;
        spiderpaths = new HashMap<>();
        index = 1;
        isProblem = false;
        makeVisible();
    }
    
    public spiderWeb(int strands, int favorite, int[][] bridges){
        this.strands = strands;
        this.favorite = favorite;
        this.bridges = bridges;
        radius = 120;
        centerX = 400;
        centerY = 400;
        strandsAndCoordenates = new HashMap<>();
        unusedBridges = new HashMap<>();
        spots = new HashMap<>();
        bridgesByColor = new HashMap<>();
        colorAndStrand = new HashMap<>();
        isProblem = true;
        makeVisible();
    }
    
    private void drawForProblem(){
        double radiansBetween = 2*Math.PI/strands;
        double currentAngle = 0;
        for(int i=0;i < strands;i++){
            x = calculateX(currentAngle);
            y = calculateY(currentAngle);
            Strand strand = new Strand(x,y,currentAngle,strands,radius);
            strandsAndCoordenates.put(i + 1, strand);
            strand.makeVisible();
            currentAngle += radiansBetween;
        }
        int filas = bridges.length;
        int columnas = bridges[0].length;
        for(int i  = 0; i < filas ; i++ ){
            int dist = bridges[i][0];
            int fStrand = bridges[i][1];
            addBridge("black",dist,fStrand);
        }
        okay = true;
    }
    
    private void draw(){
        double radiansBetween = 2*Math.PI/strands;
        double currentAngle = 0;
        for(int i=0;i < strands;i++){
            x = calculateX(currentAngle);
            y = calculateY(currentAngle);
            Strand strand = new Strand(x,y,currentAngle,strands,radius);
            strandsAndCoordenates.put(i + 1, strand);
            strand.makeVisible();
            currentAngle += radiansBetween;
        }
        okay = true;
    }
    
    /**
     * Add Bridge
     * @param color, the color of the bridge(the color can´t repeat)
     * @param distance is the distance to the center of the SpiderWeb
     * @param firstStrand is the initial strand where begin the bridge
     */
    public void addBridge(String color, int distance, int firstStrand){
        if (distance <= 0 || distance > radius) {
            okay = false;
            System.out.println("La distancia debe ser positiva y no debe ser mayor al radio");
        }
        if(firstStrand<strands){
            createBridgeCase1(color,distance,firstStrand);
            countBridges++;
        }
        else if(firstStrand == strands){
            createBridgeCase2(color,distance,firstStrand);
            countBridges++;
        }
    }
    
    /**
     * relocate the bridge of a specific color
     * @param color is to select which color bridge we will move
     * @param distance is the new distance to the center of the SpiderWeb
     */
    public void relocateBridge(String color, int distance){
        if (bridgesByColor.containsKey(color)){
            Bridge bridge = bridgesByColor.get(color);
            delBridge(color);
            int saveStrand = colorAndStrand.get(color);
            addBridge(color,distance,saveStrand);
            okay = true;
        }
    }
    
    /**
     * remove the bridge of a specific color
     * @param color is to select which color bridge we will remove
     */
    public void delBridge(String color) { 
        Bridge bridge = bridgesByColor.get(color);
        if (bridge.getColor().equals(color)) {
            bridge.makeInvisible(); 
            bridgesByColor.remove(color);
            okay = true;
        }
    }

    /**
     * Add a spot with a specific color and specific strand
     * @param color is the color of the new spot (the color can´t repeat)
     * @param strand is to select in which strand the spot going to be created
     */
    public void addSpot(String color,int strand){
        int size = (int)(radius/4);
        int xPos = findCoordenateX(radius,strand);
        int yPos = findCoordenateY(radius,strand);
        if(spots.containsKey(color)){
            System.out.println("El color" + color + "ya existe, seleccione otro color");
            okay = false;
        }
        else{Spot spot = new Spot(size,xPos,yPos,color);
            spot.makeVisible();
            spots.put(color,spot);
            okay = true;
        }
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
            okay = true;
        }
    }

    /**
     *  Sit the spider in the center of SpiderWeb 
     *  @strand is to select in which strand the spider going to walk
     */
    public void spiderSit(int strand) {
        this.strand = strand;
        if(strand > strands){
            okay = false;
            System.out.println("El strand indicado no existe");
        }
        if(spider == null){
            spider = new Spider(radius-(radius/5),centerX,centerY);
        }
        spider.makeVisible();
        spider.move(centerX,centerY); 
        deletePath();
        okay = true;
    }
    
    /**
     * walk through the spiderWeb
     * @advance is to determinate if the spider advance through the SpiderWeb or if retrocedate in some cases
     */
    public void spiderWalk(boolean advance){
        String bridgeColor = bridgeColorToMove(strand);
        int dirToMove = bridgeDir(strand);
        Bridge brid = bridgesByColor.get(bridgeColor);
        Strand stra = strandsAndCoordenates.get(strand);
        for(int i = 0; i < countBridges; i++){
            if(advance && !spider.spiderInAPosition(stra.getX(),stra.getY())){
                spiderWalkTrue();
            }else if(!advance && !spider.spiderInAPosition(centerX,centerY)){
                spiderWalkFalse();
            }
        }
    }
    
    /**
     * Make visible the spiderweb
     */
    public void makeVisible(){
        isVisible = true;
        if(isProblem){
            drawForProblem();
        }else
        draw();
        for(Bridge bridge: bridgesByColor.values()){
            bridge.makeVisible();
        }
        for(Spot spot: spots.values()){
            spot.makeVisible();
        }
        for(Strand strand: strandsAndCoordenates.values()){
            strand.makeVisible();
        }
        if(spider != null ){
            spider.makeVisible();
        }
        okay = true;
    }
    
    /**
     * Make invisible SpiderWeb, bridges, spots, spider
     */
    public void makeInvisible(){
        for(Bridge bridge: bridgesByColor.values()){
            bridge.makeInvisible();
        }
        for(Spot spot: spots.values()){
            spot.makeInvisible();
        }
        for(Strand strand: strandsAndCoordenates.values()){
            strand.makeInvisible();
        }
        if(spider != null ){
            spider.makeInvisible();
        }
        okay = true;
    }
    
    /**
     * Return in a Arraylist the spots which are reachable to the spider
     */
    public ArrayList<String> reachablesSpots(){
        ArrayList<String> reachables = new ArrayList<>();
        
        return reachables;
    }
    
    /**
     * Return the bridges that the spider dont use
     */
    public ArrayList<String> unusedBridges(){
        ArrayList<String> unused = new ArrayList<>();
        for (Map.Entry<String, Bridge> entry : unusedBridges.entrySet()) {
            String key = entry.getKey();
            unused.add(key);
        }
        return unused;
    }
    
    /**
     * Eliminate SpiderWeb, bridges, spots
     */
    private void eliminate(){
        for(Bridge bridge: bridgesByColor.values()){
            bridge.makeInvisible();
            bridgesByColor.remove(bridge);
        }
        for(Spot spot: spots.values()){
            spot.makeInvisible();
            spots.remove(spot);
        }
        for(Strand strand: strandsAndCoordenates.values()){
            strand.makeInvisible();
            strandsAndCoordenates.remove(strand);
        }
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
            okay = false;
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
            okay = false;
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
            okay = false;
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
     * Add one strand to the SpiderWeb
     */
    public void addStrand() {
        eliminate();
        strands += 1;
        draw();
        okay = true;
    }
    
    /**
     * Enlarge the SpiderWeb
     * @param percentage is many the spiderweb going to enlarge
     */
    public void enlarge(int percentage){
        eliminate();
        okay = true;
        double multi = 1 + (percentage/100);
        radius = (int) (radius * multi);
        draw();
    }

    /**
     * Count many bridges exist with a specific color
     * @return the number of bridges of this color in the integer list
     */
    public ArrayList<Integer> bridge(String color) {
        ArrayList<Integer> bridgeCounts = new ArrayList<>();
        for(Bridge bridge : bridgesByColor.values()) {
            if (bridge.getColor().equals(color)) {
                bridgeCounts.add(1); 
            }
        }
        okay = true;
        return bridgeCounts;
    }
    
    /**
     * Count many spots exist with a specific color
     * @return the number of bridges of this color in the integer list
     */
    public ArrayList<Integer> spot(String color){
        ArrayList<Integer> spotCounts = new ArrayList<>();
        for(Spot spot:spots.values()){
            if(spot.getColor().equals(color)){
                spotCounts.add(1);
            }
        }
        okay = true;
        return spotCounts;
    }
    
    /**
     * Arraylist with the color of the bridges in the spiderweb
     * @return arraylist with the color of the bridges
     */
    public ArrayList<String> bridges(){
        ArrayList<String> bridgesList = new ArrayList<>();
        for(String color: bridgesByColor.keySet()){
            bridgesList.add(color);
        }
        okay = true;
        return bridgesList;
    }
    
    /**
     * Arraylist with the color of the spots in the spiderweb
     * @return arraylist with the color of the spots
     */
    public ArrayList<String> spots(){
        ArrayList<String> spotsList = new ArrayList<>();
        for(String color: spots.keySet()){
            spotsList.add(color);
        }
        okay = true;
        return spotsList;
    }
    
    /**
     * Finish the simulator 
     */
    public void finish() {
        System.exit(1);
    }
    
    /**
     * Check if the last action was succesfull
     * @return boolean, true if the action was succesfull or false if not succesfull
     */
    public boolean ok(){
        return okay;
    }
    
    public List<Integer> spiderLastPath() {
        return spiderMovements;

    }
    
    /**
     * Return bridge when firstStrand < strands
     */
    private void createBridgeCase1(String color,int distance ,int firstStrand){
        Bridge bridge = new Bridge(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand),
            findCoordenateX(distance,firstStrand+1),findCoordenateY(distance,firstStrand+1),color, firstStrand, firstStrand + 1);
        bridge.makeVisible();
        bridgesByColor.put(color,bridge);
        unusedBridges.put(color,bridge);
        colorAndStrand.put(color,firstStrand);
        okay = true;
        
    }
    
    /**
     * Return bridge when firstStrand = strands
     */
    private void createBridgeCase2(String color,int distance, int firstStrand){
        Bridge bridge = new Bridge(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                        ,findCoordenateX(distance,1),findCoordenateY(distance,1),color,firstStrand,1);
        bridge.makeVisible();
        bridgesByColor.put(color,bridge);
        unusedBridges.put(color,bridge);
        colorAndStrand.put(color,firstStrand);
        okay = true;
    }
    
    
    
    /**
     * Calculate X
     */
    private int calculateX(double currentAngle){
        x = (int)(centerX + radius * Math.cos(currentAngle));
        return x;
    }
    
    /**
     * Calculate Y
     */
    private int calculateY(double currentAngle){
        y = (int)(centerY + radius * Math.sin(currentAngle));
        return y;
    }
    
    /**
     * Check if the specific strand have bridges 
     * @return the color of the bridge
     */
    public String bridgeColorToMove(int strand){
        double shortDistance = Double.MAX_VALUE;
        int bridgeStrand = -1;
        String color = null;
        double spiderDistance = spider.distanceToAnyObject(centerX,centerY);
        for(Bridge bridge : bridgesByColor.values()){
            int actualStrandStart = bridge.getStrandBridgeStart();
            int actualStrandEnd = bridge.getStrandBridgeEnd();
            double distance = 0;
            if(actualStrandStart == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getStartX() , 2) + Math.pow(centerY - bridge.getStartY() , 2));
            }else if(actualStrandEnd == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getEndX() , 2) + Math.pow(centerY - bridge.getEndY() , 2));
            }
            if(distance < shortDistance && distance > spiderDistance + 1){
                shortDistance = distance;
                color = bridge.getColor();
            }
        }
        return color;
    }
    
    public Integer bridgeDir(int strand){
        double shortDistance = Double.MAX_VALUE;
        int bridgeStrand = -1;
        int dir = -1;
        double spiderDistance = spider.distanceToAnyObject(centerX,centerY);
        for(Bridge bridge : bridgesByColor.values()){
            int actualStrandStart = bridge.getStrandBridgeStart();
            int actualStrandEnd = bridge.getStrandBridgeEnd();
            double distance = 0;
            if(actualStrandStart == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getStartX() , 2) + Math.pow(centerY - bridge.getStartY() , 2));
                if(distance < shortDistance && distance > spiderDistance){
                shortDistance = distance;
                dir = 1;
                }
            }else if(actualStrandEnd == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getEndX() , 2) + Math.pow(centerY - bridge.getEndY() , 2));
                if(distance < shortDistance && distance > spiderDistance){
                shortDistance = distance;
                dir = 2;
                }
            }
        }
        return dir;
    }
    
    /**
     * Spider Move in the bridge in the case that strand < strands
     */
    private void spiderWalkCase1(String color, int dir){
        Bridge bridge = bridgesByColor.get(color);
        unusedBridges.remove(color);
        if(dir == 1){
            createPath(spider.getX(), spider.getY(), bridge.getStartX(), bridge.getStartY());           
            spider.move(bridge.getStartX(),bridge.getStartY());
            createPath(spider.getX(), spider.getY(),bridge.getEndX(),bridge.getEndY());
            spider.move(bridge.getEndX(),bridge.getEndY());
            strand ++;
        }else if(dir == 2 ){
            createPath(spider.getX(), spider.getY(),bridge.getEndX(),bridge.getEndY());
            spider.move(bridge.getEndX(),bridge.getEndY());
            createPath(spider.getX(), spider.getY(), bridge.getStartX(), bridge.getStartY());    
            spider.move(bridge.getStartX(),bridge.getStartY());
            strand --;
        }
    }
    
    /**
     * Spider move in the bridge in the case that strand == strands
     */
    private void spiderWalkCase2(String color, int dir){
        Bridge bridge = bridgesByColor.get(color);
        unusedBridges.remove(color);
        if(dir == 1){
            createPath(spider.getX(), spider.getY(), bridge.getStartX(), bridge.getStartY());           
            spider.move(bridge.getStartX(),bridge.getStartY());
            createPath(spider.getX(), spider.getY(),bridge.getEndX(),bridge.getEndY());
            spider.move(bridge.getEndX(),bridge.getEndY());
            strand = 1;
        }else if(dir == 2 ){
            createPath(spider.getX(), spider.getY(),bridge.getEndX(),bridge.getEndY());
            spider.move(bridge.getEndX(),bridge.getEndY());
            createPath(spider.getX(), spider.getY(), bridge.getStartX(), bridge.getStartY());    
            spider.move(bridge.getStartX(),bridge.getStartY());
            strand --;
        }
    }
    
    /**
     * Spider move in the bridge in the case that not have valid bridges
     */
    private void spiderWalkCase3(String color, int dir){
        int xPos = findCoordenateX(radius,strand);
        int yPos = findCoordenateY(radius,strand);
        createPath(spider.getX(), spider.getY(), xPos, yPos);
        spider.move(xPos,yPos);
        spiderMovements.add(strand);
    }
    
    /**
     * Spider move in the bridge in the case that strand == 1
     */
    private void spiderWalkCase4(String color, int dir){
        Bridge bridge = bridgesByColor.get(color);
        unusedBridges.remove(color);
        if(dir == 1){
            createPath(spider.getX(), spider.getY(), bridge.getStartX(), bridge.getStartY());           
            spider.move(bridge.getStartX(),bridge.getStartY());
            createPath(spider.getX(), spider.getY(),bridge.getEndX(),bridge.getEndY());
            spider.move(bridge.getEndX(),bridge.getEndY());
            strand ++;
        }else if(dir == 2 ){
            createPath(spider.getX(), spider.getY(),bridge.getEndX(),bridge.getEndY());
            spider.move(bridge.getEndX(),bridge.getEndY());
            createPath(spider.getX(), spider.getY(), bridge.getStartX(), bridge.getStartY());    
            spider.move(bridge.getStartX(),bridge.getStartY());
            strand = strands;
        }
    }
    
    /**
     * Spider walk throught de spiderweb when the boolean is true
     */
    private void spiderWalkTrue(){
        String bridgeColor = bridgeColorToMove(strand);
        int dirToMove = bridgeDir(strand);
        if(bridgeColor!= null && strand<strands && strand != 1){
            spiderWalkCase1(bridgeColor,dirToMove);
        }else if(bridgeColor!= null && strand == strands){
            spiderWalkCase2(bridgeColor,dirToMove);
        }else if(bridgeColor == null){
            spiderWalkCase3(bridgeColor,dirToMove);
        }else if (bridgeColor != null && strand == 1){
            spiderWalkCase4(bridgeColor,dirToMove);
        }
        okay = true;
    }
    
    private void spiderWalkFalse(){
        String bridgeColor = bridgeColorToMoveFalse();
        int dirToMove = bridgeDirFalse(strand);
        if(bridgeColor!= null && strand<strands && strand != 1){
            spiderWalkCase1(bridgeColor,dirToMove);
        }else if(bridgeColor!= null && strand == strands){
            spiderWalkCase2(bridgeColor,dirToMove);
        }else if(bridgeColor == null){
            spiderWalkCaseFalse(bridgeColor,dirToMove);
        }else if (bridgeColor != null && strand == 1){
            spiderWalkCase4(bridgeColor,dirToMove);
        }
        okay = true;
    }
    
    private String bridgeColorToMoveFalse(){
        double biggestDistance = Double.MIN_VALUE;
        int bridgeStrand = -1;
        String color = null;
        double spiderDistance = spider.distanceToAnyObject(centerX,centerY);
        for(Bridge bridge : bridgesByColor.values()){
            int actualStrandStart = bridge.getStrandBridgeStart();
            int actualStrandEnd = bridge.getStrandBridgeEnd();
            double distance = 0;
            if(actualStrandStart == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getStartX() , 2) + Math.pow(centerY - bridge.getStartY() , 2));
            }else if(actualStrandEnd == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getEndX() , 2) + Math.pow(centerY - bridge.getEndY() , 2));
            }
            if(distance > biggestDistance && distance < spiderDistance){
                biggestDistance = distance;
                color = bridge.getColor();
            }
        }
        return color;
    }
    
    private int bridgeDirFalse(int strand){
        double biggestDistance = Double.MIN_VALUE;
        int bridgeStrand = -1;
        int dir = -1;
        double spiderDistance = spider.distanceToAnyObject(centerX,centerY);
        for(Bridge bridge : bridgesByColor.values()){
            int actualStrandStart = bridge.getStrandBridgeStart();
            int actualStrandEnd = bridge.getStrandBridgeEnd();
            double distance = 0;
            if(actualStrandStart == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getStartX() , 2) + Math.pow(centerY - bridge.getStartY() , 2));
                if(distance > biggestDistance && distance < spiderDistance){
                biggestDistance = distance;
                dir = 1;
                }
            }else if(actualStrandEnd == strand){
                distance = Math.sqrt(Math.pow(centerX - bridge.getEndX() , 2) + Math.pow(centerY - bridge.getEndY() , 2));
                if(distance > biggestDistance && distance < spiderDistance){
                biggestDistance = distance;
                dir = 2;
                }
            }
        }
        return dir;
    }
    
    /**
     * Spider move in the bridge in the case that not have valid bridges
     */
    private void spiderWalkCaseFalse(String color, int dir){
        spider.move(centerX,centerY);
        spiderMovements.add(strand);
    }
    
    /**
     * Delete all paths that made the spider
     */
    private void deletePath() {
        List<Integer> keysToRemove = new ArrayList<>(spiderpaths.keySet());
        for (Integer key : keysToRemove) {
            lines path = spiderpaths.get(key);
            path.makeInvisible();
            spiderpaths.remove(key); 
        }
    }
    
    /**
     * Create a representation of a path traversed by the spider.
     */
    public void createPath(int x1,int y1, int x2, int y2){
        lines path = new lines(x1+4, y1+4, x2+4, y2+4, "magenta");
        path.makeVisible();
        spiderpaths.put(index,path);
        index ++;
        okay = true;
    }
    

}
