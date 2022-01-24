/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

import java.util.*;

public class Ducky extends Monster {

	public String _name = "";
	private ArrayList<String> _questions;
	private ArrayList<ArrayList<String>>  _keywords;
	private int _whichQuest;

	public Ducky() {
		_lives = 10;
		_strength = 100;
		_defense = 75;
		_attackRating = 1;
		_whichQuest = 0;
		_questions = new ArrayList<String>(5);
		_keywords = new ArrayList<ArrayList<String>>(5);
		_questions.add("Question 1");
		_questions.add("Question 2");
		_questions.add("Question 3");
		_questions.add("Question 4");
		_questions.add("Question 5");
		ArrayList<String> answers1 = new ArrayList<String>(1);
		answers1.add("answer 1");
		ArrayList<String> answers2 = new ArrayList<String>(1);
		answers2.add("answer 2");
		ArrayList<String> answers3 = new ArrayList<String>(1);
		answers3.add("answer 3");
		ArrayList<String> answers4 = new ArrayList<String>(1);
		answers4.add("answer 4");
		ArrayList<String> answers5 = new ArrayList<String>(1);
		answers5.add("answer 5");
		_keywords.add(answers1);
		_keywords.add(answers2);
		_keywords.add(answers3);
		_keywords.add(answers4);
		_keywords.add(answers5);

	}

	public Ducky(String name) {
		this();
		_name = name;
	}

	public String getName() {
		return _name;
	}

	//for the twist, in order to pose a question to the player
	public String getQuestion() {
		final int numQuests = 5;
		_whichQuest = (int)(Math.random() * numQuests);
		String quest = "";

		quest = _questions.get(_whichQuest) + "\nYou have 5 seconds to answer.\n";
		return quest;
	}

	//for the twist, in order to determine the correctness of the player's answer
	public String judge(String answer, Ducky ducky, Player player) {
		String s = "";
		answer = answer.trim().toLowerCase();
		for (String k : _keywords.get(_whichQuest)) {
			if (answer.indexOf(k) >= 0) {
				_lives -= 1;
				s = "ur right \n";
				s += "\n" + player.getName() + "'s Lives: " + player.getLives() + "\n";
    				s += ducky.getName() + "'s Lives: " + ducky.getLives() + "\n";
				return s;
			}
		}
		player._lives -= 1;
		s = "AHHHHHHH \n";
		s += "\n" + player.getName() + "'s Lives: " + player.getLives() + "\n";
		s += ducky.getName() + "'s Lives: " + ducky.getLives() + "\n";
		return s;
	}

	//for when the player asks for help
	public String getResponse() {
		final int numResps = 6;
		int whichResp = (int)(Math.random() * numResps);
		String resp = "";

		if (whichResp == 0) {
			resp = "Think S I M P L E. Think S M A R T.";
		}

		else if (whichResp == 1) {
			resp = "Good question, Thinker.";
		}

		else if (whichResp == 2) {
			resp = "Sounds like this calls for a value-added key!";
		}

		else if (whichResp == 3) {
			resp = "Smells like a QAF post!";
		}

		else if (whichResp == 4) {
			resp = "Try it out!";
		}

		else if (whichResp == 5) {
			resp = "( ͠° ͟ʖ ͡° )";
		}

		return resp;
	}

}
