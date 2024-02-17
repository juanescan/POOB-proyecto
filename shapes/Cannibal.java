
/**
 * Write a description of class Cannibal here.
 * 
 * @author (Santiago Cordoba y Juan Cancelado) 
 * @version (a version number or a date)
 */
public class Cannibal
{
    // instance variables - replace the example below with your own
    private Circle cara;
    private Rectangle piernaD;
    private Rectangle piernaI;
    private Rectangle brazoD;
    private Rectangle brazoI;
    private Rectangle pecho;
    private boolean isVisible;

    /**
     * Constructor for objects of class Cannibal
     */
    public Cannibal()
    {
        // initialise instance variables
        cara = new Circle(30,30,30,"black");
        pecho = new Rectangle(40,30,30,60,"red");
        brazoI = new Rectangle(40,10,20,60,"black");
        brazoD = new Rectangle(40,10,60,60,"black");
        piernaD = new Rectangle(40,10,30,100,"black");
        piernaI = new Rectangle(40,10,50,100,"black");
    }

    /**
     * hace visible al canibal
     */
    public void makeVisible(){
        cara.makeVisible();
        brazoI.makeVisible();
        pecho.makeVisible();
        brazoD.makeVisible();
        piernaD.makeVisible();
        piernaI.makeVisible();
    }
    
    /**
     * hace no visible al canibal
     */
    public void makeInvisible(){
        cara.makeInvisible();
        brazoI.makeInvisible();
        pecho.makeInvisible();
        brazoD.makeInvisible();
        piernaD.makeInvisible();
        piernaI.makeInvisible();
    }
    
    public void changeSize(int newSize){
        cara.changeSize(newSize);
        brazoI.changeSize(newSize,newSize);
        pecho.changeSize(newSize,newSize);
        brazoD.changeSize(newSize,newSize);
        piernaD.changeSize(newSize,newSize);
        piernaI.changeSize(newSize,newSize);
    }
    
    public void changeColor(String newColor){
        cara.changeColor(newColor);
        brazoI.changeColor(newColor);
        pecho.changeColor(newColor);
        brazoD.changeColor(newColor);
        piernaD.changeColor(newColor);
        piernaI.changeColor(newColor);
    }
    
    public void moveTo(int f,int c){
        cara.Cordenadas(f,c);
        brazoI.Cordenadas(f,c);
        pecho.Cordenadas(f,c);
        brazoD.Cordenadas(f,c);
        piernaD.Cordenadas(f,c);
        piernaI.Cordenadas(f,c);
    }
    
    public void rotate(){
        brazoI.rotate();
        pecho.rotate();
        brazoD.rotate();
        piernaD.rotate();
        piernaI.rotate();
        cara.moveHorizontal(-30);
        cara.moveVertical(30);
        brazoI.moveHorizontal(10);
        brazoI.moveVertical(30);
    }
}
