public class Erica extends NPC{
	public Erica() {
    super();
    _questions.add("Question 1");
    _questions.add("Question 2");
    _keywords.get(0).add("Answer 1");
    _keywords.get(1).add("Answer 2");
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
