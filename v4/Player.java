/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.ArrayList;

public class Player extends Adversary{

	public String _name;
	public ArrayList<Equipment> _inventory;
	public ArrayList<Equipment> _equipment;
	public int _strengthAlt;
	public int _defenseAlt;

	public Player() {
		super();
		_inventory = new ArrayList<Equipment>(6);
		_equipment = new ArrayList<Equipment>(2);
		_strengthAlt = 0;
		_defenseAlt = 0;
	}

	public Player(String name) {
		this();
		_name = name;
	}

//returns the name of the player
	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

//add equipment to inventory
	public void invent(Equipment equipment) {
		_inventory.add(equipment);
	}

	public void equip(Equipment equipment) {
		//Make sure it's already in the inventory
		boolean invented = false;
		for (int i = 0; i < _inventory.size(); i++) {
			if ((_inventory.get(i)).equals(equipment)) {
				invented = true;
			}
		}
		if (!invented) {
			System.out.println("This item is not in your inventory.");
		}
		else {
			//You can only have 2 things max equipped at once
			if (_equipment.size() == 2) {
				System.out.println("You already have 2 items equipped. "
				+ "Unequip something if you want to replace it.");
			}
			//adding the stats to the player with respect to the equipment they've equipped
			else {
				_equipment.add(equipment);
				this._strengthAlt += equipment._strengthAlt;
				this._defenseAlt += equipment._defenseAlt;
				_inventory.remove(equipment);
			}
		}
	}

//removes equipment already equipped and updates stats
	public void unequip(Equipment equipment) {
		_equipment.remove(equipment);
		this._strengthAlt -= equipment._strengthAlt;
		this._defenseAlt -= equipment._strengthAlt;
		_inventory.add(equipment);
	}

//returns a string version of the player's inventory
        public String invToString() {
                String str = _name + "'s inventory: ";
                for (Equipment equipment : _inventory) {
                        str += equipment._name + ", ";
                }
                str = str.substring(0, str.length()-2);
                return str;
        }

//returns a string version of the player's equipped items
	public String eqToString() {
		String str = "Currently equipped: ";
		for (Equipment equipment : _equipment) {
			str += equipment._name + ", ";
		}
		str = str.substring(0, str.length()-2);
		return str;
	}

}
