import se.lth.cs.pt.maze.Maze;
import se.lth.cs.pt.window.SimpleWindow;

public class MazeWalker {
	private Turtle turtle;
	private int sum;

	public MazeWalker(Turtle turtle) {
		this.turtle = turtle;
	}

	/**
	 * Låter sköldpaddan vandra genom labyrinten maze, från ingången till utgången.
	 */
	public void walk(Maze maze,SimpleWindow w) {
		maze.draw(w);
		turtle.jumpTo(maze.getXEntry(),maze.getYEntry());
		turtle.penDown();
		while(!(maze.atExit(turtle.getX(),turtle.getY()))) {
			SimpleWindow.delay(1);
			if(maze.wallAtLeft(turtle.getDirection(),turtle.getX(),turtle.getY()) && !(maze.wallInFront(turtle.getDirection(),turtle.getX(),turtle.getY()))) {
				turtle.forward(1);
				sum++;
			}else if(maze.wallInFront(turtle.getDirection(),turtle.getX(),turtle.getY())){
				turtle.left(270);
				/*if(maze.wallInFront(turtle.getDirection(),turtle.getX(),turtle.getY())) {
					turtle.left(270);
				}*/
				turtle.forward(1);
				sum++;
			}else {
				turtle.left(90);
				turtle.forward(1);
				sum++;
	
			}
		}
		System.out.println(sum);
	}
}
