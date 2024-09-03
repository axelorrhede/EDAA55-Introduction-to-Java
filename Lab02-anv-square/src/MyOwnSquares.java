import se.lth.cs.pt.window.SimpleWindow;
import se.lth.cs.pt.square.Square;

public class MyOwnSquares {
	public static void main(String[] args) {
		SimpleWindow w = new SimpleWindow(600, 600, "DrawSquare");
		Square sq = new Square(250, 250, 100);
		sq.draw(w);
		int x = 250;
		int y = 250;
		while (true) {
			w.waitForMouseClick();
			sq.erase(w);
			sq.move(w.getClickedX() - x,w.getClickedY()- y);
			x = w.getClickedX();
			y = w.getClickedY();
			sq.draw(w);
						
		}	
	}
}
