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

  public Woo(){ //initializing variables needed
    _gameOver = false;
    _player = new Player();
    _ducky = new Ducky();
    //equipment
    _shield = new Shield();
    _armor = new Armor();
    _invisCloak = new InvisCloak();
    _sword = new Sword();
    _bowArrow = new BowArrow();
    _kts = new KtS();
    //NPCs
    _kats = new Kats(_player, _shield, _sword);
    _mykolyk = new Mykolyk(_player, _invisCloak, _kts);
    _erica = new Erica(_player, _armor, _bowArrow);
    _defeatCtr = 0;
    isr = new InputStreamReader( System.in ); //InputStreamReader reads bytes and decodes them into characters
    in = new BufferedReader( isr ); //BufferedReader reads text from a character-input stream
    _retreat = false;
    newGame();
  }

  public void newGame(){ //starts a new game
    String s = "";
    String name = "";
    String duckyName = "";
    //instructions and description of the game
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
    type(s);

    //Difficulty
    s = "\nChoose your difficulty: \n";
    s += "1: Easy\n";
    s += "2: Medium\n";
    s += "3: Hard\n";
    s += "Selection: ";
    type(s);
    try {
      _difficulty = Integer.parseInt( in.readLine() );
    }
    catch ( IOException e ) { }

    //Names
    s = "Enter your name: ";
    type(s);
    try {
      name = in.readLine();
    }
    catch ( IOException e ) { }
    s = "Enter your ducky's name: ";
    type(s);
    try {
      duckyName = in.readLine();
    }
    catch ( IOException e ) { }

    //create player and ducky
    _player._name = name;
    _ducky = new Ducky(duckyName);
    _kats = new Kats(_player, _shield, _sword);
    _mykolyk = new Mykolyk(_player, _invisCloak, _kts);
    _erica = new Erica(_player, _armor, _bowArrow);
    System.out.println("");

  }

  //to print stuff so it looks like it's being typed out
  public static void type(String s){
    for(int i = 0; i < s.length(); i++){
      delay(50);
      System.out.print(s.charAt(i));
    }
    System.out.println("");
  }

//method that will allow the player to interact with the NPC
  public void talk(NPC npc) {
    String answer;
    Scanner convo;
    type(npc.getResponse());
    if (npc._rightAnsCt < 2) {
      convo = new Scanner(System.in);
      answer = convo.nextLine();
      answer = answer.trim().toLowerCase();
      //If the player asks for help from the ducky
      while ((answer.indexOf("help") >=0)
        || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        System.out.println(help());
        System.out.println("What is your answer?");
        convo = new Scanner(System.in);
        answer = convo.nextLine();
        answer = answer.trim().toLowerCase();
      }
      //returns to the NPC interaction if the player has not asked for help from the ducky again
      type(npc.judge(answer));
    }
  }

  public String help() {
    return _ducky.getResponse();
  }

// battle function of the game
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
    type(s);
    while (_player.isAlive() && _monster.isAlive()) {
      s = "Enter \"fight\" to attack or ";
      s += "\"flight\" to run away: ";
      type(s);
      try {
        answer = in.readLine();
      }
      catch ( IOException e ) { }
      //if the player asks the ducky for help
      while ((answer.indexOf("help") >=0)
      || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        type(help());
        type("Well? Fight or flight?");
        try {
          answer = in.readLine();
        }
        catch ( IOException e ) { }
        answer = answer.trim().toLowerCase();
      }
      //retreating from the fight
      if (answer.trim().toLowerCase().indexOf("fight") < 0) {
        type("Retreat!");
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
        type(_player.getName() + " have defeated the foe!");
        _defeatCtr++;
      }
      else if (!(_player.isAlive()) && !(_monster.isAlive())) {
        type(_player.getName() + " and the foe have defeated each other!");
      }
      else if (!(_player.isAlive()) && _monster.isAlive()) {
        type("The foe has defeated " + _player.getName() + "!");
      }
    }
  }

  //overloaded battle to make sure that when the player flees a fight, they will return to the same monster
  public void battle(Monster monster) {
    String s = "";
    String answer = "";
    type("Get ready!\n");

    //The battle begins!
    s = "Your foe has arrived!\n";
    s += "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
    s += "The foe's HP: " + monster.getHP() + "\n";
    type(s);
    while (_player.isAlive() && monster.isAlive()) {
      _retreat = false;
      s = "Enter \"fight\" to attack or ";
      s += "\"flight\" to run away: ";
      type(s);
      try {
        answer = in.readLine();
      }
      catch ( IOException e ) { }
      while ((answer.indexOf("help") >=0)
      || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        type(help());
        type("Well? Fight or flight?");
        try {
          answer = in.readLine();
        }
        catch ( IOException e ) { }
        answer = answer.trim().toLowerCase();
      }
      if (answer.trim().toLowerCase().indexOf("fight") < 0) {
        type("Retreat!");
        _retreat = true;
        return;
      }

      //Attacks, get damages
      int damageAgainstMonster = _player.attack(_monster);
      int damageAgainstPlayer = monster.attack(_player);
      //Show results
      s = "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
      s += "The foe's HP: " + monster.getHP() + "\n";
      type(s);
      if ( _player.isAlive() && !(monster.isAlive())) {
        type(_player.getName() + " has defeated the foe!");
        _defeatCtr++;
      }
      else if (!(_player.isAlive()) && !(monster.isAlive())) {
        type(_player.getName() + " and the foe have defeated each other!");
      }
      else if (!(_player.isAlive()) && monster.isAlive()) {
        type("The foe has defeated " + _player.getName() + "!");
      }
    }
  }

  public void twistBattle() throws IOException {
    String s = "";
    String answer = "";
    int countDown = 5;
    while (_player.isAlive() && _ducky.isAlive()) {
      answer = ""; //answer that will be judged by the ducky
      type(_ducky.getQuestion()); //question from ducky
      Scanner y = new Scanner(System.in);
      long start = System.currentTimeMillis(); //logs the scanner start time
      // scanner runs continuously until the time limit is over
      while (System.currentTimeMillis() < start + 3000) {
        if (System.in.available() > 0) {
          String line = y.nextLine();
          answer = line;
          break;
        }
      }
      if (answer.length() > 0) {
        System.out.println("judging now...");
        type(_ducky.judge(answer, _ducky, _player));
        delay(1000);
        System.out.println("finished judging");
      }
      else {
        type("Too Slow HAHAAAHAAAAAA");
        _player._lives -= 1;
        s = "\n" + _player.getName() + "'s Lives: " + _player.getLives() + "\n";
        s += _ducky.getName() + "'s Lives: " + _ducky.getLives () + "\n";
        type(s);
      }
      delay(1000);
    }
    if (!_player.isAlive()) {
      type("Betrayal... " + _ducky.getName() + " murdered you ruthlessly.");
    }
    if (!_ducky.isAlive()) {
      type("You've killed " + _ducky.getName() +". After all they did for you. Maybe you're the true monster.");
    }
  }

//allows the player to equip equipment if it is in their inventory
  public void equip(Equipment item){
    type(_player.equip(item));
  }

  public void chooseEquipment() {
    String s = "";
    for (int n = 0; n < _player._inventory.size(); n++){
      s += (n + 1) + ". ";
      s += (_player._inventory.get(n)._name) + "\n";
    }
    type("What do you want to equip?");
    type(s);
    int itemNum = 1;
    try{
      itemNum = Integer.parseInt(in.readLine());
    }
    catch (IOException e) { }
    equip(_player._inventory.get(itemNum-1));
  }

  public void unequip(Equipment item){
    type(_player.unequip(item));
  }

  public void chooseUnequipment() {
    String s = "";
    for (int n = 0; n < _player._equipment.size(); n++){
      s += (n + 1) + ". ";
      s += (_player._equipment.get(n)._name) + "\n";
    }
    type("What do you want to unequip?");
    type(s);
    int itemNum = 1;
    try{
      itemNum = Integer.parseInt(in.readLine());
    }
    catch (IOException e) { }
    unequip(_player._equipment.get(itemNum-1));
  }

//selection options for the player (between actions)
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
    type(startMsg());
    try {
      i = Integer.parseInt(in.readLine());
    }
    catch ( IOException e) { }
    type("");

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
	type("We have a winner!!!");
      }
      return proceed;
    }

//so the player cannot equip anything not in their inventory
    else if (i == 3) {
      if (_player._inventory.size() == 0){
        type("You have nothing in your inventory.");
      }
      else {
        chooseEquipment();
      }
    }

    else if (i == 4) {
      if (_player._equipment.size() == 0){
        type("You have nothing equipped.");
      }
      else {
        chooseUnequipment();
      }
    }

    else if ( i == 5 ) {
      type(_player.invToString());
    }

    else if (i==6){
      type(_player.eqToString());
    }

//NPC choices
    else {
      String st = "";
      st = "Who do you wish to face?";
      st += "\n1: Mr. Kats for math trivia \n";
      st += "2: Erica for pop-culture trivia \n";
      st += "3: Mr. Mykolyk for computer science trivia";
      type(st);
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

  public static void delay(int milliseconds) {
    long beginTimer = System.currentTimeMillis();
    while (System.currentTimeMillis() - beginTimer < milliseconds) {
      continue;
    }
  }

//the ducky is now your enemy
  public void twist() throws IOException {
    delay(10*1000);
    type("LOL you thought you were finished?");
    delay(2*1000);
    String d = "";
    d += "          __        \n";
    d += "        <(o )___    \n";
    d += "         ( ._> /    \n";
    d += "          `---'     \n";
    type(d);
    type(_ducky._name + " has turned on you!");
    delay(2000);
    type("Defeat " + _ducky._name + " or die trying.");
    if (_player._equipment.size() > 0) {
      type(_player.unequip(_player._equipment.get(0)));
    }
    if (_player._equipment.size() > 0) {
      type(_player.unequip(_player._equipment.get(0)));
    }
    delay(2000);
    String s = "";
    s += "Choose your equipment wisely...\n";
    type(s);
    chooseEquipment();
    s = "Now choose another.\n";
    type(s);
    chooseEquipment();
    s = "You're out of time.\n";
    s += _ducky._name + " has arrived.\n";
    s += "You must attack!\n";
    type(s);
    twistBattle();

  }


  public static void main( String[] args) throws IOException {
    //new game
    Woo game = new Woo();
    while (game.playTurn()) { }
    String s = "Game Over";
    type(s);

    System.out.println("\n" + "\n");

    //twist!!!
    if (game._defeatCtr == 6) {
      game.twist();
    }
    type("Game Over (for real this time)");
  }

}
