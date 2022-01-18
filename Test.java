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
				System.out.println("times up");
	    }
	    catch (InterruptedException e) {
	    }
	}

	public static void main(String[] args) {
			System.out.println("hello: ");
			wait(2000);
			Scanner in = new Scanner(System.in);
			String line = in.nextLine();


	}
}
