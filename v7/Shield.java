/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class Shield extends Equipment{

  public Shield() {
    _name = "Shield";
    _strengthAlt = 29;
    _defenseAlt = 55;
  }

  public String about() {
    String s = "";
    s = "The Shield gives you a change to block your opponent's attack.\n";
    s += "Stats: strength +29, defense +55";
    return s;
  }
}
