import java.util.Scanner;

import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeTurtle {
	public static void main(String[] args) {
		Scanner mascan = new Scanner(System.in);
		SimpleWindow mawindow = new SimpleWindow(600,600,"TurtleMaze");
		Turtle maturtle = new Turtle(mawindow,0,0);
		MazeWalker maboii = new MazeWalker(maturtle);
		Maze mamaze = new Maze(mascan.nextInt());
		maboii.walk(mamaze,mawindow);
	}

}
 