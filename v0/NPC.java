public class NPC extends Chatbox{

	protected int _rightAnsCt;
	protected ArrayList<String> _questions;
	protected ArrayList<String> _keywords;

	public NPC() {
		_rightAnsCt = 0;
		_questions = new ArrayList<String>(2)
		_keywords = new ArrayList<String>(2)
	}

	public String getResponse() {
		String response = "Hello!";
		if (_rightAnsCt == 0) {
			response += " " + _questions.get(0);
		}
		else if (_rightAnsCt == 1) {
			response += " " + _questions.get(1);
		}
		else {
			response += " You have already answered all of my questions! " 
			+ "Search elsewhere for a source of equipment."
		}
	}

	public void judge(String answer) {

	}

}
