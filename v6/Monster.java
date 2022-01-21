/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class Monster extends Adversary{

	public Monster() {
		_lives = 1; //_lives will not change, but we want it to be >0 so that isAlive() still works
		_hp = 100; //these will be overriden in the sublevel monsters
		_strength = 50; //^
		_defense = 50;
		_attackRating = 1;
	}

}
