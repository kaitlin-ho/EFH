public class Shield extends Equipment{

  public Shield() {
    _name = "Shield";
    _strengthAlt = 10;
    _defenseAlt = 10;
  }

  public static String about() {
    return "The shield gives you a chance to block your opponent's attack.";
  }
}
