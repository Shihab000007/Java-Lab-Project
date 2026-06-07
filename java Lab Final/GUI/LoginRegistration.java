import java.util.HashMap;

public class LoginRegistration {
    // Shared user database
    public static HashMap<String, String> users = new HashMap<>();
    public static String loggedInUser = null;

    public static boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        if (users.containsKey(username)) {
            return false; // Username already exists
        }
        users.put(username, password);
        return true;
    }

    public static boolean login(String username, String password) {
        if (users.containsKey(username) && users.get(username).equals(password)) {
            loggedInUser = username;
            return true;
        }
        return false;
    }

    public static void logout() {
        loggedInUser = null;
    }
}