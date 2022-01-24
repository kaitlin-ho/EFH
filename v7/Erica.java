/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.*;
public class Erica extends NPC{
  public Erica(Armor armor, BowArrow bowArrow) {
    super();
    _questions.add("Who does Serena van der Woodsen end up with in Gossip Girl?");
    _questions.add("Who is the main character in the anime show Naruto?");
    ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("dan humphrey");
    answersOne.add("dan");
    answersOne.add("humphrey");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("naruto");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    _equipment.add(armor);
    _equipment.add(bowArrow);
    _greeting = "Hi, lets check your pop-culture expertise.";
    _winMsg = "Yay!";
    _lossMsg = "Go get access to the inter-trash, loser";
  }

  public Erica(Player player, Armor armor, BowArrow bowArrow) {
    this(armor, bowArrow);
    _player = player;
  }


}
