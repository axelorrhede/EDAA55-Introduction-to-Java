
public class MoleTurtle extends RaceTurtle {
	/**
	 * Skapar en sköldpadda som ska springa i fönstret w och som har start- nummer
	 * nbr. Sköldpaddan startar med pennan nere och nosen vänd åt höger.
	 */
	MoleTurtle(RaceWindow w, int nbr) {
		super(w, nbr);
	}

	/**
	 * Låter sköldpaddan gå framåt ett steg. Stegets längd ges av ett slumptal
	 * (heltal) mellan 1 och 6.
	 */
	public void raceStep() {
		if (rand.nextDouble() < 0.2 && draw) {
			penUp();
		}
		if (rand.nextDouble() < 0.2 && !draw) {
			penDown();
		}
		super.raceStep();
	}

	/**
	 * Returnerar en läsbar representation av denna RaceTurtle, på formen "Nummer x"
	 * där x är sköldpaddans startnummer.
	 */
	public String toString() {
		return ("Nummer " + nbr + " - MoleTurtle");
	}

}
