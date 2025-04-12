package Day12;
import java.util.*;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friends; // List of Friend IDs
    User next;

    public User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name.trim();
        this.age = age;
        this.friends = new ArrayList<>();
    }
}

class SocialNetwork {
    private User head = null;

    public void addUser(int userId, String name, int age) {
        if (getUserById(userId) != null) {
            System.out.println("User ID already exists.");
            return;
        }
        User newUser = new User(userId, name, age);
        if (head == null) {
            head = newUser;
        } else {
            User temp = head;
            while (temp.next != null) temp = temp.next;
            temp.next = newUser;
        }
    }


    public void addFriendConnection(int id1, int id2) {
        if (id1 == id2) {
            System.out.println("Cannot friend yourself.");
            return;
        }

        User user1 = getUserById(id1);
        User user2 = getUserById(id2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        if (!user1.friends.contains(id2)) user1.friends.add(id2);
        if (!user2.friends.contains(id1)) user2.friends.add(id1);
        System.out.println("Friend connection added between " + user1.name + " and " + user2.name);
    }


    public void removeFriendConnection(int id1, int id2) {
        User user1 = getUserById(id1);
        User user2 = getUserById(id2);

        if (user1 == null || user2 == null) {
            System.out.println("One or both users not found.");
            return;
        }

        user1.friends.remove((Integer) id2);
        user2.friends.remove((Integer) id1);
        System.out.println("Friend connection removed between " + user1.name + " and " + user2.name);
    }

    public void findMutualFriends(int id1, int id2) {
        User user1 = getUserById(id1);
        User user2 = getUserById(id2);

        if (user1 == null || user2 == null) {
            System.out.println("Users not found.");
            return;
        }

        Set<Integer> set = new HashSet<>(user1.friends);
        List<Integer> mutual = new ArrayList<>();

        for (int id : user2.friends) {
            if (set.contains(id)) mutual.add(id);
        }

        if (mutual.isEmpty()) {
            System.out.println("No mutual friends.");
        } else {
            System.out.println("Mutual Friends:");
            for (int fid : mutual) {
                User u = getUserById(fid);
                if (u != null) {
                    System.out.println("- " + u.name + " (ID: " + u.userId + ")");
                }
            }
        }
    }

    public void displayFriends(int userId) {
        User user = getUserById(userId);
        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        if (user.friends.isEmpty()) {
            System.out.println(user.name + " has no friends.");
            return;
        }

        System.out.println(user.name + "'s Friends:");
        for (int id : user.friends) {
            User friend = getUserById(id);
            if (friend != null) {
                System.out.println("- " + friend.name + " (ID: " + friend.userId + ")");
            }
        }
    }

    public void searchUser(String query) {
        User temp = head;
        boolean found = false;
        while (temp != null) {
            if (temp.name.equalsIgnoreCase(query.trim()) || String.valueOf(temp.userId).equals(query.trim())) {
                printUser(temp);
                found = true;
            }
            temp = temp.next;
        }
        if (!found) System.out.println("User not found.");
    }

    public void countFriends() {
        User temp = head;
        while (temp != null) {
            System.out.println(temp.name + " (ID: " + temp.userId + ") has " + temp.friends.size() + " friend(s).");
            temp = temp.next;
        }
    }

    private void printUser(User user) {
        System.out.println("ID: " + user.userId + ", Name: " + user.name + ", Age: " + user.age);
    }


    private User getUserById(int userId) {
        User temp = head;
        while (temp != null) {
            if (temp.userId == userId) return temp;
            temp = temp.next;
        }
        return null;
    }
}

public class SocialNetworkManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SocialNetwork sn = new SocialNetwork();

        while (true) {
            System.out.println("\n1. Add User\n2. Add Friend Connection\n3. Remove Friend Connection");
            System.out.println("4. Find Mutual Friends\n5. Display Friends\n6. Search User");
            System.out.println("7. Count Friends\n8. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(sc.nextLine().trim());

            switch (choice) {
                case 1:
                    System.out.print("User ID: ");
                    int id = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Name: ");
                    String name = sc.nextLine().trim().replaceAll("//.*", "");

                    System.out.print("Age: ");
                    int age = Integer.parseInt(sc.nextLine().trim());

                    sn.addUser(id, name, age);
                    break;

                case 2:
                    System.out.print("User ID 1: ");
                    int id1 = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("User ID 2: ");
                    int id2 = Integer.parseInt(sc.nextLine().trim());
                    sn.addFriendConnection(id1, id2);
                    break;

                case 3:
                    System.out.print("User ID 1: ");
                    int rid1 = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("User ID 2: ");
                    int rid2 = Integer.parseInt(sc.nextLine().trim());
                    sn.removeFriendConnection(rid1, rid2);
                    break;

                case 4:
                    System.out.print("User ID 1: ");
                    int m1 = Integer.parseInt(sc.nextLine().trim());
                    System.out.print("User ID 2: ");
                    int m2 = Integer.parseInt(sc.nextLine().trim());
                    sn.findMutualFriends(m1, m2);
                    break;

                case 5:
                    System.out.print("Enter User ID: ");
                    int fid = Integer.parseInt(sc.nextLine().trim());
                    sn.displayFriends(fid);
                    break;

                case 6:
                    System.out.print("Enter Name or User ID: ");
                    String query = sc.nextLine();
                    sn.searchUser(query);
                    break;

                case 7:
                    sn.countFriends();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
