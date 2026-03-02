package model;

public class Admin implements IUser {
    private String id;
    private String name;
    private String username;
    private String password;
    private String role;
    private boolean active;

    public Admin(String id, String name, String username, String password, String role) {
        setId(id);
        setName(name);
        setUsername(username);
        setPassword(password);
        setRole(role);

        this.active = true;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public boolean isActive() { return active; }

    @Override public boolean can(String action) {
        // Admin can do everything
        return true;
    }

    // setters
    public void setId(String id) {
        if (isBlank(id)) {
            this.id="admin";
        } else {
            this.id = id.trim();
        }
    }

    public void setUsername(String username) {
        if (isBlank(username)) {
            this.username = "admin";
        } else {
            this.username = username.trim();
        }
    }

    public void setName(String name) {
        if (isBlank(name)) {
            this.name = "Admin";
        } else {
            this.name = name.trim();
        }
    }

    public void setPassword(String password) {
        String pw = isBlank(password) ? "password" : password.trim();
        if (pw.length()<4) this.password = "password";
        else this.password = pw;
    }

    public void setRole(String role) {
        if (isBlank(role)) {
            this.role = "ADMIN";
        } else {
            this.role = role.trim();
        }
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // helpers

    private boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
    }
}