
	Bank mybank = new Bank("Swedbank");
		System.out.println("Add account " + mybank.addAccount("Eva", 7474783));
		System.out.println("Find Holder " + mybank.findHolder(7474783));
		System.out.println("Get All accounts " + mybank.getAllAccounts());
		System.out.println("Get accountnumber " + mybank.findAccountsForHolder(7474783).get(0).getAccountNumber());
		//Hur man hittar ett accountnumber: mybank.findAccountsForHolder(7474783).get(0).getAccountNumber()
		System.out.println("Find by number:  "+ mybank.findByNumber(mybank.findAccountsForHolder(7474783).get(0).getAccountNumber()));
		
		System.out.println("Find by part of name:  "+ mybank.findByPartofName("EV"));

		System.out.println("Remove account "+ mybank.removeAccount(mybank.findAccountsForHolder(7474783).get(0).getAccountNumber()));
