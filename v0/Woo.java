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
    _player = new Player();
    _monster = new Monster();
    _ducky = new Ducky();
    _kats = new Kats(_player);
    _mykolyk = new Mykolyk(_player);
    _erica = new Erica(_player);
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

  public String startMsg(){
    String s;
    s = "\nWhat would you like to do? \n";
    s += "1: See an NPC \n";
    s += "2: Battle a monster \n";
    s += "3: Equip ";
    return s;
    }

  public void talk(NPC npc) {
    String answer;
    System.out.println(npc.getResponse());
    Scanner convo = new Scanner(System.in);
    answer = convo.nextLine();
    npc.judge(answer);
  }


  public boolean playTurn(){
    int i = 1;
    System.out.println(startMsg());
    try {
      i = Integer.parseInt(in.readLine());
    }
    catch ( IOException e) { }
    if (i == 2) {

    }
    else if (i == 3) {

    }
    else {
      System.out.println("Who do you wish to consult");
      String st;
      st = "\n1: Mr. Kats for math trivia \n";
      st += "2: Erica for pop-culture trivia \n";
      st += "3: Mr. Mykolyk for computer science trivia";
      System.out.println(st);
      int u = 1;
      try {
        u = Integer.parseInt(in.readLine());
      }
      catch ( IOException e) { }
      if (u == 1) {
        talk(_kats);
      }
      else if (u == 2) {
        talk(_erica);
      }
      else {
        talk(_mykolyk);
      }
    }
    return true; // change to actual boolean value
  }


  public static void main( String[] args){

    //new game
    Woo game = new Woo();
    while (game.playTurn()) { }
    String s = "Game Over";
    System.out.println(s);
  }




}
