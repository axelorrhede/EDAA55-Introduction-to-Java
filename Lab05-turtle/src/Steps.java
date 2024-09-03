import se.lth.cs.pt.window.SimpleWindow;
import java.util.Random;

public class Steps {
 	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "Steps");
		Turtle t = new Turtle(w, 300, 300);
		Random rand = new Random();
		t.penDown();
		for (int i = 0; i < 1000; i++) {
			t.forward(rand.nextInt(10));
			SimpleWindow.delay(10);
			if (t.getX()< 50) {
				t.turnNorth();
				t.left(270);
			}else if (t.getX()> 550) {
				t.turnNorth();
				t.left(90);
			}else if (t.getY()> 550) {
				t.turnNorth();
			}else if (t.getY()< 50) {
				t.turnNorth();
				t.left(180);
			} else {
				t.left(rand.nextInt(361)-180);
			}
		}
	}
}
//Lokal variabel i används i loopen och får bara användas i det blocket
