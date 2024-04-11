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
    
    /**
     * Generate the jump of the spider, if the spider is on the bouncy 
     * @param the spider to generate the jump
     * @param Map strands to get the information of strands
     * @param nStrands to determinate the case and the number of the new strand for the spider after the jump
     */
    public void bounce(Spider spider,Map<Integer,Strand> strands, int nStrands){
            boolean spiderPosition = spider.spiderInAPosition(xPos,yPos);
            if(spiderPosition ){
                if(strand < nStrands){
                    spider.setStrand(strand + 1);
                    Strand nextStrand = strands.get(strand + 1);
                    spider.move(nextStrand.getX(),nextStrand.getY());
                }else if(strand == nStrands){
                    spider.setStrand(1);
                    Strand nextStrand = strands.get(1);
                    spider.move(nextStrand.getX(),nextStrand.getY());
                }
            }  

    }
}
