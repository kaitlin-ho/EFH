import java.io.*;
import java.util.*;

public class Woo{

  private Player _player;
  private Monster _monster;
  private Ducky _ducky;
  private Kats _kats;
  private Mykolyk _mykolyk;
  private Erica _erica;

  public Shield _shield;
  public Armor _armor;
  public InvisCloak _invisCloak;
  public Sword _sword;
  public BowArrow _bowArrow;
  public KtS _kts;

  private int _difficulty; //not used right now
  private int _defeatCtr;
  private boolean _gameOver;

  private InputStreamReader isr;
  private BufferedReader in;

  public Woo(){
    _gameOver = false;
    _player = new Player();
    _ducky = new Ducky();
    _shield = new Shield();
    _armor = new Armor();
    _invisCloak = new InvisCloak();
    _sword = new Sword();
    _bowArrow = new BowArrow();
    _kts = new KtS();
    _kats = new Kats(_player, _shield, _sword);
    _mykolyk = new Mykolyk(_player, _invisCloak, _kts);
    _erica = new Erica(_player, _armor, _bowArrow);
    _defeatCtr = 0;
    isr = new InputStreamReader( System.in ); //InputStreamReader reads bytes and decodes them into characters
    in = new BufferedReader( isr ); //BufferedReader reads text from a character-input stream
    newGame();
  }

  public void newGame(){
    String s = "";
    String name = "";
    String duckyName = "";
    s = "Ducky RPG\n";
    s += "EXPLANATION OF GAME\n";
    System.out.println(s);

    //Difficulty
    s = "\nChoose your difficulty: \n";
    s += "1: Easy\n";
    s += "2: Medium\n";
    s += "3: Hard\n";
    s += "Selection: ";
    System.out.print(s);
    try {
      _difficulty = Integer.parseInt( in.readLine() );
    }
    catch ( IOException e ) { }

    //Names
    s = "Enter your name: ";
    System.out.print(s);
    try {
      name = in.readLine();
    }
    catch ( IOException e ) { }
    s = "Enter your ducky's name: ";
    System.out.print(s);
    try {
      duckyName = in.readLine();
    }
    catch ( IOException e ) { }

    //create player and ducky
    _player = new Player(name);
    _ducky = new Ducky(duckyName);

    System.out.println("");

  }

  public void talk(NPC npc) {
    String answer;
    Scanner convo;
    System.out.println(npc.getResponse());
    convo = new Scanner(System.in);
    answer = convo.nextLine();
    answer = answer.trim().toLowerCase();
    //If the player asks for help
    while ((answer.indexOf("help") >=0)
      || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
      System.out.println(help());
      System.out.println("What is your answer?");
      convo = new Scanner(System.in);
      answer = convo.nextLine();
      answer = answer.trim().toLowerCase();
    }
    npc.judge(answer);
  }

  public String help() {
    return _ducky.getResponse();
  }

  public void battle() {
    String s = "";
    String answer = "";
    System.out.println("Get ready!\n");

    //Monster type
    if (_defeatCtr == 5) {
      _monster = new Boss();
    }
    else if (Math.random() >= _difficulty/4.0 + 0.01) {
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
        answer = in.readLine();
      }
      catch ( IOException e ) { }
      while ((answer.indexOf("help") >=0)
      || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        System.out.println(help());
        System.out.println("Well? Fight or flight?");
        try {
          answer = in.readLine();
        }
        catch ( IOException e ) { }
        answer = answer.trim().toLowerCase();
      }
      if (answer.trim().toLowerCase().indexOf("fight") < 0) {
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

  public void equip(Equipment item){
    _player.equip(item);
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
      String s = "";
      for (Equipment a : _player._inventory){
        s += _player.invToString();
      }
      System.out.println("What do you want to equip? Please spell it properly");
      System.out.println(s);
      Scanner scan = new Scanner(System.in);
      String item = scan.nextLine();
      equip(item);
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
