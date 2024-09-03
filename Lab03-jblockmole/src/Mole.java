import se.lth.cs.pt.window.SimpleWindow;

public class Mole {
	private Graphics g = new Graphics(30, 50, 10);

	public static void main(String[] args) {
		Mole m = new Mole();
		m.drawWorld();
		m.drawGrass();
		m.drawSky();
		m.dig();
	}

	public void drawWorld() {
		g.rectangle(0, 0, 30, 10, Colors.SOIL);
		new Thread(() -> ufo()).start();
	}
	/** Ett grönt UFO, som rör sig en ruta var 20:e millisekund */
	private void ufo() {
		while (true) {
			for (int i = 0; i < 30; i++) {
				g.block(i, 2, Colors.GRASS);
				SimpleWindow.delay(20);
				g.block(i, 2, Colors.SKY);
			}
		}
	}

	public void drawGrass() {
		g.rectangle(0, 2, 30, 1, Colors.GRASS);
	}

	public void drawSky() {
		g.rectangle(0, 0, 30, 2, Colors.SKY);
	}

	public void dig() {
		int x = g.getWidth() / 2; // För att börja på mitten
		int y = g.getHeight() / 2;
		while (true) {
			g.block(x, y, Colors.MOLE);
			char key = g.waitForKey();
			if (y != 2) {
				g.block(x, y, Colors.TUNNEL);
			}
			else {
				g.block(x, y, Colors.GRASS);}
			if (key == 'w') {
				if (y > 2) {
					y = y - 1;
				}
			} else if (key == 'a') {
				if (x > 0) {
					x = x - 1;
				}
			} else if (key == 's') {
				if (y < 9) {
					y = y + 1;
				}
			} else if (key == 'd') {
				if (x < 29) {
					x = x + 1;
				}
			}
		}
	}
}
