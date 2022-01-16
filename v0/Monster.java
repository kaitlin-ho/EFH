public class Monster extends Adversary{

	public Monster() {
		_hp = 100; //these will be overriden in the sublevel monsters
		_strength = 50; //^
		_defense = 50;
		_attackRating = 1;
	}

}
