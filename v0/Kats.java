public class Kats extends NPC{
  public Kats() {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
    _keywords.get(0).add("Answer 1");
    _keywords.get(1).add("Answer 2");
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
