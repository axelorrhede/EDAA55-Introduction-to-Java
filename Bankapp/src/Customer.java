import java.util.Random;

public class Customer {
	private String name;
	private Long idNr;
	private int customerNbr;
	Random rand = new Random();

	public Customer(String name, Long idNr) {
		this.name = name;
		this.idNr = idNr;
		customerNbr = rand.nextInt(Integer.MAX_VALUE-1000)+1000;

	}

	public String getString() {
		return this.name;
	}

	public Long getidNr() {
		return this.idNr;
	}

	public int getcustomerNbr() {
		return this.customerNbr;
	}
	
	public String toString() {
		return name + "\nid: " + idNr + "\nkundnummer: " + customerNbr;
	}


}
