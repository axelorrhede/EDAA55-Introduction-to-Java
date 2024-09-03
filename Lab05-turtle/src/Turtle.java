import se.lth.cs.pt.window.SimpleWindow;

public class Turtle {
	private double x;
	private double y;
	private double angle;
	private SimpleWindow w;
	protected boolean draw;
	

	/** Skapar en sköldpadda som ritar i ritfönstret w. Från början 
	    befinner sig sköldpaddan i punkten x, y med pennan lyft och 
	    huvudet pekande rakt uppåt i fönstret (i negativ y-riktning). */
	public Turtle(SimpleWindow w, int x, int y) {
		this.x = x;
		this.y = y;
		angle = Math.PI/2;
		this.w = w;
		draw = false;

				
	}

	/** Sänker pennan. */
	public void penDown() {
		draw = true;

	}
	
	/** Lyfter pennan. */
	public void penUp() {
		draw = false;

	}
	
	/** Går rakt framåt n pixlar i den riktning huvudet pekar. */
	public void forward(int n) {
		w.moveTo((int)(x), (int)(y));
		x = x + Math.cos(angle)*n;
		y = y - Math.sin(angle)*n;
		if (draw) {
			w.lineTo((int)(x), (int)(y));
		}else {
			w.moveTo((int)(x), (int)(y));
		}
		
		

	}

	/** Vrider beta grader åt vänster runt pennan. */
	public void left(int beta) {
		angle = angle+beta*Math.PI/180;

	}

	/** Går till punkten newX, newY utan att rita. Pennans läge (sänkt
	    eller lyft) och huvudets riktning påverkas inte. */
	public void jumpTo(int newX, int newY) {
		w.moveTo(newX, newY);
		x = newX;
		y = newY;
	
	}

	/** Återställer huvudriktningen till den ursprungliga. */
	public void turnNorth() {
		angle = Math.PI/2;
	}

	/** Tar reda på x-koordinaten för sköldpaddans aktuella position. */
	public int getX() {
		return (int)(x+0.5);
	}

 	/** Tar reda på y-koordinaten för sköldpaddans aktuella position. */
	public int getY() {
		return (int)(y+0.5);
	}
  
	/** Tar reda på sköldpaddans riktning, i grader från den positiva X-axeln. */
 	public int getDirection() {
 		return (int)(angle*180/Math.PI);
	}
}

//Attribut angle är private och får bara användas i klassen Turtle
//Parametern newX kommer kunna användas i metoden jumpTo
