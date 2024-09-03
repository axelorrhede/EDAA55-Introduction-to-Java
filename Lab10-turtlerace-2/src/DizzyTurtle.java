
public class DizzyTurtle extends RaceTurtle{
	int dizzyness;
	
	DizzyTurtle(RaceWindow w, int nbr){
		super(w, nbr);
		dizzyness = rand.nextInt(5) + 1;
	}
	
	public void raceStep() {
		if (rand.nextDouble()< 0.5) {
			left(dizzyness);
		}else {
			left(-dizzyness);
		}
		super.raceStep();
	}
	public String toString() {
		return ("Nummer " + nbr + " - DizzyTurtle (Yrsel: "+dizzyness+")");
	}
}
