import java.util.ArrayList;

public class Player extends Adversary{

	public String _name;
	public ArrayList<Equipment> _equipment;
	public int _strengthAlt;
	public int _defenseAlt;

	public Player() {
		super();
		_name = "";
		_equipment = new ArrayList<Equipment>(6);
		_strengthAlt = 0;
		_defenseAlt = 0;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public void equip(Equipment equipment) {
		_equipment.add(equipment);
		this._strengthAlt += equipment._strengthAlt;
		this._defenseAlt += equipment._defenseAlt;
	}

	public String eqToString() {
		String str = _name + "'s equipment: ";
		for (Equipment equipment : _equipment) {
			str += equipment._name + ", ";
		}
		str = str.substring(0, str.length()-2);
		return str;
	}

}
