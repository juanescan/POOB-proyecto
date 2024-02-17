
/**
 * Write a description of class Misionero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Misionero
{
    // instance variables - replace the example below with your own
    private Triangle sombrero;
    private Circle cara;
    private Rectangle piernaD;
    private Rectangle piernaI;
    private Rectangle brazoD;
    private Rectangle brazoI;
    private Rectangle pecho;
    private boolean isVisible;

    /**
     * Constructor for objects of class Misionero
     */
    public Misionero()
    {
        // initialise instance variables
        sombrero = new Triangle(30,30,45,0,"black");
        cara = new Circle(30,30,30,"red");
        pecho = new Rectangle(40,30,30,60,"black");
        brazoI = new Rectangle(40,10,20,60,"black");
        brazoD = new Rectangle(40,10,60,60,"black");
        piernaD = new Rectangle(40,10,30,100,"black");
        piernaI = new Rectangle(40,10,50,100,"black");
    }
    
    public void makeVisible(){
        sombrero.makeVisible();
        cara.makeVisible();
        brazoI.makeVisible();
        pecho.makeVisible();
        brazoD.makeVisible();
        piernaD.makeVisible();
        piernaI.makeVisible();
    }

    public void makeInvisible(){
        sombrero.makeInvisible();
        cara.makeInvisible();
        brazoI.makeInvisible();
        pecho.makeInvisible();
        brazoD.makeInvisible();
        piernaD.makeInvisible();
        piernaI.makeInvisible();
    }
    
    public void changeSize(int newSize){
        sombrero.changeSize(newSize,newSize);
        cara.changeSize(newSize);
        brazoI.changeSize(newSize,newSize);
        pecho.changeSize(newSize,newSize);
        brazoD.changeSize(newSize,newSize);
        piernaD.changeSize(newSize,newSize);
        piernaI.changeSize(newSize,newSize);
    }
    
    public void changeColor(String newColor){
        sombrero.changeColor(newColor);
        cara.changeColor(newColor);
        brazoI.changeColor(newColor);
        pecho.changeColor(newColor);
        brazoD.changeColor(newColor);
        piernaD.changeColor(newColor);
        piernaI.changeColor(newColor);
    }
    
     public void moveTo(int f,int c){
        sombrero.Cordenadas(f,c);
        cara.Cordenadas(f,c);
        brazoI.Cordenadas(f,c);
        pecho.Cordenadas(f,c);
        brazoD.Cordenadas(f,c);
        piernaD.Cordenadas(f,c);
        piernaI.Cordenadas(f,c);
    }
    
         
 
}
