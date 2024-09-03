import java.util.ArrayList;

public class PhoneDirectory {
	private ArrayList<Person> persons; // personerna i katalogen
	
	/** Skapar en tom telefonkatalog. */
	public PhoneDirectory() {
		persons = new ArrayList<Person>();
	}
	
	public void addPerson(String name, String nbr) {
		persons.add(new Person(name, nbr));
	}
	
	/** Sorterar den interna listan stigande med avseende på namn */
	public void sortPersons(){
		Person p;
		for(int i = 0; i<persons.size(); i++){
		    for(int k = i; k<persons.size(); k++){ //sus
		        if(compareStrings(persons.get(k).getName(), persons.get(i).getName())){
		            p = persons.get(k);
		        	persons.remove(k);
		            persons.add(i,p);
		        }
		    }
		}
	}
	public boolean compareStrings(String p1, String p2){
	    if (p1.length()<p2.length()){
	        for(int i = 0; i<p1.length();i++){
	            if (p1.charAt(i)<p2.charAt(i)){
	                return true;
	            }
	            if (p1.charAt(i)>p2.charAt(i)){
	                return false;
	            
	        }
	    }
	    return true;
	}else{
	    for(int i = 0; i<p2.length();i++){
	            if (p1.charAt(i)<p2.charAt(i)){
	                return true;
	            }
	            if (p1.charAt(i)>p2.charAt(i)){
	                return false;
	            
	        }
	    }
	    return false;
	}
	}
	
	/** Returnerar personlistan som en sträng enligt formatet för ArrayLists
            toString()-metod*/
        public String toString() {
            //OBS ändra inte denna
	    	return persons.toString();
	    }
    //Ev andra metoder kommer i nästa uppgift	
}