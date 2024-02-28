

/**
 * Write a description of class Spider here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Spider
{
    // instance variables - replace the example below with your own
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
     * Move the spider to the right 
     */
    public void moveRight(int x){
        Head.moveRight(x);
        Body.moveRight(x);
    }
    
    /**
     * Move the spider to the left
     */
    public void moveLeft(int x){
        Head.moveLeft(x);
        Body.moveLeft(x);
    }
    
    /**
     * Move the spider up
     */
    public void moveUp(int y){
        Head.moveUp(y);
        Body.moveUp(y);
    }
    
    /**
     * Move the spider down
     */
    public void moveDown(int y){
        Head.moveDown(y);
        Body.moveDown(y);
    }
    
    /**
     * Move the spider in diagonal way
     */
    public void moveSlowDiagonal(int x,int y){
        Head.moveDiagonally(x,y);
        Body.moveDiagonally(x,y);
    }
    
    /**
     * Move the spider to a specific coordenate
     */
    public void moveTo(int x,int y){
        Head.moveToCoordenates(x,y);
        Body.moveToCoordinates(x,y);
    }
    
    private void organize(){
        Body.Coordenadas(Head.getXPosition(),Head.getYPosition() + Head.getDiameter());
        leg1.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition());
        leg2.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition());
        leg3.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition() + Body.getHeight() -leg1.getHeight());
        leg4.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition() + Body.getHeight() -leg1.getHeight());
        leg5.Coordenadas(Body.getXPosition()-Body.getWidth(),Body.getYPosition()+(Body.getHeight()/2)-(leg1.getHeight()/2));
        leg6.Coordenadas(Body.getXPosition()+Body.getWidth(),Body.getYPosition()+(Body.getHeight()/2)-(leg1.getHeight()/2));
    }
    
}