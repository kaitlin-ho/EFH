/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/
import java.util.*;

public class Test {

  private static void wait(int millis)
  {
    try {
      Thread.sleep(millis);
    }
    catch (InterruptedException e) {
    }
  }

  public static void main(String[] args){
    String name = "";
    System.out.println("hello. enter your name:");
    Scanner in = new Scanner(System.in);
    wait(5000);
    System.out.println("wait is up");
    if (Thread.interrupted()) {
      name = in.nextLine();
    }
  //  if (name.length() > 0) {System.out.println(name);}
    System.out.println("hello.");
  }

}
