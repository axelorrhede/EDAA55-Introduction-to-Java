import se.lth.cs.pt.window.SimpleWindow;
import java.util.Random;

public class TwoSteppers {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "Steps");
		Turtle t1 = new Turtle(w, 350, 350);
		Turtle t2 = new Turtle(w, 250, 250);
		Random rand = new Random();
		t1.penDown();
		t2.penDown();
		while (Math.sqrt((t1.getX() - t2.getX()) * (t1.getX() - t2.getX())
				+ (t1.getY() - t2.getY()) * (t1.getY() - t2.getY())) >= 50) {
			t1.forward(rand.nextInt(10));
			SimpleWindow.delay(0);
			if (t1.getX() < 50) {
				t1.turnNorth();
				t1.left(270);
			} else if (t1.getX() > 550) {
				t1.turnNorth();
				t1.left(90);
			} else if (t1.getY() > 550) {
				t1.turnNorth();
			} else if (t1.getY() < 50) {
				t1.turnNorth();
				t1.left(180);
			} else {
				t1.left(rand.nextInt(361) - 180);
			}
			t2.forward(rand.nextInt(10));
			if (t2.getX() < 50) {
				t2.turnNorth();
				t2.left(270);
			} else if (t2.getX() > 550) {
				t2.turnNorth();
				t2.left(90);
			} else if (t2.getY() > 550) {
				t2.turnNorth();
			} else if (t2.getY() < 50) {
				t2.turnNorth();
				t2.left(180);
			} else {
				t2.left(rand.nextInt(361) - 180);
			}
		}
	}
}
