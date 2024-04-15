package spiderweb;
import java.util.Map;


/**
 * Write a description of class mobile here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mobile extends Bridge{
    /**
     * Constructor for objects of class mobile
     */
    public Mobile(int x1, int y1, int x2, int y2, String color, int strand, int secondStrand,int distance){
        super(x1,y1,x2,y2,color,strand,secondStrand,distance);
        this.color = color;
    }

    /**
     *  Move the bridge to other strand with 20% more of distance,if the spider cross the bridge 
     */
    public void movilizate(spiderWeb spiderweb,Spider spider){
        if(spider.spiderInAPosition(x2,y2)){
            distance = (int)(distance * 1.2);
            spiderweb.relocateBridge(color,distance);
        }else if(spider.spiderInAPosition(x1,y1)){
            distance = (int)(distance * 1.2);
            spiderweb.relocateBridge(color,distance);
        }
    }
    
    public spiderWeb getInstanceofSpiderWeb(){
        return spiderWeb.getInstance();
    }
    
    public void delete(Map<String,Bridge>bridgesByColor,Map<String,Integer>colorAndStrand,Map<String,Bridge>unusedBridges){
        makeInvisible();
        bridgesByColor.remove(color);
        unusedBridges.remove(color);
        colorAndStrand.remove(color);
    }   

    public void act(){
        spiderWeb spiderweb = getInstanceofSpiderWeb();
        Spider spider = spiderweb.getSpider();
        distance = (int)(distance * 1.2);
        if(spider.spiderInAPosition(x2,y2)){
            Map<String,Bridge> bridges = spiderweb.getBridgesByColor();
            Map<String,Bridge> unused = spiderweb.getUnusedBridges();
            Map<String,Integer> colorAndStrand = spiderweb.getColorAndStrand();
            delete(bridges,colorAndStrand,unused);
            spiderweb.addBridge(color,distance,strandBridgeEnd);
        }else if(spider.spiderInAPosition(x1,y1)){
            Map<String,Bridge> bridges = spiderweb.getBridgesByColor();
            Map<String,Bridge> unused = spiderweb.getUnusedBridges();
            Map<String,Integer> colorAndStrand = spiderweb.getColorAndStrand();
            delete(bridges,colorAndStrand,unused);
            spiderweb.addBridge(color,distance,strandBridgeEnd);
        }
    }
    
}
