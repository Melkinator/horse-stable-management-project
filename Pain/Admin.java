package Pain;
public class Admin extends Staff {

    public Admin(String id, String name, String username, String password, String role) {
        super(id, name, username, password, role);
        // Constructor chaining: Java runs Staff(...) first, then returns here.
    }

    // Admin can do everything.
    @Override
    public boolean can(String action) {
        return true;
    }
}
