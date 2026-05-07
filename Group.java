import java.util.ArrayList;

public class Group {
	protected String name;
    protected String description;
    protected ArrayList<User> members = new ArrayList<User>();
    
    
    public Group(String aName, String description) {
        name = aName;
        this.description = description;
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isMember(User u) {
        return members.contains(u);
    }
    
    // Προσθήκη μέλους στην ομάδα
    public void addMember(User u) {
        if (u == null) return;

        if (!isMember(u)) {
            members.add(u);
            u.addGroup(this);
            System.out.println(u.getName() + " has been successfully enrolled in group " + name);
        }
    }
    
    public void printMembers() {
        System.out.println("*******************************");
        System.out.println("Members of group " + name);
        System.out.println("*******************************");

        int i = 1;
        for (User u : members) {
            System.out.println(i++ + ": " + u.getName());
        }
        System.out.println("-----------------------------");
    }



}
