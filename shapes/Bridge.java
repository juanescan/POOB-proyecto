
/**
 * Bridge.
 * 
 * @author (Juan Cancelado y Santiago Córdoba) 
 * @version (1.0)
 */
public class Bridge
{
    private lines bridge;
    private boolean isVisible;
    
    /**
     * Constructor for objects of class Bridge
     * @param x1 the x part of the first Coordenate of the bridge
     * @param y1 the y part of the first Coordenate of the bridge
     * @param x2 the x part of the second Coordenate of the bridge
     * @param y1 the y part of the seconf Coordenate of the bridge
     * @param color of the bridge
     */
    public Bridge(int x1, int y1, int x2, int y2, String color)
    {
        bridge = new lines(x1,y1,x2,y2,color);
        isVisible = false;
    }
    
    public boolean isOnBridge(int x, int y) {
        return bridge.isOnLine(x, y);
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
    
    public int getEndX(int startX,int startY) {
        return bridge.getEndX(startX,startY);
    }   
    
    public int getEndY(int startX,int startY) {
        return bridge.getEndX(startX,startY);
    } 
}
