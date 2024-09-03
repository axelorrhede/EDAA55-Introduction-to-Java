package cardgame;

import pair.PairSet;

public class Sannolikhet {

	public static void main(String[] args) {
		double max = 10000000;
		double sum = 0;
		int t = 0;
		for (int p = 0; p < max; p++) {
			PairSet cardDeck = new PairSet(4, 13);
			int n = 0;
			while (cardDeck.more()) {
				if (n == 3) {
					n = 0;
				}
				if (cardDeck.pick().second() == n) {
					t = 1;
					break;
				}
				n++;
			}
			if (t == 0) {
				sum = sum + 1;
			}
			t = 0;

		}
		System.out.print(sum / max);

	}

}
