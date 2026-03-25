package Pain;

public abstract class Staff implements IUser {

    private String id;
    private String name;
    private String phone;      
    private String username;
    private String password;
    private String role;
    private boolean active;

    @Override
    public abstract boolean can(String action);

    public Staff(String id, String name, String phone,
                 String username, String password, String role) {
        setId(id);
        setName(name);
        setPhone(phone);
        setUsername(username);
        setPassword(password);
        setRole(role);
        this.active = true;
    }

    protected String getPassword() { return password; }

    public String getId()       { return id; }
    public String getName()     { return name; }
    public String getPhone()    { return phone; }
    public String getUsername() { return username; }
    public String getRole()     { return role; }
    public boolean isActive()   { return active; }

    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    public void setId(String id) {
        if (isBlank(id)) this.id = "UNKNOWN"; else this.id = id.trim();
    }
    public void setName(String name) {
        if (isBlank(name)) this.name = "No Name"; else this.name = name.trim();
    }
    public void setPhone(String phone) {
        String p = (phone == null) ? "" : phone.trim();
        if (!isDigits(p) || p.length() < 8 || p.length() > 15) this.phone = "00000000";
        else this.phone = p;
    }
    public void setUsername(String username) {
        if (isBlank(username)) this.username = "staff_" + this.id;
        else this.username = username.trim();
    }
    public void setPassword(String password) {
        String pw = (password == null) ? "" : password;
        if (pw.length() < 4) this.password = "0000"; else this.password = pw;
    }
    public void setRole(String role) {
        if (isBlank(role)) this.role = "STAFF"; else this.role = role.trim();
    }
    public void setActive(boolean active) { this.active = active; }

    @Override
    public boolean equals(Object obj) {
        Staff other = (Staff) obj;
        if (other.phone.equals(phone)) return true;
        return false;
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private boolean isDigits(String s) {
        if (s == null || s.trim().isEmpty()) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i); if (c < '0' || c > '9') return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{id='" + id + "'" +
               ", name='" + name + "'" +
               ", phone='" + phone + "'" +
               ", username='" + username + "'" +
               ", role='" + role + "'" +
               ", active=" + active + "}";
    }
}
