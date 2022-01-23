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
  private int _attackCtr;
  private int _defeatCtr;
  private int _npcCtr;
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
    //Counters
    _attackCtr = 0;
    _defeatCtr = 0;
    _npcCtr = 0;

    isr = new InputStreamReader( System.in ); //InputStreamReader reads bytes and decodes them into characters
    in = new BufferedReader( isr ); //BufferedReader reads text from a character-input stream
    _retreat = false;
    newGame();
  }

  public void newGame(){ //starts a new game
    String s = "";
    String name = "";
    String duckyName = "";
    String narration = "";

    s = "Ducky RPG\n";
    s += "For the best experience, please adjust your window so this line fits on your screen. Please also be sure your font is at a comfortable reading size.";
    System.out.println(s);

    //Difficulty
    s = "\nChoose your difficulty: \n";
    s += "1: Easy\n";
    s += "2: Medium\n";
    s += "3: Hard\n";
    s += "Selection: ";
    System.out.println(s);
    try {
      _difficulty = Integer.parseInt( in.readLine() );
    }
    catch ( IOException e ) { }

    narration += "Hello? Uh... Hello? Can you hear me? I don't think they can hear me.\n";
    narration += "O Great Adventurer! Can you hear me?\n";

    type(narration);

    try {
      String anything = in.readLine();
    }
    catch ( IOException e ) { }
    System.out.println("");

    narration = "You can! What's your name?\n";

    type(narration);
    try {
      name = in.readLine();
    }
    catch ( IOException e ) { }

    //names the player
    _player._name = name;

//STORY
    narration = "\n" + _player.getName() + "! I've been trying to reach you for over ten years now. You're the destined one. Destined for what, you ask? Destined to be our savior.\n"
    + "Our hero.\n"
    + "Our world has been riddled with monsters who have destroyed our crops and ruined our cities.\n"
    + "They attack in everlasting waves, controlled by their Boss.\n"
    + "We need YOU, " + _player.getName() + ", to defeat the big ma- Uh. I mean the boss monster. \n"
    + "There have been Adventurers before you. We have a bunch of their skeletons in our closet.\n"
    + "Sometimes we feed them to our dog. Ha Ha.\n"
    + "But you! The divine prophet, oh so wise and fair, spoke of you. She said you're our only hope, our Hercules. You know, that Greek guy?\n"
    + "Will you help us?\n";
    type(narration);

//player may type anything, but the story will continue as if they said yes
    try {
      String anything = in.readLine();
    }
    catch ( IOException e ) { }
    System.out.println("");

//STORY
    narration = "Hurrah, hurrah! Let's get going! What? Ducky? Do they not know about that already? Oh really... "
    + "Sigh, I guess I'll have to explain.\n"
    + "So, " + _player.getName() + ", since I've received intelligence that you somehow, do not know what a Ducky is, I will explain them to you.\n"
    + "Duckies are a magical omniscient race, renowned for their "
    + "expansive knowledge. The wild ones cannot be tamed, but we have tamed one just for you."
    + "\n"
    + "\n"
    + "Here, hold it. Give it a name, and it will be yours. What will you name it?\n";
    type(narration);

//player names their duck
    try {
      duckyName = in.readLine();
    }
    catch ( IOException e ) { }

    //create ducky
    _ducky = new Ducky(duckyName);

    narration = "\n" + _ducky.getName() + "? Oh! I think it likes its name! That's a first. They're very hard to please, you know. "
    + _ducky.getName() + " will follow you 'til your death.\n"
    + "It's pretty lazy, though, so it'll only respond to its name and/or \"help\" during battles or conversations with the Questioners. \n"
    + "Don't bother trying to talk to it at any other time.\n";
    type(narration);

   //player asks: Questioners? (or anything they want)

//STORY
   narration = "Huh? My lunch is ready? Well, " + _player.getName() + ", I have to go, it's time to eat!";
   type(narration);

//NPCS and equipment
    _kats = new Kats(_player, _shield, _sword);
    _mykolyk = new Mykolyk(_player, _invisCloak, _kts);
    _erica = new Erica(_player, _armor, _bowArrow);
    System.out.println("");

  }


  //to print stuff so it looks like it's being typed out
  public static void type(String s){
    String punc = ",.?!-";
    for(int i = 0; i < s.length(); i++){
      System.out.print(s.charAt(i));
      if (punc.indexOf(s.charAt(i)) >= 0) {
        delay(400);
      }
      else {
        delay(50);
      }
    }
    System.out.println("");
  }

  public static void italicize(String s){
    System.out.println("\u001b[3m" + s + "\u001b[0m");
  }

//method that will allow the player to interact with the NPC
  public void talk(NPC npc) {
    String answer;
    Scanner convo;
    System.out.println(npc.getResponse());
    if (npc._rightAnsCt < 2) {
      convo = new Scanner(System.in);
      answer = convo.nextLine();
      answer = answer.trim().toLowerCase();
      //If the player asks for help from the ducky
      while ((answer.indexOf("help") >=0)
        || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        System.out.println(help());
        System.out.println("Are you ready now? What is your answer?");
        convo = new Scanner(System.in);
        answer = convo.nextLine();
        answer = answer.trim().toLowerCase();
      }
      //returns to the NPC interaction if the player has not asked for help from the ducky again
      System.out.println(npc.judge(answer));
    }
  }

//calling the ducky
  public String help() {
    return _ducky.getResponse();
  }

// battle function of the game
  public void battle() {
    String s = "";
    String narration = "";
    String answer = "";
    System.out.println("Get ready!\n");

    //Monster type
    if (_defeatCtr == 5) {
      _monster = new Boss();
      //STORY
      narration = "What the- \n"
      + "It's the boss! He's never showed up this quickly for our other adventurers. I knew you were special.\n"
      + "We are almost free! Terror no more!\n"
      + "The final battle, isn't this just exhilarating? Go " + _player.getName() + ", go!";
      type(narration);
    }
    else if (Math.random() >= _difficulty/4.0 + 0.01) {
      _monster = new MonWeak();
    }
    else {
      _monster = new MonOk();
    }

    //The battle begins!
    s = "A monster has arrived!\n";
    s += "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
    s += "The monster's HP: " + _monster.getHP() + "\n";
    System.out.println(s);
    while (_player.isAlive() && _monster.isAlive()) {
      //if player has never encountered a monster
      if (_attackCtr == 0) {
        narration = "I'm back, I'm back! Just in time. By the way, that food was quite yummy, if I do say so myself.\n";
        narration += "Anyway, I see you've met your first monster. It's your first battle! Aren't you excited?\n";
        type(narration);

        try {
          String anything = in.readLine();
        }
        catch ( IOException e ) { }
        System.out.println("");

        narration = "It doesn't matter, get ready! Oh- And, I hope you've talked to the Questioners. You'll really need their help. Have fun!\n";
        type(narration);

        s = "Enter \"f\" for fight or ";
        s += "\"l\" for flight: ";
      }
      else {
        s = "Fight or flight?";
      }
      System.out.println(s);
      try {
        answer = in.readLine();
      }
      catch ( IOException e ) { }
      _attackCtr = 1; //the player won't see initial instructions again

      //if the player asks the ducky for help:
      while ((answer.indexOf("help") >=0)
      || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        System.out.println(help());
        System.out.println("Fight or flight?");
        try {
          answer = in.readLine();
        }
        catch ( IOException e ) { }
        answer = answer.trim().toLowerCase();
      }

      //retreating from the fight
      if (answer.trim().toLowerCase().indexOf("f") < 0) {
        System.out.println("Retreat!");
        _retreat = true;
        return;
      }

      //Attacks, get damages
      int damageAgainstMonster = _player.attack(_monster);
      int damageAgainstPlayer = _monster.attack(_player);
      //Show results
      s = "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
      s += "The monster's HP: " + _monster.getHP() + "\n";
      System.out.println(s);
      if ( _player.isAlive() && !(_monster.isAlive())) {
        System.out.println(_player.getName() + " has defeated the monster!");
        _defeatCtr++;
      }
      else if (!(_player.isAlive()) && !(_monster.isAlive())) {
        System.out.println(_player.getName() + " and the monster have defeated each other!");
      }
      else if (!(_player.isAlive()) && _monster.isAlive()) {
        System.out.println("The monster has defeated " + _player.getName() + "!");
      }      s += "\"flight\" to run away: ";
    }
  }

  //overloaded battle to make sure that when the player flees a fight, they will return to the same monster
  public void battle(Monster monster) {
    String s = "";
    String answer = "";
    System.out.println("Get ready!\n");

    //The battle begins!
    s = "A monster has arrived!\n";
    s += "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
    s += "The monster's HP: " + monster.getHP() + "\n";
    System.out.println(s);
    while (_player.isAlive() && monster.isAlive()) {
      _retreat = false;
      s = "Fight or flight?";
      System.out.println(s);
      try {
        answer = in.readLine();
      }
      catch ( IOException e ) { }
      while ((answer.indexOf("help") >=0)
      || answer.indexOf(_ducky.getName().toLowerCase()) >= 0) {
        System.out.println(help());
        System.out.println("Fight or flight?");
        try {
          answer = in.readLine();
        }
        catch ( IOException e ) { }
        answer = answer.trim().toLowerCase();
      }
      if (answer.trim().toLowerCase().indexOf("f") < 0) {
        System.out.println("Retreat!");
        _retreat = true;
        return;
      }

      //Attacks, get damages
      int damageAgainstMonster = _player.attack(_monster);
      int damageAgainstPlayer = monster.attack(_player);
      //Show results
      s = "\n" + _player.getName() + "'s HP: " + _player.getHP() + "\n";
      s += "The monster's HP: " + monster.getHP() + "\n";
      System.out.println(s);
      if ( _player.isAlive() && !(monster.isAlive())) {
        System.out.println(_player.getName() + " has defeated the monster!");
        _defeatCtr++;
      }
      else if (!(_player.isAlive()) && !(monster.isAlive())) {
        System.out.println(_player.getName() + " and the monster have defeated each other!");
      }
      else if (!(_player.isAlive()) && monster.isAlive()) {
        System.out.println("The monster has defeated " + _player.getName() + "!");
      }
    }
  }

//endgame
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
    System.out.println(_player.equip(item));
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
    System.out.println(_player.unequip(item));
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

//selection options for the player (between actions)
  public String startMsg(){
    String s;
    s = "\nWhat would you like to do? \n";
    s += "1: Interact with a Questioner \n";
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
    String narration = "";
    System.out.println(startMsg());
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

  //STORY
  narration = "You-\n"
  + "YOU DID IT! + " + _player.getName() + ", OUR SAVIOR! HERO! LIBERATION FOR ALL! Hurry, tell everyone!"
  + "\n"
  + "..."
  + "Do you hear that? The townspeople are cheering for you. Oh, not just them. The continent is cheering for you. Huzzah!"
  + "You've saved them from the tyranny of those disgusting creatures.\n"
  + "What? Oh, really? " + _player.getName() + " Someone's just told me they're planning a huge celebration for you tonight.\n"
  + "You can use my cabin to clean that dirty blood off yourself and rest up before your big party.\n"
  + "I'll go help them set things up. See you there!";
  type(narration);

      }
      return proceed;
    }

//so the player cannot equip anything not in their inventory
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

//NPC choices
    else {
      String st = "";

      st = "Who do you wish to face?";
      st += "\n1: Mr. Kats for math trivia \n";
      st += "2: Erica for pop-culture trivia \n";
      st += "3: Mr. Mykolyk for computer science trivia";

      //if player has never seen an npc, display narrative
      if (_npcCtr == 0) {
        narration = "Why do I have to do this? Why don't you do it!? \n"
        + "...\n"
        + "Fine.\n"
        + "My brave Adventurer! This is a Questioner, an elite being that transcends mortality. They are other-worldly blacksmiths, the finest out there.\n"
        + "They do not accept mere human currency as payment for their extraordinary weapons and armor.\n"
        + "In love with riddles and questions, they will deem you worthy of their pieces of work if you answer correctly.\n"
        + "Each person may only answer 2 questions, though, as they do not have time for more than that. I wish you luck!\n";
        type(narration);
      }

      System.out.println(st);
      int u = 1;
      try {
        u = Integer.parseInt(in.readLine());
      }
      catch ( IOException e) { }
      _npcCtr = 1; //the player won't see the initial npc narrative again
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
    System.out.println("LOL you thought you were finished?");
    delay(2*1000);
    String d = "";
    d += "          __        \n";
    d += "        <(o )___    \n";
    d += "         ( ._> /    \n";
    d += "          `---'     \n";
    System.out.println(d);
    System.out.println(_ducky._name + " has turned on you!");
    delay(2000);
    System.out.println("Defeat " + _ducky._name + " or die trying.");
    if (_player._equipment.size() > 0) {
      System.out.println(_player.unequip(_player._equipment.get(0)));
    }
    if (_player._equipment.size() > 0) {
      System.out.println(_player.unequip(_player._equipment.get(0)));
    }
    delay(2000);
    String s = "";
    s += "Choose your equipment wisely...\n";
    System.out.println(s);
    chooseEquipment();
    s = "Now choose another.\n";
    System.out.println(s);
    chooseEquipment();
    s = "You're out of time.\n";
    s += _ducky._name + " has arrived.\n";
    s += "You must attack!\n";
    System.out.println(s);
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
    type("GAME OVER");
  }

}
