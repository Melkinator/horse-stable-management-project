package model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Stable {

    // perms
    private static final String CREATE_STAFF="CREATE_STAFF";
    private static final String CREATE_HORSE="CREATE_HORSE";
    private static final String CREATE_STABLE="CREATE_STABLE";
    private static final String VIEW_STABLES="VIEW_STABLE";

    private String name;

    private int horseCount;

    // arrays
    private ArrayList<IUser> staffs;
    private ArrayList<Horse> horses;
    private ArrayList<Customer> customers;

    private IUser loggedInUser;

    public Stable(String name) {
        setName(name);
        this.horseCount = 0;

        this.staffs = new ArrayList<>();
        this.horses = new ArrayList<>();
        this.customers = new ArrayList<>();
    }

    // getters
    public String getName() { return name; }
    public int getHorseCount() { return horseCount; }
    public boolean isLoggedIn() { return loggedInUser != null; }
    public IUser getLoggedInUser() { return loggedInUser; }

    // setters
    public void setName(String name) {
        if (name==null||name.trim().isEmpty()) {
            this.name = "No Name";
        } else {
            this.name = name.trim();
        }
    }

    // login check
    public boolean requireStaffLogin() {
        if (loggedInUser==null) {
            System.out.println("You must be logged in to do this.");
            return false;
        }
        if (!loggedInUser.isActive()) {
            System.out.println("You do not have permission to do this.");
            return false;
        }
        return true;
    }

    // create staff
    public void createStaff(String id, String name, String role, String username, String password) {
        if (!requireStaffLogin()) return;

        if (!loggedInUser.can(CREATE_STAFF)) {
            System.out.println("You do not have permission to do this.");
            return;
        }

        if (isBlank(id)||isBlank(username)) {
            System.out.println("ID or username cannot be blank.");
            return;
        }

        for (int i=0;i<staffs.size();i++) {
            if (staffs.get(i).getUsername().equalsIgnoreCase(username.trim())) {
                System.out.println("Username already exists.");
                return;
            }
        }

        if (role.equals("ADMIN")) {
            staffs.add(new Admin(id, name, username, password, role));
        } else if (role.equals("MANAGER")) {
            staffs.add(new Manager(id, name, username, password, role));
        } else if (role.equals("STAFF")) {
            staffs.add(new Staff(id, name, role, username, password));
        } else {
            System.out.println("Invalid role.");
        }
    }


    //helpers
    public boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
}