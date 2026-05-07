
public class ClosedGroup extends Group{
	
	public ClosedGroup(String name, String description) {
        super(name, description);
    }
	
	public void addMember(User u) {

	    // 1. Αν η ομάδα δεν έχει καθόλου μέλη το πρώτο μέλος μπαίνει χωρίς έλεγχο
	    if (members.isEmpty()) {
	        super.addMember(u);
	        return;
	    }

	    // 2. Έλεγχος: είναι ο u φίλος με κάποιο μέλος;
	    for (User m : members) {
	        if (m.isFriend(u)) {
	            super.addMember(u);
	            return;
	        }
	    }

	    // 3. Αν δεν είναι φίλος με ΚΑΝΕΝΑ
	    System.out.println("FAILED: " + u.getName() + " cannot be enrolled in group " + name);
	}
}
