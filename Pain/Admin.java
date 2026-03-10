package Pain;

public class Admin extends Staff {

    private float salary;

    public Admin(Staff s, float salary) {
        super(s.getId(), s.getName(), s.getUsername(), s.getPassword(), "ADMIN");
        setSalary(salary);
    }

    public float getSalary() { return salary; }

    public void setSalary(float salary) {
        if (salary < 1000) System.out.println("Warning: Admin salary should be at least 1000.");
        else this.salary = salary;
    }

    @Override
    public boolean can(String action) {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) return false;
        if (!(obj instanceof Admin)) return false;
        Admin other = (Admin) obj;
        return Float.floatToIntBits(this.salary) == Float.floatToIntBits(other.salary);
    }

    @Override
    public String toString() {
        return super.toString().replace("]", "") + ", salary=" + salary + "]";
    }
}
