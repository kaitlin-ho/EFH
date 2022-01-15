public class Adversary implements AdInt{

	protected int _hp;
	protected int _defense;
	protected int _attackRating;
	protected int _strength;

	public boolean isAlive() {
		return true; //temporary (for compiler)
	}

	public int getDefense() {
		return _defense;
	}

	public void lowerHP(int damage) {
		_hp = _hp - damage;
	}

	public int attack(Adversary oponent) {
		return 0; //temporary (for compiler)
	}

}
