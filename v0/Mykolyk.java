public class Mykolyk extends NPC{
  public Mykolyk() {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
    _keywords.get(0).add("Answer 1");
    _keywords.get(1).add("Answer 2");
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
