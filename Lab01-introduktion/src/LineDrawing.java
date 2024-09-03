import se.lth.cs.pt.window.SimpleWindow;

public class LineDrawing {
	public static void main(String[] args) {
		double x;
		double y;
		SimpleWindow w = new SimpleWindow(500, 500, "LineDrawing");
		w.moveTo(0, 0);
		while (true) {
			w.waitForMouseClick();
			x = w.getMouseX();
			y = w.getMouseY();
			w.lineTo((int) (x), (int) (y));

		}
	}
}
