import se.lth.cs.pt.window.SimpleWindow;

public class Game {
	Graphics game = new Graphics(100, 40, 20);
	private int score = 0;
	private SimpleWindow window = game.getWindow();
	private int point;
	private boolean pressed;
	private int missar = 0;
	private int miss = 0;

	public Game() {
		game.rectangle(0, 0, 100, 40, Colors.BLACK);
		game.square(10, 37, Colors.RED);
		new Thread(() -> thing()).start();
		while (true) {
			window.waitForKey();
			score = score + point;
			missar = missar + miss; 
			pressed = true;
		}

	}

	public void thing() {
		while (true) {
			game.rectangle(0, 0, 100, 40, Colors.BLACK);
			game.square(10, 37, Colors.RED);
			game.text("Poäng: " + score, 1300,20, Colors.WHITE);
			game.text("Missar: " + missar, 1300,40, Colors.WHITE);
			game.text("All time highscore: 9800", 1300,60, Colors.WHITE);
			for (int i = 0; i < 40; i++) {
				game.rectangle(10, i, 2, 2, Colors.RED);
				SimpleWindow.delay(100000/(score+1000));
				game.rectangle(10, i, 2, 2, Colors.BLACK);
				if (i<33) {
					point = 0;
					miss = 1;
				}else if (i<37) {
					point = (i-33)*100;
					miss = 0;
				}else {
					point = 0;
					miss = 1;
				}
				if (pressed) {
					pressed = false;
					break;
				}

			}

			if (missar >= 20) {
				game.text("Du har förlorat! Ditt resultat blev; " + score, 700,400, Colors.WHITE);
				break;
			}
		}
	}

}
