/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class Sword extends Equipment {

	public Sword() {
		_name = "Sword";
		_strengthAlt = 64;
		_defenseAlt = 28;
	}

	public String about() {
		String s = "";
		s = "The Sword makes your attacks more powerful.\n";
		s += "Stats: strength +64, defense +28";
		return s;
	}

}
