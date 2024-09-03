
public class Person {
	String name;
	String phoneNbr;
	/** Skapar en person med namnet name och telefonnumret phoneNbr. */
	public Person(String name, String phoneNbr) {
		this.name = name;
		this.phoneNbr = phoneNbr;
	}
	

	/** Tar reda på personens namn. */
	public String getName() {
		return name;
	}

	/** Tar reda på personens telefonnummer. */
	public String getPhoneNbr() {
		return phoneNbr;
	}
	/** Skriver ut personen på formen (name=namnet, nbr=telefonnr). */
	public String toString() {
		return ("name="+name+", nbr="+phoneNbr);
	}
}
