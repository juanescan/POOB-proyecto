package spiderweb;


/**
 * Write a description of class killer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Killer extends Spot
{
    /**
     * Constructor for objects of class killer
     */
    public Killer(int size, int xPos, int yPos, String color, int strand)
    {
        super(size, xPos, yPos, color, strand);

    }
    
    public spiderWeb getInstanceofSpiderWeb(){
        return spiderWeb.getInstance();
    }
    
    public void actWithTheSpider(Spider spider){
        spiderWeb spiderweb = getInstanceofSpiderWeb();
        if(spider.getX() == xPos && spider.getY() == yPos){
            spider.makeInvisible();
            spider.deletePath();
            spider = null;
        }
    
    }
}
