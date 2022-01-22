/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.*;
public class Kats extends NPC{

  public Kats(Shield shield, Sword sword) {
    super();
    _questions.add("The leadership team of a school is looking to select 3 " +
    "teachers for a team from a group of teachers that include Christopher. " +
    "If the probability that Christopher gets selected for the team is 1/2, " +
    "how many teachers are in the group? Go team!!!");
    _questions.add("David considers a string to be sluggish if, for every 3 " +
    "consecutive letters, there is at least one vowel. How many permutations " +
    "of the letters in the word LAGGARD are sluggish?");
    ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("6");
    answersOne.add("six");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("180");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    _equipment.add(shield);
    _equipment.add(sword);
    _greeting = "Asking the hard-hitting questions.";
    _winMsg = "Good.";
    _lossMsg = "HAHA!";
  }

  public Kats(Player player, Shield shield, Sword sword) {
    this(shield, sword);
    _player = player;
  }

}
