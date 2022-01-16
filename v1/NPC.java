import java.util.*;
public class NPC extends Chatbox{

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

	public String getResponse() {
		String response = _greeting;
		if (_rightAnsCt == 0) {
			response += " " + _questions.get(0);
		}
		else if (_rightAnsCt == 1) {
			response += " " + _questions.get(1);
		}
		else {
			response += " You have already answered all of my questions! "
			+ "Search elsewhere for a source of equipment.";
		}
		return response;
	}

	public void judge(String answer) {
		answer = answer.trim().toLowerCase();
		for (String i : _keywords.get(_rightAnsCt)) {
			if (answer.indexOf(i) >= 0) {
				System.out.println(_winMsg);
				_player.invent(_equipment.get(_rightAnsCt));
				System.out.println(_equipment.get(_rightAnsCt).about());
				_rightAnsCt += 1;
				return;
			}
		}
		System.out.println(_lossMsg);
	}

}
