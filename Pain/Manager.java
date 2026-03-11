package Pain;

public class Manager extends Staff {

    private float salary;

    public Manager(String id, String name, String phone,
                   String username, String password, float salary) {
        super(id, name, phone, username, password, "MANAGER");
        setSalary(salary);
    }

    public float getSalary() { return salary; }

    public void setSalary(float salary) {
        if (salary < 500) System.out.println("error: need more salary");
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
        Manager other = (Manager) obj;
        if (!super.equals(obj)) return false;
        if (Float.floatToIntBits(salary) != Float.floatToIntBits(other.salary)) return false;
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + "Manager [salary=" + salary + "]";
    }
}
