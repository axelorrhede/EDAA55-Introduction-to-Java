package pair;

import java.util.Random;

public class PairSet {
	private Pair[][] pairs;
	private static Random rand = new Random();
	private int rows;
	private int cols;


	/** Skapar en mängd av alla talpar (a,b) sådana att
        0 <= a < rows  och  0 <= b < cols */
	public PairSet(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		pairs = new Pair[rows][cols];
		for(int r=0;r<rows;r++) {
			for (int c = 0; c<cols; c++) {
				pairs[r][c] = new Pair(r,c);
			}
		}

	}

	/** Undersöker om det finns fler par i mängden. */
	public boolean more() {
		for(int r=0;r<rows;r++) {
			for (int c = 0; c<cols; c++) {
				if (pairs[r][c] != null) {
					return true;
				}
			}
		}
		return false;
	}

	/** Hämtar ett slumpmässigt valt talpar ur mängden. Mängden
	 	blir ett element mindre. Om mängden är tom returneras null. */
	public Pair pick() {
		while(more()) {
			int r = rand.nextInt(rows);
			int c = rand. nextInt(cols);
			if (pairs[r][c] != null) {
				Pair x = pairs[r][c];
				pairs[r][c] = null;
				return x;
			}
		}
		
		
		return null;
	}
}
