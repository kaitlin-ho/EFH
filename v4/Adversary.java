/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class Adversary implements AdInt{

	protected int _hp;
	protected int _defense;
	protected double _attackRating;
	protected int _strength;
	protected int _defenseMod;
	protected int _strengthMod;

	public Adversary() {
		_hp = 10000;
		_defense = 60;
		_attackRating = 1.0;
		_strength = 244;
		_defenseMod = 0;
		_strengthMod = 0;
	}

	public boolean isAlive() {
		return (_hp > 0);
	}

	public int getHP() {
		return _hp;
	}

	public int getDefense() {
		return _defense;
	}

	public void lowerHP(int damage) {
		_hp = _hp - damage;
		if (_hp < 0) { _hp = 0; }
	}

	public double attackRating() {
		_attackRating = Math.random() * .20 + .90;
		return _attackRating;
	}

	public int attack(Adversary opponent) {
		int damage = (int)((_strength + _strengthMod) * attackRating()) - (opponent._defense + opponent._defenseMod); //Mods come from equipment
		if (damage < 0) { damage = 0; }
		opponent.lowerHP(damage);
		return damage;
	}

}
