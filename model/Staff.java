package model;

public class Staff {
    private int id;
    private String name;
    private String role;

    private static int staffCounter=0;

    public Staff(String name, String role) {
        staffCounter++;
        this.id = staffCounter;
        setName(name);
        setRole(role);
    }

    // getters
    public int getStaffCounter() { return staffCounter; }
    public String getName() { return name; }
    public String getRole() { return role; }
    public int getId() { return id; }

    // setters
    public void setName(String name) {
        if (name==null||name.trim().isEmpty()) {
            this.name = "No Name";
        } else {
            this.name = name.trim();
        }
    }

    public void setRole(String role) {
        if (role==null||role.trim().isEmpty()) {
            this.role = "No Role";
        } else {
            this.role = role.trim();
        }
    }

    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name + ", role=" + role + "]";
    }

}