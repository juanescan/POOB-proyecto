package spiderweb;


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

    public void movilizate(spiderWeb spiderweb){
        Spider spider = spiderweb.getSpider();
        if(spider.spiderInAPosition(x2,y2)){
            distance = (int)(distance * 1.2);
            spiderweb.relocateBridge(color,distance);
        }
    }
}
