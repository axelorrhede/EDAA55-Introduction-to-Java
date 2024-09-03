 package life;

public class LifeBoard {

	private boolean [][] board;
	private int generation;

	/** Skapar en spelplan med rows rader och cols kolonner.
	    Spelplanen är från början tom, dvs alla rutorna är
	    tomma och generationsräknaren är 1. */	
	public LifeBoard(int rows, int cols) {
		board = new boolean[rows][cols];
		generation = 1;
	}

	/** Undersöker om det finns en individ i rutan med index row,col. */
	public boolean get(int row, int col) {
		if (row < 0 || col < 0 || row >= board.length || col >= board[0].length) {
			return false;
		} else {
			return board[row][col];
		}
	}

	/** Lagrar värdet val i rutan med index row,col. */
	public void put(int row, int col, boolean val) {
		board[row][col] = val;
	}

	/** Tar reda på antalet rader. */
	public int getRows() {
		return board.length;
	}

 	/** Tar reda på antalet kolonner. */
	public int getCols() {
		return board[0].length;
	}

	/** Tar reda på aktuellt generationsnummer. */
	public int getGeneration() {
		return generation;
	}

	/** Ökar generationsnumret med ett. */
	public void increaseGeneration() {
		generation++;
	}
}
