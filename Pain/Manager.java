package Pain;

public class Manager extends Staff {

    public Manager(String id, String name, String username, String password, String role) {
        super(id, name, username, password, role);
    }

    @Override
    public boolean can(String action) {
        return action.equals(Stable.CREATE_STAFF)
            || action.equals(Stable.CREATE_HORSE)
            || action.equals(Stable.CREATE_CUSTOMER)
            || action.equals(Stable.VIEW_STABLES);
    }
}
