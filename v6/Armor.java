/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/

public class Armor extends Equipment{

  public Armor() {
    _name = "Armor";
    _strengthAlt = 13;
    _defenseAlt = 64;
  }

  public String about() {
    String s = "";
    s = "Armor allows you to take less damage.\n";
    s += "Stats: strength +13, defense +64";
    return s;
  }

}
