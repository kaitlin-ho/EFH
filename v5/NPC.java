/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.*;
public class NPC implements ChatInt {

	protected int _rightAnsCt;
	protected ArrayList<String> _questions;
	protected ArrayList<ArrayList<String>> _keywords;
	protected ArrayList<Equipment> _equipment;
	protected String _greeting;
	protected String _winMsg;
	protected String _lossMsg;
	protected Player _player;

	public NPC() {
		_rightAnsCt = 0;
		_questions = new ArrayList<String>(2);
		_keywords = new ArrayList<ArrayList<String>>(2);
		_equipment = new ArrayList<Equipment>(2);
	}

	public NPC(Player player) {
		this();
		_player = player;
	}

	//gets a response from the NPC the player requested from
	public String getResponse() {
		String response = _greeting;
		if (_rightAnsCt == 0) {
			response += "" + _questions.get(0);
		}
		else if (_rightAnsCt == 1) {
			response += "" + _questions.get(1);
		}
		//message that is printed after the player has obtained both rewards
		else {
			response = "You have already answered all of my questions! "
			+ "Search elsewhere for a source of equipment. ";
		}
		return response;
	}

//NPC will add the reward equipment into the player's inventory if they correctly answer the question
	public String judge(String answer) {
		answer = answer.trim().toLowerCase();
		for (String i : _keywords.get(_rightAnsCt)) {
			if (answer.indexOf(i) >= 0) {
				_player.invent(_equipment.get(_rightAnsCt));
				System.out.println(_equipment.get(_rightAnsCt).about());
				_rightAnsCt += 1;
				return _winMsg;
			}
		}
		return _lossMsg);
	}

}
