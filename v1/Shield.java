public class Shield extends Equipment{

  public Shield() {
    _name = "Shield";
    _strengthAlt = 30;
    _defenseAlt = 40;
  }

  public String about() {
    return "The shield gives you a chance to block your opponent's attack.";
  }
}
