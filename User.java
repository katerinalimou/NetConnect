import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class User {
	private String name;
	private String email;
	private ArrayList<User> friends = new ArrayList<User>();
	private ArrayList<Group> groups = new ArrayList<Group>();
	private ArrayList<Post> posts = new ArrayList<>();
	
	//Έλεγχος εγκυρότητας email σύμφωνα με την εκφώνηση 
	public User(String aName, String eMail) {
		
		name = aName;
		if(!isEmailOk(eMail)) {
			System.out.println("User " + name + " has not been created. Email format is not acceptable. ");
			return;
		}
		
		email = eMail;
		
	}

	public String getName() {
        return name;
    }
	
	public boolean isEmailOk(String eMail) {
		return eMail.matches("(ics|iis|dai)\\d{3,5}@uom\\.edu\\.gr");
	}
	
	//Είναι φίλοι;
	public boolean isFriend(User other) {
        if (other == null || other == this) return false;
        return friends.contains(other);
    }
	
	//Προσθήκη φίλου
	public void addFriend(User other) {
        if (other == null || other == this) return;

        if (!isFriend(other)) {
            friends.add(other);
            other.friends.add(this);
            System.out.println(this.name + " and " + other.name + " are now friends!");
        }
    }
	
	// Λίστα με κοινούς φίλους
	public ArrayList<User> getCommonFriends(User other) {
        ArrayList<User> common = new ArrayList<>();
        for (User f : friends) {
            if (other.friends.contains(f)) {
                common.add(f);
            }
        }
        return common;
    }
	
	public void printFriends() {
        System.out.println("************************");
        System.out.println("Friends of " + name);
        System.out.println("************************");

        int i = 1;
        for (User f : friends) {
            System.out.println(i++ + ": " + f.name);
        }
        System.out.println("-----------------------");
    }
	
	
	public void addGroup(Group g) {
        if (!groups.contains(g)) {
            groups.add(g);
        }
    }
	
	public void printGroups() {
        System.out.println("**************************************");
        System.out.println("Groups that " + name + " has been enrolled in");
        System.out.println("**************************************");

        int i = 1;
        for (Group g : groups) {
            System.out.println(i++ + ": " + g.getName());
        }

        System.out.println("--------------------------------------");
    }
	
	
	// Μέθοδος εντοπισμού πιθανών μεταδόσεων ιού
	// Επιστρέφει όλους τους φίλους και τους φίλους των φίλων χωρίς διπλότυπα
	public ArrayList<User> getVirusTransmissionList() {
		ArrayList<User> result = new ArrayList<>();

	    // 1. Πρόσθεσε όλους τους φίλους
	    for (User f : friends) {
	        if (!result.contains(f)) {
	            result.add(f);
	        }

	        // 2. Πρόσθεσε τους φίλους των φίλων
	        for (User ff : f.friends) {
	            if (ff != this && !result.contains(ff)) {
	                result.add(ff);
	            }
	        }
	    }

	    return result;
	 
	}
	
	public void addPost(String text) {
	    if (text == null || text.trim().isEmpty()) return;
	    posts.add(new Post(text, this));
	}
	
	public ArrayList<Post> getFriendsAndOwnPosts() {
	    ArrayList<Post> allPosts = new ArrayList<>();
	    allPosts.addAll(posts);

	    for (User f : friends) {
	        allPosts.addAll(f.posts);
	    }

	    Collections.sort(allPosts, new Comparator<Post>() {
	        public int compare(Post p1, Post p2) {
	            return p2.getTimestamp().compareTo(p1.getTimestamp());
	        }
	    });

	    return allPosts;
	}
	
	public ArrayList<User> getSuggestedFriends() {
	    ArrayList<User> suggested = new ArrayList<>();

	    for (User f : friends) {
	        for (User ff : f.friends) {
	            if (ff == this) continue;
	            if (isFriend(ff)) continue;
	            if (!suggested.contains(ff)) {
	                suggested.add(ff);
	            }
	        }
	    }
	    return suggested;
	}

	public String getEmail() {
		return email;
	}
	
	
}