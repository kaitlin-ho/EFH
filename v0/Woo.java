import java.io.*;
import java.util.*;

public class Woo{

  private Player _player;
  private Monster _monster;
  private Ducky _ducky;
  private Kats _kats;
  private Mykolyk _mykolyk;
  private Erica _erica;

  private int _difficulty; //not used right now
  private int _defeatCtr;
  private boolean _gameOver;

  private InputStreamReader isr;
  private BufferedReader in;

  public Woo(){
    _gameOver = false;
    _player = new Player();
    _ducky = new Ducky();
    _kats = new Kats(_player);
    _mykolyk = new Mykolyk(_player);
    _erica = new Erica(_player);
    _defeatCtr = 0;
    isr = new InputStreamReader( System.in ); //InputStreamReader reads bytes and decodes them into characters
    in = new BufferedReader( isr ); //BufferedReader reads text from a character-input stream
    newGame();
  }

  public void newGame(){
    String s = "";
    String name = "";
    s = "Ducky RPG\n";
    s += "EXPLANATION OF GAME\n";
    System.out.println(s);

    //Difficulty
    s = "\nChoose your difficulty: \n";
    s += "1: Easy\n";
    s += "2: Hard\n";
    s += "Selection: ";
    System.out.print(s);

    try {
      _difficulty = Integer.parseInt( in.readLine() );
    }
    catch ( IOException e ) { }

    //Name
    s = "Enter your name: ";
    System.out.print(s);

    try {
      name = in.readLine();
    }
    catch ( IOException e ) { }

    //create character
    _player = new Player();
    _player._name = name;

    System.out.println("");

  }

  public void talk(NPC npc) {
    String answer;
    System.out.println(npc.getResponse());
    Scanner convo = new Scanner(System.in);
    answer = convo.nextLine();
    npc.judge(answer);
  }

  public String help() {
    return _ducky.getResponse();
  }

  public void battle() {
    String s = "";
    String command = "";
    System.out.println("Get ready!\n");

    //Monster type
    if (_defeatCtr == 5) {
      _monster = new Boss();
    }
    else if (Math.random() >= _difficulty/2.0) {
      _monster = new MonWeak();
    }
    else {
      _monster = new MonOk();
    }

    //The battle begins!
    s = "Your foe has arrived!\n";
    s += "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
    s += "The foe's HP: " + _monster.getHP() + "\n";
    System.out.println(s);
    while (_player.isAlive() && _monster.isAlive()) {
      s = "Enter \"fight\" to attack or ";
      s += "\"flight\" to run away: ";
      System.out.println(s);
      try {
        command = in.readLine();
      }
      catch ( IOException e ) { }
      if (command.trim().toLowerCase().indexOf("fight") < 0) {
        System.out.println("Retreat!");
        return;
      }

      //Attacks, get damages
      int damageAgainstMonster = _player.attack(_monster);
      int damageAgainstPlayer = _monster.attack(_player);
      //Show results
      s = "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
      s += "The foe's HP: " + _monster.getHP() + "\n";
      System.out.println(s);
      if ( _player.isAlive() && !(_monster.isAlive())) {
        System.out.println(_player.getName() + " have defeated the foe!");
        _defeatCtr++;
      }
      else if (!(_player.isAlive()) && !(_monster.isAlive())) {
        System.out.println(_player.getName() + " and the foe have defeated each other!");
      }
      else if (!(_player.isAlive()) && _monster.isAlive()) {
        System.out.println("The foe has defeated " + _player.getName() + "!");
      }
    }
  }

  public String startMsg(){
    String s;
    s = "\nWhat would you like to do? \n";
    s += "1: See an NPC \n";
    s += "2: Battle a monster \n";
    s += "3: Equip \n";
    s += "Selection: ";
    return s;
  }

  public boolean playTurn(){
    boolean proceed = true;
    int i = 1;
    System.out.print(startMsg());
    try {
      i = Integer.parseInt(in.readLine());
    }
    catch ( IOException e) { }
    System.out.println("");
    if (i == 2) {
      battle();
      if (!(_player.isAlive()) || _defeatCtr == 6) {
        proceed = false;
      }
      return proceed;
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
    return proceed; // change to actual boolean value
  }


  public static void main( String[] args){

    //new game
    Woo game = new Woo();
    while (game.playTurn()) { }
    String s = "Game Over";
    System.out.println(s);
  }




}
