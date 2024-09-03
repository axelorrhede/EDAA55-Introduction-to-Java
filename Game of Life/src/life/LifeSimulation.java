package life;

public class LifeSimulation {

	private LifeBoard board;

	/** Skapar en ny Life-simulering, som använder brädet 'board'. */
	public LifeSimulation(LifeBoard board) {
		this.board = board;
	}

	private int countNeighbors(int row, int col) {
		int s = 0;
		for (int rr = row - 1; rr <= row + 1; rr++) {
			for (int cc = col - 1; cc <= col + 1; cc++) {
				if (board.get(rr, cc) && !(cc == col && rr == row)) {
					s++;
				}
			}
			
		}
		return s;
	}
	
	/** Räknar fram nästa generation i simuleringen. */
	public void newGeneration() {
		// individ med 2-3 grannar: fortlevnad
		// individ med < 2 grannar eller > 3 grannar: dödsfall
		// tom ruta med exakt 3 grannar: födelse

		board.increaseGeneration();
		
		boolean[][] next = new boolean[board.getRows()][board.getCols()];

		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getCols(); c++) {
				int n = countNeighbors(r, c);
				if (n >= 2 && n <= 3 && board.get(r, c)) {
					next[r][c] = true;
					// individ med 2-3 grannar: fortlevnad
				} else if ((n < 2 || n > 3) && board.get(r, c)) {
					// individ med < 2 grannar eller > 3 grannar: dödsfall
					next[r][c] = false;
				} else if (n == 3 && !board.get(r, c)) {
					next[r][c] = true;
				}
			}
		}
		
		for (int r = 0; r < board.getRows(); r++) {
			for (int c = 0; c < board.getCols(); c++) {
				board.put(r, c, next[r][c]);
			}
		}
	}
	
	/** Byter värde i rutan (row,col). */
	public void flip(int row, int col) {
		boolean q = board.get(row, col);
		board.put(row, col, !q);
	}
}
