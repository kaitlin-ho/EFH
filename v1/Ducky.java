public class Ducky extends Chatbox{

	public Ducky() {
		super();
	}

	public Ducky(String name) {
		this();
		_name = name;
	}

	public String _name = "";

	public String getName() {
		return _name;
	}

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
