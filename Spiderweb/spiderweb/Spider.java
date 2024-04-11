 
package spiderweb; 
import shapes.*;  
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spider
{
    private Circle Head;
    private Rectangle Body;
    private Rectangle leg1;
    private Rectangle leg2;
    private Rectangle leg3;
    private Rectangle leg4;
    private Rectangle leg5;
    private Rectangle leg6;
    private String color;
    private boolean isVisible;
    private Map<String, Bridge> unusedBridges;
    private Map<Integer, lines> spiderpaths;
    private int strand;
    private int index;
    private int nStrands;
    private int radius;
    private int centerY;
    private int centerX;
     private Map<Integer, Strand> strandsAndCoordenates;
    
    /**
     * Constructor for objects of class Spider
     * @param xStrand x part of the first Coordenate of the bridge  and  yStrand y part of the first Coordenate of the bridge
     * @param strand is the strand that the spider was sit
     */
    public Spider(int size, int xStrand, int yStrand, int strand, int nStrands, int radius, int centerX, int centerY, Map<Integer, Strand> strands)
    {
        Head = new Circle(size/6,xStrand,yStrand,"red");
        Body = new Rectangle(size/3,size/7,Head.getXPosition(),Head.getYPosition(),"black");
        leg1 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg2 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg3 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg4 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg5 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg6 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        this.strand = strand;
        index = 1;
        unusedBridges = new HashMap<>();
        spiderpaths = new HashMap<>();
        this.nStrands = nStrands;
        this.radius = radius;
        this.centerX = centerX;
        this.centerY = centerY;
        strandsAndCoordenates = strands;
    }
    
    /**
     * Make visible the spider
     */
    public void makeVisible()
    {
        Head.makeVisible();
        Body.makeVisible();
        leg1.makeVisible();
        leg2.makeVisible();
        leg3.makeVisible();
        leg4.makeVisible();
        leg5.makeVisible();
        leg6.makeVisible();
        organize();
    }
    
    /**
     * Make invisible the spider
     */
    public void makeInvisible(){
        Head.makeInvisible();
        Body.makeInvisible();
        leg1.makeInvisible();
        leg2.makeInvisible();
        leg3.makeInvisible();
        leg4.makeInvisible();
        leg5.makeInvisible();
        leg6.makeInvisible();
    }
    
    /**
     * Organize the spider to keep the shape
     */
    private void organize(){
        Body.Coordenadas(Head.getXPosition(),Head.getYPosition() + Head.getDiameter());
        leg1.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition());
        leg2.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition());
        leg3.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition() + Body.getHeight() -leg1.getHeight());
        leg4.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition() + Body.getHeight() -leg1.getHeight());
        leg5.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition()+(Body.getHeight()/2)-(leg1.getHeight()/2));
        leg6.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition()+(Body.getHeight()/2)-(leg1.getHeight()/2));
    }

    
    /**
     * Get the xPosition of spider
     * @return coordenate x of the spider
     */
    public int getX() {
        return Head.getXPosition();
    }  
    
     /**
     * Get the yPosition of spider
     * @return coordenate y of the spider
     */
    public int getY() {
    return Head.getYPosition();
    }
    
    /**
     * Change the strand of the spider
     */
    public void setStrand(int newStrand){
        this.strand = newStrand;
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
     * @param firstStrand is to select in what strand calculate the value of coordenate x
     */
    private int findCoordenateX(int distance,int firstStrand){ 
        int x2 = (int)(centerX + distance * Math.cos(getAngleByIndex(firstStrand)));
        return x2;
    }
    
    /**
     * Find the new coordenate y
     * @param distance is the distance of the new point 
     * @param firstStrand is to select in what strand calculate the value of coordenate x
     */
    private int findCoordenateY(int distance,int firstStrand){ 
        int y2 = (int)(centerY + distance * Math.sin(getAngleByIndex(firstStrand)));
        return y2;
    }
    
    /**
     * Calculate the distance of the spider to any coordenate
     * @return the distance of the spider to that coordenate
     */
    public double distanceToAnyObject(int x, int y){
        double distance = Math.sqrt(Math.pow(getX() - x , 2) + Math.pow(getY() - y , 2));
        return distance;
    }
    
    /**
     * move the spider to a specific coordenate 
     */
    public void move(int x, int y) {
    
        Head.moveToCoordenates(x, y);
        Body.moveToCoordenates(x, y);
        leg1.moveToCoordenates(x, y);
        leg2.moveToCoordenates(x, y);
        leg3.moveToCoordenates(x, y);
        leg4.moveToCoordenates(x, y);
        leg5.moveToCoordenates(x, y);
        leg6.moveToCoordenates(x, y);
        organize();
    }
    
    /**
     * Determinate if the spider is in a certain position
     * @return boolean, if the spider is in that position return true else return false 
     */
    public boolean spiderInAPosition(int xPos, int yPos){
        boolean res = false;
        if(getX() == xPos && getY() == yPos){
            res = true;
            return res;
        }
        return res;
    }
    
    /**
     * Check if the spider is on a Bouncy 
     */
    public void spiderOnBouncy(Map<String, Bouncy> bouncys){
        boolean limit = false;
        for(Bouncy b : bouncys.values()){
           if(getX() == b.getX() && getY() == b.getY() && !limit) {
               b.bounce(this, strandsAndCoordenates, nStrands);
               limit = true;
           } 
        }
    }
    
    /**
     * Check if the spider is on a Killer
     */
    public void spiderOnKiller(Map<String, Killer> killers, spiderWeb spiderweb){
        for(Killer k : killers.values()){
            if(getX() == k.getX() && getY() == k.getY()){
                k.kill(this,spiderweb);
            }
        }
    }
    
    /**
     * Find and return the color of the bridge that of the spider is going to walk
     * @return the color of the bridge
     */
    private String bridgeColorToMove(int strand, Map<String, Bridge>bridgesByColor){
        double shortDistance = Double.MAX_VALUE;
        int bridgeStrand = -1;
        String color = null;
        double spiderDistance = distanceToAnyObject(centerX,centerY);
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
    
    /**
     * Determinate the direction of the spider going to cross one bridge
     * return int, -1 if dont exist bridge that fulfill the condition, 1 if going to cross the bridge is to the start point of the bridge to the final point, 2 if going to cross the bridge is to end point of the bridge to the start point
     */ 
    private Integer bridgeDir(int strand, Map<String, Bridge>bridgesByColor){
        double shortDistance = Double.MAX_VALUE;
        int bridgeStrand = -1;
        int dir = -1;
        double spiderDistance = distanceToAnyObject(centerX,centerY);
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
    private void spiderWalkCase1(String color, int dir,Map<String, Bridge>bridgesByColor ,Map <String,Integer>colorAndStrand,spiderWeb spiderweb){
        Bridge bridge = bridgesByColor.get(color);
        unusedBridges.remove(color);
        if(dir == 1){
            createPath(getX(), getY(), bridge.getStartX(), bridge.getStartY());           
            move(bridge.getStartX(),bridge.getStartY());
            createPath(getX(), getY(),bridge.getEndX(),bridge.getEndY());
            move(bridge.getEndX(),bridge.getEndY());
            if(bridge instanceof Mobile){
                ((Mobile)bridge).movilizate(spiderweb,this);
            }else if(bridge instanceof Weak){
                ((Weak)bridge).destroy(bridge,bridgesByColor,colorAndStrand);
            }
            strand ++;
        }else if(dir == 2 ){
            createPath( getX(),  getY(),bridge.getEndX(),bridge.getEndY());
            move(bridge.getEndX(),bridge.getEndY());
            createPath( getX(),  getY(), bridge.getStartX(), bridge.getStartY());    
            move(bridge.getStartX(),bridge.getStartY());
            if(bridge instanceof Mobile){
                ((Mobile)bridge).movilizate(spiderweb,this);
            }else if(bridge instanceof Weak){
                ((Weak)bridge).destroy(bridge,bridgesByColor,colorAndStrand);
            }
            strand --;
        }
    }
    
    /**
     * Spider move in the bridge in the case that strand == strands
     */
    private void spiderWalkCase2(String color, int dir, Map<String, Bridge>bridgesByColor ,Map <String,Integer>colorAndStrand,spiderWeb spiderweb){
        Bridge bridge = bridgesByColor.get(color);
        unusedBridges.remove(color);
        if(dir == 1){
            createPath( getX(),  getY(), bridge.getStartX(), bridge.getStartY());           
            move(bridge.getStartX(),bridge.getStartY());
            createPath( getX(),  getY(),bridge.getEndX(),bridge.getEndY());
            move(bridge.getEndX(),bridge.getEndY());
            if(bridge instanceof Mobile){
                ((Mobile)bridge).movilizate(spiderweb,this);
            }else if(bridge instanceof Weak){
                ((Weak)bridge).destroy(bridge,bridgesByColor,colorAndStrand);
            }
            strand = 1;
        }else if(dir == 2 ){
            createPath( getX(),  getY(),bridge.getEndX(),bridge.getEndY());
            move(bridge.getEndX(),bridge.getEndY());
            createPath( getX(),  getY(), bridge.getStartX(), bridge.getStartY());    
            move(bridge.getStartX(),bridge.getStartY());
            if(bridge instanceof Mobile){
                ((Mobile)bridge).movilizate(spiderweb,this);
            }else if(bridge instanceof Weak){
                ((Weak)bridge).destroy(bridge,bridgesByColor,colorAndStrand);
            }
            strand --;
        }
    }
    
    /**
     * Spider move in the bridge in the case that not have valid bridges
     */
    private void spiderWalkCase3(String color, int dir){
        int xPos = findCoordenateX(radius,strand);
        int yPos = findCoordenateY(radius,strand);
        createPath( getX(),  getY(), xPos, yPos);
        move(xPos,yPos);

    }
    
    /**
     * Spider move in the bridge in the case that strand == 1
     */
    private void spiderWalkCase4(String color, int dir, Map<String, Bridge>bridgesByColor,Map <String,Integer>colorAndStrand,spiderWeb spiderweb){
        Bridge bridge = bridgesByColor.get(color);
        unusedBridges.remove(color);
        if(dir == 1){
            createPath( getX(),  getY(), bridge.getStartX(), bridge.getStartY());           
            move(bridge.getStartX(),bridge.getStartY());
            createPath( getX(),  getY(),bridge.getEndX(),bridge.getEndY());
            move(bridge.getEndX(),bridge.getEndY());
            if(bridge instanceof Mobile){
                ((Mobile)bridge).movilizate(spiderweb,this);
            }else if(bridge instanceof Weak){
                ((Weak)bridge).destroy(bridge,bridgesByColor,colorAndStrand);
            }
            strand ++;
        }else if(dir == 2 ){
            createPath( getX(),  getY(),bridge.getEndX(),bridge.getEndY());
            move(bridge.getEndX(),bridge.getEndY());
            createPath( getX(),  getY(), bridge.getStartX(), bridge.getStartY());    
            move(bridge.getStartX(),bridge.getStartY());
            if(bridge instanceof Mobile){
                ((Mobile)bridge).movilizate(spiderweb,this);
            }else if(bridge instanceof Weak){
                ((Weak)bridge).destroy(bridge,bridgesByColor,colorAndStrand);
            }
            strand = nStrands;
        }
    }
    
    /**
     * Determinate what is the case that the spider going to walk in case that advance was true
     */
    public void spiderWalkTrue(Map<String, Bridge>bridgesByColor,Map <String,Integer>colorAndStrand,spiderWeb spiderweb){
        String bridgeColor = bridgeColorToMove(strand,bridgesByColor);
        int dirToMove = bridgeDir(strand,bridgesByColor);
        if(bridgeColor!= null && strand<nStrands && strand != 1){
            spiderWalkCase1(bridgeColor,dirToMove,bridgesByColor,colorAndStrand,spiderweb);
        }else if(bridgeColor!= null && strand == nStrands){
            spiderWalkCase2(bridgeColor,dirToMove,bridgesByColor,colorAndStrand,spiderweb);
        }else if(bridgeColor == null){
            spiderWalkCase3(bridgeColor,dirToMove);
        }else if (bridgeColor != null && strand == 1){
            spiderWalkCase4(bridgeColor,dirToMove,bridgesByColor,colorAndStrand,spiderweb);
        }
    }

    /**
     * Determinate what is the case that the spider going to walk in case that advance was false
     */
    public void spiderWalkFalse(Map<String, Bridge>bridgesByColor,Map <String,Integer>colorAndStrand,spiderWeb spiderweb){
        String bridgeColor = bridgeColorToMoveFalse(strand,bridgesByColor);
        int dirToMove = bridgeDirFalse(strand,bridgesByColor);
        if(bridgeColor!= null && strand<nStrands && strand != 1){
            spiderWalkCase1(bridgeColor,dirToMove,bridgesByColor,colorAndStrand,spiderweb);
        }else if(bridgeColor!= null && strand == nStrands){
            spiderWalkCase2(bridgeColor,dirToMove,bridgesByColor,colorAndStrand,spiderweb);
        }else if(bridgeColor == null){
            spiderWalkCaseFalse(bridgeColor,dirToMove);
        }else if (bridgeColor != null && strand == 1){
            spiderWalkCase4(bridgeColor,dirToMove,bridgesByColor,colorAndStrand,spiderweb);
        }
    }
    
     /**
     * Find and return the color of the bridge that of the spider is going to walk in case that advance was false
     * @return the color of the bridge
     */
    private String bridgeColorToMoveFalse(int strand, Map<String, Bridge>bridgesByColor){
        double biggestDistance = Double.MIN_VALUE;
        int bridgeStrand = -1;
        String color = null;
        double spiderDistance =  distanceToAnyObject(centerX,centerY);
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
    
    /**
     * Determinate the direction of the spider going to cross one bridge in case that advance was false
     * return int, -1 if dont exist bridge that fulfill the condition, 1 if going to cross the bridge is to the start point of the bridge to the final point, 2 if going to cross the bridge is to end point of the bridge to the start point
     */ private int bridgeDirFalse(int strand, Map<String, Bridge>bridgesByColor){
        double biggestDistance = Double.MIN_VALUE;
        int bridgeStrand = -1;
        int dir = -1;
        double spiderDistance =  distanceToAnyObject(centerX,centerY);
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
     * Spider move in the bridge in the case that not have valid bridges and advance was fals
     */
    private void spiderWalkCaseFalse(String color, int dir){
         move(centerX,centerY);
    }
    
     /**
     * Delete all paths that made the spider
     */
    public void deletePath() {
        ArrayList<Integer> keysToRemove = new ArrayList<>(spiderpaths.keySet());
        for (Integer key : keysToRemove) {
            lines path = spiderpaths.get(key);
            path.makeInvisible();
            spiderpaths.remove(key); 
        }
    }
    
    /**
     * Create a representation of a path traversed by the spider
     */
    private void createPath(int x1,int y1, int x2, int y2){
        lines path = new lines(x1+4, y1+4, x2+4, y2+4, "magenta");
        path.makeVisible();
        spiderpaths.put(index,path);
        index ++;
    }
    
    /**
     * Return the bridges that the spider dont use
     * @return ArrayList of Strings that are the color of the bridges 
     */
    public ArrayList<String> unusedBridges(Map<String, Bridge> unusedBridges){
        this.unusedBridges = unusedBridges;
        ArrayList<String> unused = new ArrayList<>();
        for (Map.Entry<String, Bridge> entry : unusedBridges.entrySet()) {
            String key = entry.getKey();
            unused.add(key);
        }
        return unused;
    }
}
