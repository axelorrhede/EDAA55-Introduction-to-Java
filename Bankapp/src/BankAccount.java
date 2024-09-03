import java.util.Random;

public class BankAccount {

	private double amount;
	private int accountNbr;
	private Random rand = new Random();
	private Customer holder; 

	/**
	 * Skapar ett nytt bankkonto åt en innehavare med namn 'holderName' och id
	 * 'holderId'. Kontot tilldelas ett unikt kontonummer och innehåller
	 * inledningsvis 0 kr.
	 */
	public BankAccount(String holderName, long holderId) {
		holder = new Customer(holderName, holderId); 
		amount = 0;
		accountNbr = rand.nextInt(Integer.MAX_VALUE - 1000) + 1000;

	}

	/**
	 * Skapar ett nytt bankkonto med innehavare 'holder'. Kontot tilldelas ett unikt
	 * kontonummer och innehåller inledningsvis 0 kr.
	 */

	public BankAccount(Customer holder) {
		this.holder = holder;
		accountNbr = rand.nextInt(Integer.MAX_VALUE - 1000) + 1000;
		amount = 0;

	}

	/** Tar reda på kontots innehavare. */
	public Customer getHolder() {
		return this.holder;
				
	}

	/** Tar reda på det kontonummer som identifierar detta konto. */

	public int getAccountNumber() {
		return accountNbr;

	}

	/** Tar reda på hur mycket pengar som finns på kontot. */
	public double getAmount() {
		return amount;

	}

	/** Sätter in beloppet 'amount' på kontot. */
	public void deposit(double amount) {
		this.amount += amount;

	}

	/**
	 * Tar ut beloppet 'amount' från kontot. Om kontot saknar täckning blir saldot
	 * negativt.
	 */
	public void withdraw(double amount) {
		this.amount -= amount;

	}

	/** Returnerar en strängrepresentation av bankkontot. */
	public String toString() {
		return  holder.toString() + "\nSaldo: " + amount + "\nKontoid :" + accountNbr;  

	}
	
	public boolean isHolder(long idNbr) {
		return (idNbr == holder.getidNr());
		
	}

}
