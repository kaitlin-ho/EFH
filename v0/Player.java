import java.util.ArrayList;

public class Player extends Adversary{

	public String _name = "";
	public Ducky _ducky = new Ducky();
	public ArrayList<Equipment> _equipment = new ArrayList<Equipment>(6);

	public void setName(String name) {
		_name = name;
	}

	public void setDuckyName(String name) {
		_ducky.setName(name);
	}

}
