/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.ArrayList;

public class Player extends Adversary{

	public String _name;
	public ArrayList<Equipment> _inventory;
	public ArrayList<Equipment> _equipment; //"equipment" in this case refers to items that are equipped

	public Player() {
		super();
		_inventory = new ArrayList<Equipment>(6);
		_equipment = new ArrayList<Equipment>(2);
		_lives = 3;
		_inventory = new ArrayList<Equipment>(6); //There are 6 items, inventory has space for all of them
		_equipment = new ArrayList<Equipment>(2); //Max 2 items equipped at once
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

		//You can only have 2 things max equipped at once
		if (_equipment.size() == 2) {
			System.out.println("You already have 2 items equipped. "
			+ "Unequip something if you want to replace it.");
		}
		//adding the stats to the player with respect to the equipment they've equipped
		else {
			//transfer equipped item from inventory
			_equipment.add(equipment);
			_inventory.remove(equipment);
		}

		//Reset and recalculate _strengthMod and _defenseMod w/ updated equipped items
		_strengthMod = 0;
		_defenseMod = 0;
		for (Equipment e : _equipment) {
			_strengthMod += e._strengthAlt;
			_defenseMod += e._defenseAlt;
		}
	}
	//removes equipment already equipped and updates stats
	public void unequip(Equipment equipment) {
		//transfer equipped item back to inventory
		_equipment.remove(equipment);
		_inventory.add(equipment);

		//Reset and recalculate _strengthMod and _defenseMod w/ updated equipped items
		_strengthMod = 0;
		_defenseMod = 0;
		for (Equipment e : _equipment) {
			_strengthMod += e._strengthAlt;
			_defenseMod += e._defenseAlt;
		}
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
