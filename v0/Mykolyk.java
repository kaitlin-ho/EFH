import java.util.*;
public class Mykolyk extends NPC{
  public Mykolyk() {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
    ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("answer 1");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("answer 2");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    InvisCloak InvisCloak = new InvisCloak();
    _equipment.add(InvisCloak);
    KtS key = new KtS();
    _equipment.add(key);
    _greeting = "";
    _winMsg = "";
    _lossMsg = "";
  }

  public Mykolyk(Player player) {
    this();
    _player = player;
  }

}
