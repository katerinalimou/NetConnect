import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		
		User u1 = new User("Makis", "iis2598@uom.edu.gr");
		User u2 = new User("Petros", "ics2524@uom.edu.gr");
		User u3 = new User("Maria", "iis2512@uom.edu.gr");
		User u4 = new User("Gianna", "iis25133@uom.edu.gr");
		User u5 = new User("Nikos", "dai1758@uom.edu.gr");
		User u6 = new User("Babis", "ics25104@uom.edu.gr");
		User u7 = new User("Stella", "dai1827@uom.edu.gr");
		User u8 = new User ("Eleni","ics2586@gmail.com");
		
		
		// Φιλίες
        u1.addFriend(u2);
        u1.addFriend(u5);
        u5.addFriend(u6);
        u3.addFriend(u4);
        u3.addFriend(u2);
        u4.addFriend(u6);
        u5.addFriend(u3);
        u1.addFriend(u6);
        u5.addFriend(u2);
        u7.addFriend(u1);
		
        // Κοινοί Φίλοι
        System.out.println("**************************************");
        System.out.println("Common friends of Nikos and Gianna");
        System.out.println("**************************************");
        int i = 1;
        for (User u : u5.getCommonFriends(u4)) {
            System.out.println(i++ + ": " + u.getName());
        }
        System.out.println("--------------------------------------");

        System.out.println("**************************************");
        System.out.println("Common friends of Makis and Nikos");
        System.out.println("**************************************");
        i = 1;
        for (User u : u1.getCommonFriends(u5)) {
            System.out.println(i++ + ": " + u.getName());
        }
        System.out.println("--------------------------------------");

        u1.printFriends();
        u3.printFriends();
		
        //Ομάδες
        Group g1 = new Group("WebGurus","A group for web passionates");
		ClosedGroup g2 = new ClosedGroup("ExamSolutions","Solutions to common examquestions"); 
	
	
        g1.addMember(u4);
        g1.addMember(u3);
        g1.addMember(u2);

        g2.addMember(u4);
        g2.addMember(u5); 
        g2.addMember(u6);
        g2.addMember(u5); 

        u4.printGroups();

        g1.printMembers();
        g2.printMembers();

        System.out.println("*******************************");
        System.out.println(u4.getName() + " has been infected. The following users have to be tested");
        System.out.println("*******************************");

        for (User u : u4.getVirusTransmissionList()) {
        	System.out.println(u.getName());
        }
        
        System.out.println("-----------------------------");
    
		
	
		ArrayList<User> users = new ArrayList<>();
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		users.add(u6);
		users.add(u7);
	
		new LoginFrame(users);
		
	}
	
}

