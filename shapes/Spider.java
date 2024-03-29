

/**
 * Spider.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spider
{
    private Circle Head;
    private Rectangle Body;
    private Rectangle leg1;
    private Rectangle leg2;
    private Rectangle leg3;
    private Rectangle leg4;
    private Rectangle leg5;
    private Rectangle leg6;
    private String color;
    private boolean isVisible;
    

    /**
     * Constructor for objects of class Spider
     * @param xStrand x part of the first Coordenate of the bridge
     * @param yStrand y part of the first Coordenate of the bridge
     */
    public Spider(int size, int xStrand, int yStrand)
    {
        Head = new Circle(size/6,xStrand,yStrand,"red");
        Body = new Rectangle(size/3,size/7,Head.getXPosition(),Head.getYPosition(),"black");
        leg1 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg2 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg3 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg4 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg5 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
        leg6 = new Rectangle(size/14,size/6,Body.getXPosition(),Body.getYPosition(),"blue");
    }
    
    /**
     * Make visible the spider
     */
    public void makeVisible()
    {
        Head.makeVisible();
        Body.makeVisible();
        leg1.makeVisible();
        leg2.makeVisible();
        leg3.makeVisible();
        leg4.makeVisible();
        leg5.makeVisible();
        leg6.makeVisible();
        organize();
    }
    
    /**
     * Make invisible the spider
     */
    public void makeInvisible(){
        Head.makeInvisible();
        Body.makeInvisible();
        leg1.makeInvisible();
        leg2.makeInvisible();
        leg3.makeInvisible();
        leg4.makeInvisible();
        leg5.makeInvisible();
        leg6.makeInvisible();
    }
    
    /**
     * Organize the spider to keep the shape
     */
    private void organize(){
        Body.Coordenadas(Head.getXPosition(),Head.getYPosition() + Head.getDiameter());
        leg1.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition());
        leg2.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition());
        leg3.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition() + Body.getHeight() -leg1.getHeight());
        leg4.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition() + Body.getHeight() -leg1.getHeight());
        leg5.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition()+(Body.getHeight()/2)-(leg1.getHeight()/2));
        leg6.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition()+(Body.getHeight()/2)-(leg1.getHeight()/2));
    }
    
    /**
     * Move the spider to a specific coordenate
     * @param x and y are the coordenate
     */
    public void moveToCoordenates(int x, int y) {
        int dx = x - Head.getXPosition();
        int dy = y - Head.getYPosition();
    
        Head.moveTo(dx, dy);
        Body.moveTo(dx, dy);
        leg1.moveTo(dx, dy);
        leg2.moveTo(dx, dy);
        leg3.moveTo(dx, dy);
        leg4.moveTo(dx, dy);
        leg5.moveTo(dx, dy);
        leg6.moveTo(dx, dy);
    }
    
    /**
     * Move the spider slow to a specific position
     * @param targetX and targetY are the coordenate
     * @speed how fast you want it to go
     */
    public void moveSlowlyToCoordenates(int targetX, int targetY, int speed) {
        int currentX = Head.getXPosition();
        int currentY = Head.getYPosition();
        int dx = targetX - currentX;
        int dy = targetY - currentY;
        double distance = Math.sqrt(dx * dx + dy * dy); 
        int steps = (int) (distance / speed);
        double stepX = dx / (double) steps;
        double stepY = dy / (double) steps;
        for (int i = 0; i < steps; i++) {
            currentX += stepX;
            currentY += stepY;
            moveToCoordenates((int) currentX, (int) currentY);
        }
    }
    
    public int getX() {
        return Head.getXPosition();
    }  
    
    public int getY() {
    return Head.getYPosition();
    }

    
    

}