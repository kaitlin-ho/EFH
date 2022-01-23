/*
Erica's Fans and Hugo (EFH):
Hugo Jenkins, Ariella Katz, Kaitlin Ho, Boary, Tom, Apple
*/
import java.util.*;

public class Test {

  public static void delay(int milliseconds) {
    long beginTimer = System.currentTimeMillis();
    while (System.currentTimeMillis() - beginTimer < milliseconds) {
      continue;
    }
  }


  public static void type(String s){
    String punc = ",.?!-";
    for(int i = 0; i < s.length(); i++){
      System.out.print(s.charAt(i));
      if (punc.indexOf(s.charAt(i)) >= 0) {
        delay(400);
      }
      else {
        delay(50);
      }
    }
    System.out.println("");
  }

  public static void italicizeType(String s){
    String punc = ",.?!-";
    for(int i = 0; i < s.length(); i++){
          System.out.print("\u001b[3m" + s.charAt(i) + "\u001b[0m");
          if (punc.indexOf(s.charAt(i)) >= 0) {
            delay(400);
          }
          else {
            delay(50);
          }
    }
  }


  public static void main(String[] args){

    italicizeType("hahah. trash");



  }

}
