/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.*;
public class Mykolyk extends NPC{
  public Mykolyk(InvisCloak invisCloak, KtS kts) {
    super();
    _questions.add("What is the Wikipedia definition of computer science? Start with \"Computer science is \".");
    _questions.add("f(n) is \"Big Oh\" of g(n) if... (include spaces before and after any/all operators)");
    ArrayList<String> answersOne = new ArrayList<String>(1);
    answersOne.add("computer science is the study of theoretical foundations of computation and of practical implementation of this theory in computer systems.");
    ArrayList<String> answersTwo = new ArrayList<String>(1);
    answersTwo.add("f(n) <= C * g(n) for some C & k where n > k.");
    _keywords.add(answersOne);
    _keywords.add(answersTwo);
    _equipment.add(invisCloak);
    _equipment.add(kts);
    _greeting = "Greetings, Thinker! Let's relax, shall we?";
    _winMsg = "That smells like success to me!";
    _lossMsg = "Sounds like you could benefit from a QAF post.";
  }

  public Mykolyk(Player player, InvisCloak invisCloak, KtS kts) {
    this(invisCloak, kts);
    _player = player;
  }

}
