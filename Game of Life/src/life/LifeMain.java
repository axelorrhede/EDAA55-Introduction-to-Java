package life;

public class LifeMain {
	private static final int BOARD_ROWS = 30;
	private static final int BOARD_COLS = 50;

	private static final int CLICKED_CELL = 1;
	private static final int CLICKED_NEXT = 2;
	private static final int CLICKED_QUIT = 3;
	
	public static void main(String[] args) {
		LifeBoard board = new LifeBoard(BOARD_ROWS, BOARD_COLS);
		LifeView window = new LifeView(board);
		LifeSimulation sim = new LifeSimulation(board);
		window.drawBoard();

		while (true) {
			int cmd = window.getCommand();

			if (cmd == CLICKED_CELL) {
				int row = window.getRow();
				int col = window.getCol();
				sim.flip(row, col);
			} else if (cmd == CLICKED_NEXT) {
				sim.newGeneration();
			} else if (cmd == CLICKED_QUIT) {
				System.exit(0);
			}

			window.update();
		}
	}
}
