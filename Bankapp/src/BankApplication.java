import java.util.ArrayList;
import java.util.Scanner;

public class BankApplication {

	private static Scanner sc;
	private Bank bank;
	private int sum;

	public BankApplication() {
		sc = new Scanner(System.in);
		System.out.println("Välkommen till Orre & Svennis Bankapplikation");
		System.out.println("Vad vill ni döpa er bank till?");
		bank = new Bank(sc.nextLine());
		System.out.println("Välkommen till " + bank.toString());
		System.out.println("Vad önskar ni göra?");

		int val;
		boolean kor = true; // Boolean för att köra programmet
		while (kor) {
			intro();
			val = scanner(9);
			System.out.println("Du valde val: " + val);

			switch (val) {
			case 1:
				BankAccount bankkonto = getBankAccount();
				System.out.println(bankkonto.toString());
				break;
			case 2:
				findCustomer();
				break;
			case 3:
				deposit();
				break;
			case 4:
				withdraw();
				break;
			case 5:
				transfer();
				break;
			case 6:
				createAccount();
				break;
			case 7:
				removeAccount();
				break;
			case 8:
				showAccounts();
				break; 
			case 9: 
				System.out.println("Bankprogrammet avslutas."); // Lägg till "sparar" promt här
				kor = false;
				break;
			}
			System.out.println("Åtgärden slutförd");
			sc.nextLine();
			System.out.println("-----------------------------------------------------------------");



		}

	}

	public void intro() {
		System.out.println("Tryck 1 för att leta upp ett konto för en viss kontoinnehavare");
		System.out.println("Tryck 2 för att leta upp en kontoinnehavare");
		System.out.println("Tryck 3 för att sätta in pengar på ett nytt konto");
		System.out.println("Tryck 4 för att ta ut pengar från ett konto");
		System.out.println("Tryck 5 för att överföra pengar från ett konto till ett annat");
		System.out.println("Tryck 6 för att skapa ett nytt konto");
		System.out.println("Tryck 7 för att ta bort ett konto");
		System.out.println("Tryck 8 för att för att visa en lista på samtliga användare");
		System.out.println("Tryck 9 för att för att avsluta programmet");
		System.out.println("-----------------------------------------------------------------");

	}

	public BankAccount getBankAccount() {
		while(true) {
			System.out.println("Ange personnummer: ");
			long idNr = scanner(Long.MAX_VALUE);
			if (bank.findHolder(idNr) != null) {
				ArrayList<BankAccount> accounts = bank.findAccountsForHolder(idNr);
				for (int i = 0; i < accounts.size(); i++) {
					System.out.println("-----------------------------------------------------------------");
					System.out.println("Skriv " + (i + 1) + " för konto tillhörande: " + accounts.get(i));

				}
				return accounts.get(scanner(accounts.size()) - 1);
			}
			System.out.println("Detta personnummer har inget konto på vår bank");
		}
	}

	public Customer findCustomer() {
		sum=0;
		System.out.println("Mata in vilken person du vill söka efter");
		String person = scanner();
		System.out.println("Vem av dessa personer? " + bank.findByPartofName(person));
		ArrayList<Customer> customers = bank.findByPartofName(person);
		for (Customer customer : customers) {
			sum++;
			System.out.println("val " + sum + " är: " + customer);
		}

		return customers.get(scanner(customers.size()) - 1);

	}

	public void deposit() {
		BankAccount account = getBankAccount();
		System.out.println("Skriv in hur mycket pengar som ni önskar sätta in");
		account.deposit(scanner(Double.MAX_VALUE));
		System.out.println("Detta konto har nu: " + account.getAmount() + " SEK");

	}

	public void withdraw() {
		BankAccount account = getBankAccount();
		System.out.println("Hur mycket pengar önskar ni ta ut?");
		System.out.println("Ni kan som mest ha 1000 SEK i kredit.");
		account.withdraw(scanner(account.getAmount() + 1000.0)); // Max back 1000 kr
		System.out.println("Detta konto har nu: " + account.getAmount() + " SEK");

	}

	public void transfer() {
		System.out.println("Välj konto att överföra från");
		BankAccount account = getBankAccount();
		System.out.println("Detta konto har nu: " + account.getAmount() + " SEK");
		System.out.println("Hur mycket vill ni överföra?");
		double value = scanner(account.getAmount() + 1000.0);
		account.withdraw(value);
		System.out.println("Detta konto har nu: " + account.getAmount() + " SEK");
		System.out.println("Till vilken person vill ni överföra pengarna");
		BankAccount account2 = getBankAccount(); // Nytt kontoobjekt där man lägger till pengar
		account2.deposit(value);
		System.out.println("Detta konto har nu: " + account2.getAmount() + " SEK");

	}

	public void createAccount() {
		System.out.println("Ange namn: ");
		String name = scanner();
		System.out.println("Ange Personnummer: ");
		Long idNr = scanner(Long.MAX_VALUE);
		int accNBR = bank.addAccount(name, idNr); // Skapar ett konto och sparar ner kontonumret;

		System.out.println("Konto för " + name + " med personnummer: " + idNr + " har nu skapats");
		System.out.println("Kontonummer: " + accNBR);

	}

	public void removeAccount() {
		BankAccount account = getBankAccount();
		if (bank.removeAccount(account.getAccountNumber())) {
			System.out.println(
					"Kontot " + account.getAccountNumber() + " tillhörandes " + account.getHolder() + " och är nu borttaget");
		} else {
			System.out.println("Ett fel har uppstått, kontakta administratör");
		}

	}

	public int scanner(int goodNumbers) {
		while (true) {
			try {
				int val = 0;
				val = Integer.parseInt(sc.nextLine());
				if (val >= 0 && val <= goodNumbers) {
					return val;
				} else {
					System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				}

			} catch (NumberFormatException e) {
				System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				continue;

			}
		}
	}

	public double scanner(double goodNumbers) {
		while (true) {
			try {
				double val = 0;
				val = Double.parseDouble(sc.nextLine());
				if (val >= 0 && val <= goodNumbers) {
					return val;
				} else {
					System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				}

			} catch (NumberFormatException e) {
				System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				continue;

			}
		}
	}

	public String scanner() {
		while (true) {
			try {
				String val;
				val = sc.nextLine();
				return val;
			}

			catch (NumberFormatException e) {
				System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				continue;

			}
		}
	}

	public long scanner(long goodNumbers) {
		while (true) {
			try {
				long val = 0;
				val = Long.parseLong(sc.nextLine());
				if (val >= 0 && val <= goodNumbers) {
					return val;
				} else {
					System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				}

			} catch (NumberFormatException e) {
				System.out.println("Detta är dessvärre inte ett giltigt val. Försök igen");
				continue;

			}
		}
	}
	
	public void showAccounts() {
		System.out.println("Nedan visas en lista på samtliga konton i banken:");
		ArrayList<BankAccount> list = bank.getAllAccounts();
		for (BankAccount account: list) {
			System.out.println(account.toString());
		}
	}

}
