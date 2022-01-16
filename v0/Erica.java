import java.util.*;
public class Erica extends NPC{
	public Erica() {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
		ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("answer 1");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("answer 2");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    Armor armor = new Armor();
    _equipment.add(armor);
    BowArrow BA = new BowArrow();
    _equipment.add(BA);
    _greeting = "Hi, lets check your pop-culture expertise.";
    _winMsg = "Yay!";
    _lossMsg = "Go get access to the inter-trash, loser";
  }

  public Erica(Player player) {
    this();
    _player = player;
  }


}
