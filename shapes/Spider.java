

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
    private lines leg1;
    private lines leg2;
    private lines leg3;
    private lines leg4;
    private String color;
    private boolean isVisible;
    

    /**
     * Constructor for objects of class Spider
     */
    public Spider()
    {
        Head = new Circle(20,140,160,"red");
        Body = new Rectangle(40,5,146,119,"black");
        leg1 = new lines(145,150,105,140,"blue");
        leg2 = new lines(145,130,107,110,"blue");
        leg3 = new lines(189,140,152,150,"blue");
        leg4 = new lines(192,120,152,130,"blue");
        //public lines(int x1,int y1,int x2,int y2,String c)
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
    }
    
    /**
     * move to the center 
     */
    public void center(){
        Head.center(140,160);
        Body.center(146,119);
        leg1.center(125,145);
        leg2.center(126,120);
        leg3.center(170,145);
        leg4.center(173,126);
    }
    
    /**
     * Move the spider to the right 
     */
    public void moveRight(int x){
        Head.moveRight(x);
        Body.moveRight(x);
        leg1.moveRight(x);
        leg2.moveRight(x);
        leg3.moveRight(x);
        leg4.moveRight(x);
    }
    
    /**
     * Move the spider to the left
     */
    public void moveLeft(int x){
        Head.moveLeft(x);
        Body.moveLeft(x);
        leg1.moveLeft(x);
        leg2.moveLeft(x);
        leg3.moveLeft(x);
        leg4.moveLeft(x);
    }
    
    /**
     * Move the spider up
     */
    public void moveUp(int y){
        Head.moveUp(y);
        Body.moveUp(y);
        leg1.up(y);
        leg2.up(y);
        leg3.up(y);
        leg4.up(y);
    }
    
    /**
     * Move the spider down
     */
    public void moveDown(int y){
        Head.moveDown(y);
        Body.moveDown(y);
        leg1.down(y);
        leg2.down(y);
        leg3.down(y);
        leg4.down(y);
    }
    
    /**
     * Move the spider in diagonal way
     */
    public void moveSlowDiagonal(int x,int y){
        Head.moveDiagonally(x,y);
        Body.moveDiagonally(x,y);
        leg1.moveDiagonally(x,y);
        leg2.moveDiagonally(x,y);
        leg3.moveDiagonally(x,y);
        leg4.moveDiagonally(x,y);
    }
    
    /**
     * Move the spider to a specific coordenate
     */
    public void moveTo(int x,int y){
        Head.moveTo(x,y);
        Body.moveTo(x,y);
        leg1.moveTo(x,y);
        leg2.moveTo(x,y);
        leg3.moveTo(x,y);
        leg4.moveTo(x,y);
    }
    
    public void moveAlongWeb(spiderWeb web, int webIndex) {
        web.moveCircleAlongWeb(Head, webIndex);
        web.moveRectangleAlongWeb(Body, webIndex);
        web.movelinesAlongWeb(leg1, webIndex);
        web.movelinesAlongWeb(leg2, webIndex);
        web.movelinesAlongWeb(leg3, webIndex);
        web.movelinesAlongWeb(leg4, webIndex);
    }
        
}