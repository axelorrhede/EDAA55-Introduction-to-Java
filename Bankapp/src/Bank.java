import java.util.ArrayList;

public class Bank {
	private ArrayList<BankAccount> BankAccounts;
	String name;
	BankAccount goodAccount;

	public Bank(String name) {
		BankAccounts = new ArrayList<BankAccount>();
		this.name = name;
	}

	public int addAccount(String holderName, long idNr) {
		Boolean exist = true;
		while (exist) {
			exist = false;
			BankAccount newAccount = new BankAccount(holderName, idNr);
			goodAccount = newAccount;
			for (BankAccount account : BankAccounts) {
				if (newAccount.getAccountNumber() == account.getAccountNumber()) {
					exist = true;
					continue;
				}
			}
		}
		BankAccounts.add(goodAccount);
		return goodAccount.getAccountNumber();

	}

	public Customer findHolder(long idNr) {
		for (BankAccount account : BankAccounts) {
			if (account.isHolder(idNr)) {
				return account.getHolder();
			}
		}
		return null;
	}

	public boolean removeAccount(int accountNumber) {
		for (BankAccount account : BankAccounts) {
			if (account.getAccountNumber() == accountNumber) {
				BankAccounts.remove(account);
				return true;
			}
		}
		return false;

	}

	public ArrayList<BankAccount> getAllAccounts() {
		BankAccount account;
		for (int i = 0; i < BankAccounts.size(); i++) {
			for (int k = i; k < BankAccounts.size(); k++) {
				if (compareStrings(BankAccounts.get(k).getHolder().getString(),
						BankAccounts.get(i).getHolder().getString())) {
					account = BankAccounts.get(k);
					BankAccounts.remove(k);
					BankAccounts.add(i, account);
				}
			}
		}
		return BankAccounts;
	}

	private boolean compareStrings(String p1, String p2) {
		if (p1.length() < p2.length()) {
			for (int i = 0; i < p1.length(); i++) {
				if (p1.charAt(i) < p2.charAt(i)) {
					return true;
				}
				if (p1.charAt(i) > p2.charAt(i)) {
					return false;

				}
			}
			return true;
		} else {
			for (int i = 0; i < p2.length(); i++) {
				if (p1.charAt(i) < p2.charAt(i)) {
					return true;
				}
				if (p1.charAt(i) > p2.charAt(i)) {
					return false;

				}
			}
			return false;
		}
	}

	public BankAccount findByNumber(int accountNumber) {
		for (BankAccount account : BankAccounts) {
			if (account.getAccountNumber() == accountNumber) {
				return account;
			}
		}
		return null;
	}

	ArrayList<BankAccount> findAccountsForHolder(long idNr) {
		ArrayList<BankAccount> sameCustomer = new ArrayList<BankAccount>();
		for (BankAccount account : BankAccounts) {
			if (account.isHolder(idNr)) {
				sameCustomer.add(account);
			}
		}
		return sameCustomer;
	}

	public ArrayList<Customer> findByPartofName(String namePart) {
		ArrayList<Customer> customers = new ArrayList<Customer>();
		for (BankAccount account : BankAccounts) {
			if (account.getHolder().getString().toLowerCase().contains(namePart.toLowerCase())) {
				customers.add(account.getHolder());
			}
		}
		return customers;
	}
	
	public String toString() {
		return name;
	}

}
