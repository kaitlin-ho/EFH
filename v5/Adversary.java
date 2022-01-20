/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class Adversary implements AdInt{

	protected int _hp; //for battles with regular monsters (weak, ok, boss)
	protected int _defense;
	protected double _attackRating;
	protected int _strength;
	protected int _defenseMod;
	protected int _strengthMod;
	protected int _lives; //for twist battle with ducky
	//player switches from an hp to a lives system when it battles the ducky
	//non-ducky monsters only use hp and the ducky only uses lives

	public Adversary() {
		_hp = 10000;
		_defense = 60;
		_attackRating = 1.0;
		_strength = 244;
		_defenseMod = 0;
		_strengthMod = 0;
	}

	public boolean isAlive() {
		return ((_hp > 0) || (_lives > 0));
	}

	public int getHP() {
		return _hp;
	}

	public int getDefense() {
		return _defense;
	}

	public int getLives() {
		return _lives;
	}

	public void lowerHP(int damage) {
		_hp = _hp - damage;
		if (_hp < 0) { _hp = 0; }
	}

	//finds a randomized value for attackRating, but within a certain range (90-110%)
	public double attackRating() {
		_attackRating = Math.random() * .20 + .90;
		return _attackRating;
	}

	public int attack(Adversary opponent) {
		int damage = (int)(_strength * attackRating()) - opponent._defense - opponent._defenseMod + _strengthMod;
		if (damage < 0) { damage = 0; }
		opponent.lowerHP(damage);
		return damage;
	}

}
