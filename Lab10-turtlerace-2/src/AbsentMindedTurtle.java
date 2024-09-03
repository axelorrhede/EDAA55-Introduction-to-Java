
public class AbsentMindedTurtle extends RaceTurtle{
	double absence;
	
	AbsentMindedTurtle(RaceWindow w, int nbr){
		super(w,nbr);
		absence = rand.nextDouble();
		
	}
	public void raceStep() {
		if (rand.nextDouble()>absence) {
			super.raceStep();
			//super.raceStep(); //boost!

		}

	}
	public String toString() {
		return ("Nummer " + nbr + " - AbsentMindedTurtle ("+(int)(absence*100)+"% Frånvarande)");
	}
}
