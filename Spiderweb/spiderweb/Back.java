package spiderweb;


/**
 * Write a description of class Back here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Back extends Spot{
    /**
     * Constructor for objects of class Back
     */
    public Back(int size, int xPos, int yPos, String color, int strand){
        super(size, xPos, yPos, color, strand);
    }

    public void actWithTheSpider(Spider spider){
        if(spider.getX() == xPos && spider.getY() == yPos){
            spider.move(spider.getCenterX(),spider.getCenterY());
        }
    }
}
