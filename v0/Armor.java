public class Armor extends Equipment{

  public Armor() {
    _name = "Armor";
    _strengthAlt = 10;
    _defenseAlt = 10;
  }

  public static String about() {
    return "Armor allows you to take less damage.";
  }

}
