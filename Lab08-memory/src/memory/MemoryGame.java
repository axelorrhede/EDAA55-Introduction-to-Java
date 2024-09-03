package memory;

public class MemoryGame {
	String[] frontFileNames = { "can.jpg", "flopsy_mopsy_cottontail.jpg", "friends.jpg", "mother_ladybird.jpg",
			"mr_mcgregor.jpg", "mrs_rabbit.jpg", "mrs_tittlemouse.jpg", "radishes.jpg" };
	String backFileName = "back.jpg";
	public MemoryBoard board;
	public MemoryWindow window;
	public int r1;
	public int c1;
	public int r2;
	public int c2;
	public int score = 0;

	public static void main(String[] args) {
		MemoryGame game = new MemoryGame();

	}

	public MemoryGame() {
		board = new MemoryBoard(4, backFileName, frontFileNames);
		window = new MemoryWindow(board);
		window.drawBoard();
		while (!(board.hasWon())) {
			window.waitForMouseClick();
			r1 = window.getMouseRow();
			c1 = window.getMouseCol();
			while(board.frontUp(r1, c1)) {
				window.waitForMouseClick();
				r1 = window.getMouseRow();
				c1 = window.getMouseCol();
			}
			board.turnCard(r1, c1);
			window.drawBoard();
			
			
			window.waitForMouseClick();
			r2 = window.getMouseRow();
			c2 = window.getMouseCol();
			while(board.frontUp(r2, c2)) {
				window.waitForMouseClick();
				r2 = window.getMouseRow();
				c2 = window.getMouseCol();
			}
			board.turnCard(r2, c2);
			window.drawBoard();
			MemoryWindow.delay(800);
			if (!(board.same(r1, c1, r2, c2))) {
				board.turnCard(r1, c1);
				board.turnCard(r2, c2);
				score++;
				window.drawBoard();
			}
		}
		System.out.print(score);

	}
}
