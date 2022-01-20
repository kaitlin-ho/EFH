/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

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
  private boolean _retreat;

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
    _retreat = false;
    newGame();
  }

  public void newGame(){
    String s = "";
    String name = "";
    String duckyName = "";
    s = "Ducky RPG\n";
    s += "\tIn this game, you will battle monsters using equipment obtained by\n";
    s += "correctly answering trivia questions posed by NPCS.\n";
    s += "\tThere are 3 trivia categories: Math, Computer Science, and Pop-culture.\n";
    s += "The corresponding NPCs are Mr. Kats, Mr. Mykolyk, and Erica.\n";
    s += "\tYou will have a personal ducky that follows you around. If you need help\n";
    s += "answering a trivia question or fighting a monster, you may request\n";
    s += "assistance from your ducky by entering a phrase containing your ducky's name\n";
    s += "(which you will choose), the word \"help\", or both, even if asking for help isn't\n";
    s += "explicitly listed as an option. However, if you are prompted to enter a number\n";
    s += "to signify a choice, you may only enter one of the listed numbers.\n";
    s += "\tYou will be able to select the overall difficulty of the game, which will\n";
    s += "also increase with every monster you defeat. The 6th and final monster you\n";
    s += "encounter will be the Boss, and after defeating the Boss, you will be declared the\n";
    s += "winner and the game will end.\n";
    s += "\tEquipment from the NPCs will be added your inventory, and you may choose to\n";
    s += "equip up to 2 pieces of equipment at a time. You may also view your inventory and\n";
    s += "currently equipped items at will.\n";
    s += "\n";
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
    _kats = new Kats(_player, _shield, _sword);
    _mykolyk = new Mykolyk(_player, _invisCloak, _kts);
    _erica = new Erica(_player, _armor, _bowArrow);
    System.out.println("");

  }

  public void talk(NPC npc) {
    String answer;
    Scanner convo;
    System.out.println(npc.getResponse());
    if (npc._rightAnsCt < 2) {
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
        _retreat = true;
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

  public void battle(Monster monster) {
    String s = "";
    String answer = "";
    System.out.println("Get ready!\n");

    //The battle begins!
    s = "Your foe has arrived!\n";
    s += "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
    s += "The foe's HP: " + monster.getHP() + "\n";
    System.out.println(s);
    while (_player.isAlive() && monster.isAlive()) {
      _retreat = false;
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
        _retreat = true;
        return;
      }

      //Attacks, get damages
      int damageAgainstMonster = _player.attack(_monster);
      int damageAgainstPlayer = monster.attack(_player);
      //Show results
      s = "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
      s += "The foe's HP: " + monster.getHP() + "\n";
      System.out.println(s);
      if ( _player.isAlive() && !(monster.isAlive())) {
        System.out.println(_player.getName() + " have defeated the foe!");
        _defeatCtr++;
      }
      else if (!(_player.isAlive()) && !(monster.isAlive())) {
        System.out.println(_player.getName() + " and the foe have defeated each other!");
      }
      else if (!(_player.isAlive()) && monster.isAlive()) {
        System.out.println("The foe has defeated " + _player.getName() + "!");
      }
    }
  }

  public void twistBattle() throws IOException {
    String s = "";
    String answer = "";
    int countDown = 5;
    while (_player.isAlive() && _ducky.isAlive()) {
      answer = "";
      System.out.println(_ducky.getQuestion());
      Scanner y = new Scanner(System.in);
      long start = System.currentTimeMillis();
      while (System.currentTimeMillis() < start + 3000) {
        if (System.in.available() > 0) {
          String line = y.nextLine();
          answer = line;
          break;
        }
      }
      if (answer.length() > 0) {
        System.out.println("judging now...");
        _ducky.judge(answer, _ducky, _player);
        delay(1);
        System.out.println("finished judging");
      }
      else {
        System.out.println("AHHHHHH");
        _ducky.attack(_player);
        s = "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
        s += _ducky.getName() + "'s HP: " + _ducky.getHP() + "\n";
        System.out.println(s);
      }
      delay(1);
    }
  }

  public void equip(Equipment item){
    _player.equip(item);
  }

  public void chooseEquipment() {
    String s = "";
    for (int n = 0; n < _player._inventory.size(); n++){
      s += (n + 1) + ". ";
      s += (_player._inventory.get(n)._name) + "\n";
    }
    System.out.println("What do you want to equip?");
    System.out.println(s);
    int itemNum = 1;
    try{
      itemNum = Integer.parseInt(in.readLine());
    }
    catch (IOException e) { }
    equip(_player._inventory.get(itemNum-1));
  }

  public void unequip(Equipment item){
    _player.unequip(item);
  }

  public void chooseUnequipment() {
    String s = "";
    for (int n = 0; n < _player._equipment.size(); n++){
      s += (n + 1) + ". ";
      s += (_player._equipment.get(n)._name) + "\n";
    }
    System.out.println("What do you want to unequip?");
    System.out.println(s);
    int itemNum = 1;
    try{
      itemNum = Integer.parseInt(in.readLine());
    }
    catch (IOException e) { }
    unequip(_player._equipment.get(itemNum-1));
  }

  public String startMsg(){
    String s;
    s = "\nWhat would you like to do? \n";
    s += "1: See an NPC \n";
    s += "2: Battle a monster \n";
    s += "3: Equip \n";
    s += "4: Unequip \n";
    s += "5: Check your inventory\n";
    s += "6: Check what you have equipped\n";
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
      if (_retreat) {
        battle(_monster);
      }
      else {
        battle();
      }
      if (!(_player.isAlive()) || _defeatCtr == 6) {
        proceed = false;
      }
      if (_defeatCtr == 6) {
	proceed = false;
	System.out.println("We have a winner!!!");
      }
      return proceed;
    }

    else if (i == 3) {
      if (_player._inventory.size() == 0){
        System.out.println("You have nothing in your inventory.");
      }
      else {
        chooseEquipment();
      }
    }

    else if (i == 4) {
      if (_player._equipment.size() == 0){
        System.out.println("You have nothing equipped.");
      }
      else {
        chooseUnequipment();
      }
    }

    else if ( i == 5 ) {
      System.out.println(_player.invToString());
    }

    else if (i==6){
      System.out.println(_player.eqToString());
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

  public void delay(int seconds) {
    long beginTimer = System.currentTimeMillis();
    while (System.currentTimeMillis() - beginTimer < seconds * 1000) {
      continue;
    }
  }

  public static void wait(int millis) {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) { }
  }

  public void twist() throws IOException {
    delay(10);
    System.out.println("LOL you thought you were finished?");
    delay(2);
    String d = "";
    d += "          __        \n";
    d += "        <(o )___    \n";
    d += "         ( ._> /    \n";
    d += "          `---'     \n";
    System.out.println(d);
    System.out.println(_ducky._name + " has turned on you!");
    delay(2);
    System.out.println("Defeat " + _ducky._name + " or die trying.");
    _player.unequip(_player._equipment.get(0));
    _player.unequip(_player._equipment.get(0));
    delay(2);
    String s = "";
    s += "Choose your equipment wisely...\n";
    System.out.print(s);
    chooseEquipment();
    s = "Now choose another.\n";
    System.out.print(s);
    chooseEquipment();
    s = "You're out of time.\n";
    s += _ducky._name + " has arrived.\n";
    s += "You must attack!\n";
    twistBattle();

  }

  public static void main( String[] args) throws IOException {
    //new game
    Woo game = new Woo();
    while (game.playTurn()) { }
    String s = "Game Over";
    System.out.println(s);

    System.out.println("\n" + "\n");

    //twist!!!
    if (game._defeatCtr == 6) {
      game.twist();
    }
  }

}
