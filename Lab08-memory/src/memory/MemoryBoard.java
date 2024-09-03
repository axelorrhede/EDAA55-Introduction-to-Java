package memory;

import java.util.Random;

public class MemoryBoard {
	public MemoryCardImage[][] board;
	public MemoryCardImage cards[];
	public Random rand = new Random();
	public int size;
	public boolean[][] turned;

	/**
	 * Skapar ett memorybräde med size * size kort. backFileName är filnamnet för
	 * filen med baksidesbilden. Vektorn frontFileNames innehåller filnamnen för
	 * frontbilderna.
	 */
	public MemoryBoard(int size, String backFileName, String[] frontFileNames) {
		board = new MemoryCardImage[size][size];
		this.size = size;
		turned = new boolean[size][size];
		createCards(backFileName, frontFileNames);
		

	}

	/*
	 * Skapar size * size / 2 st memorykortbilder. Placerar ut varje kort på två
	 * slumpmässiga ställen på spelplanen.
	 */
	private void createCards(String backFileName, String[] frontFileNames) {
		int r;
		int c;
		boolean in = false;
		boolean in2 = false;
		MemoryCardImage[] cards = new MemoryCardImage[size * size / 2];
		for (int v = 0; v < (size * size / 2); v++) {
			cards[v] = new MemoryCardImage(frontFileNames[v], backFileName);
			in = false;
			while (!(in)) {
				c = rand.nextInt(size);
				r = rand.nextInt(size);
				if (board[r][c] == null) {
					board[r][c] = cards[v];
					in = true;
				}
			}
			in2 = false;
			while (!(in2)) {
				c = rand.nextInt(size);
				r = rand.nextInt(size);
				if (board[r][c] == null) {
					board[r][c] = cards[v];
					in2 = true;
				}
			}
		}
	}

	/** Tar reda på brädets storlek. */
	public int getSize() {
		return size;
	}

	/**
	 * Hämtar den tvåsidiga bilden av kortet på rad r, kolonn c. Raderna och
	 * kolonnerna numreras från 0 och uppåt.
	 */
	public MemoryCardImage getCard(int r, int c) {
		if (turned[r][c]) {
			return board[r][c];
		} else {
			return board[r][c];
		}
	}

	/** Vänder kortet på rad r, kolonn c. */
	public void turnCard(int r, int c) {
		if (turned[r][c]) {
			turned[r][c] = false;
		} else {
			turned[r][c] = true;
		}
	}

	/** Returnerar true om kortet r, c har framsidan upp. */
	public boolean frontUp(int r, int c) {
		return turned[r][c];
	}

	/**
	 * Returnerar true om det är samma kort på rad r1, kolonn c2 som på rad r2,
	 * kolonn c2.
	 */
	public boolean same(int r1, int c1, int r2, int c2) {
		if (board[r1][c1] == board[r2][c2]) {
			return true;
		} else {
			return false;
		}

	}

	/** Returnerar true om alla kort har framsidan upp. */
	public boolean hasWon() {
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (!(turned[r][c])) {
					return false;
				}
			}
		}
		return true;
	}
}
