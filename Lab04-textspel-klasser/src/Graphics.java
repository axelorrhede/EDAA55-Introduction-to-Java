import se.lth.cs.pt.window.SimpleWindow;

public class Graphics {
	private SimpleWindow w;
	private int width;
	private int blockSize;
	private int height;

	public Graphics(int w, int h, int bs) {
		width = w;
		blockSize = bs;
		height = h;
		this.w = new SimpleWindow(width * blockSize, height * blockSize, "CloneHero");
	}

	public void square(int x, int y, java.awt.Color color) {
		w.setLineColor(color);
		w.moveTo(x*blockSize, y*blockSize);
		w.lineTo(x*blockSize, y*blockSize + blockSize*2);
		w.lineTo(x*blockSize + blockSize*2, y*blockSize + blockSize*2);
		w.lineTo(x*blockSize + blockSize*2, y*blockSize);
		w.lineTo(x*blockSize, y*blockSize);
	}

	
	public void block(int x, int y, java.awt.Color color) {
		int left = x * blockSize;
		int right = left + blockSize - 1;
		int top = y * blockSize;
		int bottom = top + blockSize - 1;
		w.setLineColor(color);
		for (int row = top; row <= bottom; row++) {
			w.moveTo(left, row);
			w.lineTo(right, row);
		}
	}
	public void rectangle(int x, int y, int width, int height, java.awt.Color color) {
		for (int yy = y; yy < y + height; yy++) {
			for (int xx = x; xx < x + width; xx++) {
				block(xx, yy, color);
			}
		}

	}
	public void text(java.lang.String txt, int x, int y, java.awt.Color color) {
		w.setLineColor(color);
		w.moveTo(x,y);
		w.writeText(txt);
	}

	public SimpleWindow getWindow() {
		return w;
	}
}

