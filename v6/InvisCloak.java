/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class InvisCloak extends Equipment {

  public InvisCloak() {
    _name = "Invisibility Cloak";
    _strengthAlt = 36;
    _defenseAlt = 32;
  }

  public String about() {
    String s = "";
    s = "The Invisibility Cloak makes it harder for your enemeny to land a powerful blow.\n";
    s += "Stats: strength +36, defense +32";
    return s;
  }
}
