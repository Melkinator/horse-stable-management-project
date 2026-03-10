package Pain;

public class Manager extends Staff {

    private float salary;

    public Manager(Staff s, float salary) {
        super(s.getId(), s.getName(), s.getUsername(), s.getPassword(), "MANAGER");
        setSalary(salary);
    }

    public float getSalary() { return salary; }

    public void setSalary(float salary) {
        if (salary < 500) System.out.println("Warning: Manager salary should be at least 500.");
        else this.salary = salary;
    }

    @Override
    public boolean can(String action) {
        return action.equals(Stable.CREATE_STAFF)
            || action.equals(Stable.CREATE_HORSE)
            || action.equals(Stable.CREATE_CUSTOMER)
            || action.equals(Stable.VIEW_STABLES);
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Manager)) return false;
        Manager other = (Manager) obj;
        return Float.floatToIntBits(this.salary) == Float.floatToIntBits(other.salary);
    }

    @Override
    public String toString() {
        return super.toString().replace("]", "") + ", salary=" + salary + "]";
    }
}
