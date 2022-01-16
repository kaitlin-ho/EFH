import java.util.*;
public class Kats extends NPC{
  public Kats() {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
    ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("answer 1");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("answer 2");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    Shield shield = new Shield();
    _equipment.add(shield);
    Sword sword = new Sword();
    _equipment.add(sword);
    _greeting = "Asking the hard-hitting questions.";
    _winMsg = "Good.";
    _lossMsg = "HAHA!";
  }

  public Kats(Player player) {
    this();
    _player = player;
  }

}
