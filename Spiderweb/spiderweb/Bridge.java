package spiderweb; 
import shapes.*;   

/**
 * Bridge.
 * 
 * @author (Juan Cancelado y Santiago Córdoba) 
 * @version (1.0)
 */
public class Bridge
{
    protected lines bridge;
    protected boolean isVisible;
    protected int x1;
    protected int y1;
    protected int x2;
    protected int y2;
    protected int strandBridgeStart;
    protected int strandBridgeEnd;
    protected int strand;
    protected String color;
    protected int distance;
    
    /**
     * Constructor for objects of class Bridge
     * @param x1 the x part of the first Coordenate of the bridge
     * @param y1 the y part of the first Coordenate of the bridge
     * @param x2 the x part of the second Coordenate of the bridge
     * @param y1 the y part of the seconf Coordenate of the bridge
     * @param color of the bridge
     */
    public Bridge(int x1, int y1, int x2, int y2, String color, int strand, int secondStrand, int distance)
    {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        bridge = new lines(x1,y1,x2,y2,color);
        isVisible = false;
        strandBridgeStart = strand;
        strandBridgeEnd = secondStrand;
        this.color = color;
        this.distance = distance;
    }
    
    /**
     * Make visible the spiderweb
     */
    public void makeVisible(){
        bridge.makeVisible();
        isVisible = true;
    }
    
    /**
     * Make invisible the spiderweb
     */
    public void makeInvisible(){
        bridge.makeInvisible();
        isVisible = false;
    }
    
    /**
     * Get the color of the bridge
     */
    public String getColor(){
        return bridge.getColor();
    }
    
    public int getStartX(){
        return x1;
    }
    
    public int getStartY(){
        return y1;
    }
    
    public int getEndX(){
        return x2;
    }
    
    public int getEndY(){
        return y2;
    }
    
    public int getStrandBridgeStart(){
        return strandBridgeStart;
    }
    
    public int getStrandBridgeEnd(){
        return strandBridgeEnd;
    }
}
