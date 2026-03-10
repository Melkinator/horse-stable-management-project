package Pain;
public class Admin extends Staff {

    public Admin(Staff s) {
        super(s.getId(), s.getName(), s.getUsername(), s.getPassword(), s.getRole());
    }

    // Admin can do everything.
    @Override
    public boolean can(String action) {
        return true;
    }
}
