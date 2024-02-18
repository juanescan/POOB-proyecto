

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
        Head = new Circle(20,150,150,"red");
        Body = new Rectangle(40,5,158,110,"black");
        leg1 = new lines(150,140,110,130,"blue");
        leg2 = new lines(150,140,110,130,"blue");
        leg3 = new lines(150,130,110,140,"blue");
        leg4 = new lines(150,130,110,140,"blue");
    }
    
    public void makeVisible()
    {
        Head.makeVisible();
        Body.makeVisible();
        leg1.moveRight(7);
        leg2.moveRight(7);
        leg2.up(20);
        leg3.moveRight(54);
        leg4.moveRight(54);
        leg4.up(20);
    }
    
    
}