import java.io.*;
import java.util.*;

public class Woo{
  
  private Player _player;
  private Monster _monster;
  private Ducky _ducky;
  private Kats _kats;
  private Mykolyk _mykolyk;
  private Erica _erica;
  
  private int difficulty; //not used right now
  
  private boolean gameOver; 
  
  private InputStreamReader isr;
  private BufferedReader in;
  
  public Woo(){
    gameOver = false;
    isr = new InputStreamReader( System.in ); //InputStreamReader reads bytes and decodes them into characters
    in = new BufferedReader( isr ); //BufferedReader reads text from a character-input stream
    newGame();
  }
  
  public void newGame(){
    String s = "";
    String name = "";
    s = "Ducky RPG\n";
    s += "EXPLANATION OF GAME";
    System.out.println(s);
    
    //Name
    s = "Enter your name:";
    System.out.println(s);
    
    try {
      name = in.readLine();
    }
    catch ( IOException e ) { }
    
    //create character
    _player = new Player();
    _player._name = name;
    
    
  }
  
  /*
  public boolean play(){
    
  
  }
  */
  
  public static void main( String[] args){
  
    //new game
    Woo game = new Woo();
    if (!game.play()){
      String s = "Game Over";
      System.out.println(s);
      return;
    }
  
  }
  
  
  
  
}
