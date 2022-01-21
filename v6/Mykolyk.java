/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.*;
public class Mykolyk extends NPC{
  public Mykolyk(InvisCloak invisCloak, KtS kts) {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
    ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("answer 1");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("answer 2");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    _equipment.add(invisCloak);
    _equipment.add(kts);
    _greeting = "";
    _winMsg = "";
    _lossMsg = "";
  }

  public Mykolyk(Player player, InvisCloak invisCloak, KtS kts) {
    this(invisCloak, kts);
    _player = player;
  }

}
