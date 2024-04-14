package spiderweb;


/**
 * Write a description of class Normal here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Normal extends Spot
{

    /**
     * Constructor for objects of class Normal
     */
    public Normal(int size, int xPos, int yPos, String color, int strand){
        super(size, xPos, yPos, color, strand);
    }

    public void actWithTheSpider(Spider spider){

    }
    
    public static spiderWeb getSpiderWebInstance(){
        return spiderWeb.getInstance();
    }
}
