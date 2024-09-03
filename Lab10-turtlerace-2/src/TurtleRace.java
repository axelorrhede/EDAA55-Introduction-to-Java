import java.util.ArrayList;
import java.util.Random;

public class TurtleRace {
	public static void main(String[] args) {
		RaceWindow w = new RaceWindow();
		ArrayList<RaceTurtle> racers = new ArrayList<RaceTurtle>();
		ArrayList<RaceTurtle> finishers = new ArrayList<RaceTurtle>();
		Random rand = new Random();
		int vem;
		for(int i = 0; i<9;i++) {
			vem = rand.nextInt(3);
			if(vem == 0) {
				racers.add(new MoleTurtle(w, i));
			}
			if(vem == 1) {
				racers.add(new DizzyTurtle(w, i));
			}
			if(vem == 2) {
				racers.add(new AbsentMindedTurtle(w, i));
			}
		}
		// Turtle 0 racear inte, och vi kan lösa orättvisan genom att tilldela i nedan slumpmässigt mellan 1 och 8
		w.waitForMouseClick();
		while(finishers.size() < 8) {
			for(int i = 1; i<racers.size();i++) {
				racers.get(i).raceStep();
				if (racers.get(i).getX()>RaceWindow.X_END_POS && !finishers.contains(racers.get(i))) {
					finishers.add(racers.get(i));
					racers.remove(racers.get(i));
					i--;

				}
			}
		RaceWindow.delay(10);
		
		
		
		}
	
		System.out.println("På plats 1: " + finishers.get(0));
		System.out.println("På plats 2: " + finishers.get(1));
		System.out.println("På plats 3: " + finishers.get(2));
		

	}

}
