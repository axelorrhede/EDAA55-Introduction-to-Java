
public class Rating {
	public static void main(String[] args) {
		int[] v = { 3, 2, 6, 7, 0, 88, 1, 5 };
		int[] list = new int[v.length];
		for (int i = 0; i < v.length; i++) {
			list[i] = Integer.MAX_VALUE;
		}
 
		int place = 0;
		for (int i = 0; i < v.length; i++) {
			for (int k = 0; k < v.length; k++) {
				if (!(v[i] >= list[k])) {
					place = k;
					break;
				}
			}
			for (int c = v.length - 1; c > place; c=c-1) {
				list[c] = list[c-1];
			}
			list[place] = v[i];

		}
		for (int i = 1; i < v.length; i++) {
			v[i] = list[i];
		}

	}
}
