package Pain;

public abstract class Staff implements IUser {

    private String id;
    private String name;
    private String username;
    private String password;
    private String role;
    private boolean active;

    public Staff(String id, String name, String username, String password, String role) {
        setId(id);
        setName(name);
        setUsername(username);
        setPassword(password);
        setRole(role);
        this.active = true;
    }

    @Override
    public abstract boolean can(String action);

    public String getId()       { return id; }
    public String getName()     { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole()     { return role; }
    public boolean isActive()   { return active; }

    public void setId(String id) {
        if (isBlank(id)) this.id = "unknown"; else this.id = id.trim();
    }
    public void setName(String name) {
        if (isBlank(name)) this.name = "unknown"; else this.name = name.trim();
    }
    public void setUsername(String username) {
        if (isBlank(username)) this.username = "unknown"; else this.username = username.trim();
    }
    public void setPassword(String password) {
        String pw = isBlank(password) ? "password" : password.trim();
        if (pw.length() < 4) this.password = "password"; else this.password = pw;
    }
    public void setRole(String role) {
        if (isBlank(role)) this.role = "STAFF"; else this.role = role.trim();
    }
    public void setActive(boolean active) { this.active = active; }

    // equals: two Staff are equal if same id
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Staff)) return false;
        Staff other = (Staff) obj;
        return this.id != null && this.id.equals(other.id);
    }

    protected boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Staff [id=" + id + ", name=" + name +
               ", username=" + username + ", role=" + role +
               ", active=" + active + "]";
    }
}
