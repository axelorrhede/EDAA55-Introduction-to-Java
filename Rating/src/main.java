
public class main {

	public static void main(String[] args) {
		PhoneDirectory pb = new PhoneDirectory();
		pb.addPerson("Sandra", "123");
		pb.addPerson("Alice", "333");
		pb.addPerson("Peter", "1234");
		pb.addPerson("Anna", "6453");
		pb.sortPersons();
		System.out.println(pb);
	}

}
