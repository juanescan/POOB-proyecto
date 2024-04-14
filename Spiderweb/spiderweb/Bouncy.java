package spiderweb;
import java.util.Map;



public class Bouncy extends Spot
{
    /**
     * Constructor for objects of class Bouncy
     */
    public Bouncy(int size, int xPos, int yPos, String color, int strand)
    {
        super(size, xPos, yPos, color, strand);
    }
    
    public spiderWeb getInstanceofSpiderWeb(){
        return spiderWeb.getInstance();
    }
    
    /**
     * Generate the jump of the spider, if the spider is on the bouncy 
     * @param the spider to generate the jump
     * @param Map strands to get the information of strands
     * @param nStrands to determinate the case and the number of the new strand for the spider after the jump
     */
    public void actWithTheSpider(Spider spider){
        spiderWeb spiderweb = getInstanceofSpiderWeb();
        int nStrands = spiderweb.getStrands();
        Map<Integer,Strand> strands = spiderweb.getStrandsAndCoordenates();
        boolean limit = false;
        if(spider.getX() == xPos && spider.getY() == yPos){
            if(strand < nStrands && !limit){
                    spider.setStrand(strand + 1);
                    Strand nextStrand = strands.get(strand + 1);
                    spider.move(nextStrand.getX(),nextStrand.getY());
                    limit = true;
            }else if(strand == nStrands && !limit){
                    spider.setStrand(1);
                    Strand nextStrand = strands.get(1);
                    spider.move(nextStrand.getX(),nextStrand.getY());
                    
                }
        }
    }
}
