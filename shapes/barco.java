
/**
 * Write a description of class barco here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class barco
{
    // instance variables - replace the example below with your own
    private Rectangle bote;
    private Triangle vela;
    private Rectangle mastil;
    /**
     * Constructor for objects of class barco
     */
    public barco()
    {
        // initialise instance variables
        bote = new Rectangle(30,250,30,150,"black");
        vela = new Triangle(100,50,150,30,"red");
        mastil = new Rectangle(50,10,145,100,"blue");
        //int altura,int ancho,int x,int y,String C)
    }
    
    public void makeVisible(){
        bote.makeVisible();
        vela.makeVisible();
        mastil.makeVisible();
    }
      
    public void makeInvisible(){
        bote.makeInvisible();
        vela.makeInvisible();
        mastil.makeInvisible();
    }
    
    public void changeSize(int newSize){
        bote.changeSize(newSize,newSize);
        vela.changeSize(newSize,newSize);
        mastil.changeSize(newSize,newSize);
    }
    
    public void changeColor(String newColor){
        bote.changeColor(newColor);
        vela.changeColor(newColor);
        mastil.changeColor(newColor);
    }
    
    public void moveTo(int f,int c){
        bote.Cordenadas(f,c);
        vela.Cordenadas(f,c);
        mastil.Cordenadas(f,c);
    }
}
