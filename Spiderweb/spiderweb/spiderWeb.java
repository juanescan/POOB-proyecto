package spiderweb; 
import shapes.*;

import java.lang.Math;
import java.util.*;
import java.util.ArrayList;
import javax.swing.JFrame;
import java.lang.reflect.*;

/**
 * SpiderWeb.
 * 
 * @author (Juan Cancelado y Santiago Córdoba) 
 * @version (1.0)
 */
public class spiderWeb
{
    private static spiderWeb instance = null;
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
    private int countBridges;
    private ArrayList<Integer> spiderLastPath;
    private int index;
    private Map<String, Bridge> unusedBridges;
    private int[][] bridges;
    private boolean isProblem;

    
    /**
     * Constructor for objects of class spiderWeb
     * @param strands number of the strands of the SpiderWeb
     * @param radius the size of the SpiderWeb
     */
    private spiderWeb(int strands,int radius){
        this.strands = strands;
        this.radius = radius;
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
        countBridges = 1;
        spiderLastPath = new ArrayList<>();
        isProblem = false;
        makeVisible();
    }
    
    /**
     * Constructor for objects of class spiderWeb for the problem
     * @param strands number of the strands of the SpiderWeb
     * @param favorite is the spot of the spider
     * @param radius the size of the SpiderWeb
     */
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
        okay = true;
    }
    
    /**
     * Draw the spiderweb for the problem 
     */
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
    
    /**
     * Draw the spiderweb normal
     */
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
        if (distance <= 0 || distance > radius || firstStrand<1 || firstStrand > strands) {
            okay = false;
            System.out.println("El ingreso de los datos es erroneo ingrese los datos correspondientes ");
        }else{
            if(firstStrand<strands){
                createBridgeCase1(color,distance,firstStrand);
                okay = true;
            }else if(firstStrand == strands){
                createBridgeCase2(color,distance,firstStrand);
                okay = true;
            }
        }

    }
    
    /**
     * Add Bridge with the type to create many types of bridges
     * @param type is the type of the bridge ("normal","weak","mobile","transform")
     * @param color, the color of the bridge(the color can´t repeat)
     * @param distance is the distance to the center of the SpiderWeb
     * @param firstStrand is the initial strand where begin the bridge
     */
    public void addBridge(String type, String color, int distance, int firstStrand){
        int x1 = findCoordenateX(distance,firstStrand);
        int y1 = findCoordenateY(distance,firstStrand);
        int x2 = 0;
        int y2 = 0;
        int secondStrand = 0;
        if(firstStrand<strands){
            x2 = findCoordenateX(distance,firstStrand+1);
            y2 = findCoordenateY(distance,firstStrand+1);      
            secondStrand = firstStrand+1;
        }else if(firstStrand == strands){
            x2 = findCoordenateX(distance,1);
            y2 = findCoordenateY(distance,1);      
            secondStrand = 1;
        }
        try {
            Class<?> bridgeClass = Class.forName("spiderweb." + type);
            Constructor<?> constructor = bridgeClass.getConstructor(int.class, int.class, int.class, int.class, String.class, int.class,int.class, int.class);
            Object bridgeInstance = constructor.newInstance(x1, y1, x2, y2, color, firstStrand, secondStrand, distance);
            ((Bridge) bridgeInstance).makeVisible();
            bridgesByColor.put(color, (Bridge) bridgeInstance);
            unusedBridges.put(color, (Bridge) bridgeInstance);
            colorAndStrand.put(color, firstStrand);
            countBridges ++;
            okay = true;
        } catch (Exception e) {
            okay = false;
            e.printStackTrace();
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
            if(bridge.getDistance() == distance || distance < 1 || distance > radius){
                System.out.println("ponga una distancia distinta");
                okay = false;
            }else{
                int saveStrand = colorAndStrand.get(color);
                delBridge(color);
                addBridge(color,distance,saveStrand);
                okay = true; 
            }
        }else {
            okay = false;
        }
    }
    
    
    /**
     * remove the bridge of a specific color
     * @param color is to select which color bridge we will remove
     */
    public void delBridge(String color) { 
        if (bridgesByColor.containsKey(color)){
            Bridge bridge = bridgesByColor.get(color);
            bridge.delete(bridgesByColor,colorAndStrand,unusedBridges);
            okay = true;
        }else {
            okay = false;
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
        boolean spotExists = spots.values().stream().anyMatch(spot ->  spot.getStrand() == strand); 
        if(spots.containsKey(color)){
            System.out.println("El color" + color + "ya existe, seleccione otro color");
            okay = false;
        }else if(spotExists){
            System.out.println("Ya existe un spot en esa strand");
            okay = false;
        }else{
            Spot spot = new Normal(size,xPos,yPos,color,strand);
            spot.makeVisible();
            spots.put(color,spot);
            okay = true;
        }
    }
    
    /**
     * Add a spot class with a specific color and specific strand
     * @param color is the color of the new spot(the color can´t repeat)
     * @param strand is to select in which strand the spot going to be created
     * @param type is to specify the type of spot ("bouncy","killer")
     */
    
     public void addSpot(String type, String color, int strand) {
        int size = (int) (radius / 4);
        Strand s = strandsAndCoordenates.get(strand);
        int xPos = s.getX();
        int yPos = s.getY();
        
        try {

            Class<?> spotClass = Class.forName("spiderweb." + type);

            Constructor<?> constructor = spotClass.getConstructor(int.class, int.class, int.class, String.class, int.class);

            Object spotInstance = constructor.newInstance(size, xPos, yPos, color, strand);

            ((Spot) spotInstance).makeVisible();
            spots.put(color, (Spot) spotInstance);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Delete spot of a selected color
     * @param color is to select which spot remove
     */
    public void delSpot(String color) {
        if(spots.containsKey(color)){
            Spot spot = spots.get(color);
            spot.makeInvisible(); 
            spots.remove(color);
            okay = true;
        }else{
            okay = false;
        }
        
    }

    /**
     *  Sit the spider in the center of SpiderWeb 
     *  @param strand is to select in which strand the spider going to walk
     */
    public void spiderSit(int strand) {
        this.strand = strand;
        if(strand > strands || strand < 1){
            okay = false;
            System.out.println("El strand indicado no existe");
        }else{
            if(spider == null){
            spider = new Spider(radius-(radius/2),centerX,centerY,strand,strands,radius,centerX,centerY,strandsAndCoordenates);
            }
            spider.setStrand(strand);
            spider.makeVisible();
            spider.move(centerX,centerY); 
            spider.deletePath();
            okay = true;
        }
    }
    
    /**
     * walk through the spiderWeb
     * @advance is to determinate if the spider advance through the SpiderWeb or if retrocedate in some cases
     */
    public void spiderWalk(boolean advance) {
        if(spider!=null){
            for (int i = 0; i < countBridges; i++) {
               Strand stra = strandsAndCoordenates.get(strand);
               if(advance && spider != null  && spider.getX() != stra.getX() || spider.getY() != stra.getY()){
                spider.spiderWalkTrue(bridgesByColor,colorAndStrand,this,spots);
               }else if(!advance && spider != null) {
                spider.spiderWalkFalse(bridgesByColor,colorAndStrand,this);
               }
            }
            strand = spider.getStrand();
            spotAct();
            okay = true;
        }else{
            System.out.println("No existe ninguna araña primero inicialicela con spiderSit");
            okay = false;
        }
    }
    
    /**
     * Make visible the spiderweb,bridges,spots and spider
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
     * @return ArrayList of strings with the color of spots that can be reachables
     */
    public ArrayList<String> reachablesSpots(){
        ArrayList<String> reachables = new ArrayList<>();
        
        return reachables;
    }
    
    /**
     * Return the bridges that the spider dont use
     * @return ArrayList of string that are the color of bridges of bridges that in the moment dont use for the spider
     */
    public ArrayList<String> unusedBridges(){
        if(spider != null){
            ArrayList<String> unused = spider.unusedBridges(unusedBridges);
            okay = true;
            return unused;
        }
        System.out.println("inicialice la araña primero");
        okay = false;
        return null;
    }
    
    /**
     * Eliminate SpiderWeb, bridges, spots
     */
    public void eliminate(){
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
        double multi = 1 + (double)(percentage/100);
        int newRadius = (int) (radius * multi);
        radius = newRadius;
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
                Bridge b = bridgesByColor.get(color);
                int i = b.getStrandBridgeStart();
                bridgeCounts.add(i);
                int j = b.getStrandBridgeEnd();
                bridgeCounts.add(j);
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
                Spot s = spots.get(color);
                int i = s.getStrand();
                spotCounts.add(i);
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
        System.exit(0);
    }
    
    /**
     * Check if the last action was succesfull
     * @return boolean, true if the last action was succesfull or false if not succesfull
     */
    public boolean ok(){
        return okay;
    }
    
    /**
     * return the indexes for the last bridges that spider use
     * @return Arraylist of integers with the indexes of bridges
     */
    public ArrayList<Integer> spiderLastPath() {
        ArrayList<Integer> spiderMovements = spider.lastPath();
        return spiderMovements;

    }
    
    /**
     *  Create bridge when firstStrand < strands
     */
    private void createBridgeCase1(String color,int distance ,int firstStrand){
        Bridge bridge = new NormalB(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand),
            findCoordenateX(distance,firstStrand+1),findCoordenateY(distance,firstStrand+1),color, firstStrand, firstStrand + 1,distance);
        bridge.makeVisible();
        bridgesByColor.put(color,bridge);
        unusedBridges.put(color,bridge);
        colorAndStrand.put(color,firstStrand);
        okay = true;
        countBridges++;
    }
    
    /**
     * Create bridge when firstStrand = strands
     */
    private void createBridgeCase2(String color,int distance, int firstStrand){
        Bridge bridge = new NormalB(findCoordenateX(distance,firstStrand),findCoordenateY(distance,firstStrand)
                        ,findCoordenateX(distance,1),findCoordenateY(distance,1),color,firstStrand,1,distance);
        bridge.makeVisible();
        bridgesByColor.put(color,bridge);
        unusedBridges.put(color,bridge);
        colorAndStrand.put(color,firstStrand);
        okay = true;
        countBridges++;
    }
    
    /**
     * Calculate X
     * @param currentAngle is the angle of the strand
     * @return the coordenate x in the final point of strand 
     */
    private int calculateX(double currentAngle){
        x = (int)(centerX + radius * Math.cos(currentAngle));
        return x;
    }
    
    /**
     * Calculate Y
     * @param currentAngle is the angle of the strand
     * @return the coordenate y in the final point of strand 
     */
    private int calculateY(double currentAngle){
        y = (int)(centerY + radius * Math.sin(currentAngle));
        return y;
    }
      
    public static spiderWeb getInstance() {
        return instance;
    }
    
    public static spiderWeb getInstance(int strands, int radius) {
        if (instance == null) {
            instance = new spiderWeb(strands, radius);
        }
        return instance;
    }
    
    public Map<Integer,Strand> getStrandsAndCoordenates(){
        return strandsAndCoordenates;
    }
    
    public int getStrands(){
        return strands;
    }
    
    public Spider getSpider(){
        return spider;
    }
    
    public Map<String,Bridge> getBridgesByColor(){
        return bridgesByColor;
    }
    
    public Map<String,Bridge> getUnusedBridges(){
        return unusedBridges;
    }
    
    public Map<String,Integer> getColorAndStrand(){
        return colorAndStrand;
    }
    
    public Map<String,Spot> getSpots(){
        return spots;
    }
    
    public int getStrand(){
        return strand;
    }
    
    private void spotAct(){
        boolean limit = false;
        for(Spot s : spots.values()){
            if(s.getStrand() == strand && !limit){
                Spot spot = s;
                spot.actWithTheSpider(spider);
                limit = true;
            }
        }
    }
}
