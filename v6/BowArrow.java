/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class BowArrow extends Equipment{

  public BowArrow(){
    _name = "Bow and Arrow";
    _strengthAlt = 173;
    _defenseAlt = 15;
  }

  public String about() {
    String s = "";
    s = "The Bow and Arrow adds a new weapon to your arsenal of ways to damage your foe.\n";
    s += "Stats: strength +173, defense +15";
    return s;
  }

}
