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

    public void kill(Spider spider, spiderWeb spiderweb){
        boolean spiderPosition = spider.spiderInAPosition(xPos,yPos);
        if(spiderPosition){
            spider.makeInvisible();
            spider.deletePath();
            spiderweb.setSpider(null);
        }
    }
}
