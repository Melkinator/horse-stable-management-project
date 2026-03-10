package Pain;

public class Manager extends Staff {

    public Manager(Staff s) {
        super(s.getId(), s.getName(), s.getUsername(), s.getPassword(), s.getRole());
    }

    @Override
    public boolean can(String action) {
        return action.equals(Stable.CREATE_STAFF)
            || action.equals(Stable.CREATE_HORSE)
            || action.equals(Stable.CREATE_CUSTOMER)
            || action.equals(Stable.VIEW_STABLES);
    }
}
