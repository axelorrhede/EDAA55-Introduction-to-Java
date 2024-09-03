package cardreader;


public class Testprogram {
	private UserTable table;
	private User[] users;
	public static void main(String[] args) {
		Testprogram test = new Testprogram();
		//test.find();
		//User a = new User(1234,"Pelle Pellsson");
		//test.Add(a);
		//test.print();
		test.testa();
	}

	public Testprogram() {
		table = new UserTable();
		users = new User[1000];
	}
	public void find() {
		users[0] = table.find((long)(24073));
		users[1] = table.find((long)(24074));
		users[2] = table.findByName("Jens Holmgren");
		System.out.print(users[1]);
		System.out.print(users[2]);
	}
	
	public void print() {
		table.print();
	}
	public void Add(User a) {
		table.add(a);
	}
	public void testa() {
		System.out.print(table.testFind());
	}

	

}
