import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class MyOwnCoolSquares2 {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");
		Square sq = new Square(250, 250, 100);
		sq.draw(w);
		int currentX = 250;
		int oldY = 250;
		int moveX = 0;
		int moveY = 0;
		while (true) {
			w.waitForMouseClick();
			int x = w.getClickedX();
			int y = w.getClickedY();
			moveX = (x - currentX) / 50;
			moveY = (y - oldY) / 50;
			for (int i = 0; i < 50; i++) {
				sq.erase(w);
				sq.move(moveX, moveY);
				sq.draw(w);
				SimpleWindow.delay(5);
				currentX = currentX + moveX;
				oldY = oldY + moveY;
			}

		}
	}
}
