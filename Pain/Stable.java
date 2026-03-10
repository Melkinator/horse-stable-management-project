package Pain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Stable {

    // ===== Permission constants =====
    public static final String CREATE_STAFF    = "CREATE_STAFF";
    public static final String CREATE_HORSE    = "CREATE_HORSE";
    public static final String CREATE_CUSTOMER = "CREATE_CUSTOMER";
    public static final String VIEW_STABLES    = "VIEW_STABLE";

    // ===== Basic info =====
    private String name;

    // ===== Data lists =====
    private ArrayList<IUser> staffs;
    private ArrayList<Horse> horses;
    private ArrayList<Customer> customers;

    // ===== Login state =====
    private IUser loggedInUser;

    // ===== Feedback message =====
    private String lastMessage;

    // ===== Constructor =====
    public Stable(String name) {
        setName(name);
        this.staffs       = new ArrayList<>();
        this.horses       = new ArrayList<>();
        this.customers    = new ArrayList<>();
        this.loggedInUser = null;
        seedDefaultAdmin();
        lastMessage = "Stable system started. Default login: admin / 1234";
    }

    // ===== Getters =====
    public String getName()        { return name; }
    public boolean isLoggedIn()    { return loggedInUser != null; }
    public IUser getLoggedInUser() { return loggedInUser; }
    public String getLastMessage() { return lastMessage; }

    public void setName(String name) {
        if (isBlank(name)) this.name = "No Name";
        else this.name = name.trim();
    }
    private void setLastMessage(String msg) { lastMessage = msg; }

    // ===== Seed default admin =====
    private void seedDefaultAdmin() {
        Staff defaultStaff = new Staff("A001", "Admin", "admin", "1234", "ADMIN");
        staffs.add(new Admin(defaultStaff));
    }

    // ===== Login check =====
    public boolean requireStaffLogin() {
        if (loggedInUser == null) {
            setLastMessage("Action denied: you must be logged in.");
            return false;
        }
        if (!loggedInUser.isActive()) {
            loggedInUser = null;
            setLastMessage("Action denied: account inactive (auto logout).");
            return false;
        }
        return true;
    }

    // ===== Login / Logout =====
    public void login(String username, String password) {
        if (isBlank(username) || password == null) {
            setLastMessage("Login failed: missing username or password.");
            return;
        }
        for (int i = 0; i < staffs.size(); i++) {
            IUser user = staffs.get(i);
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                if (!user.isActive()) { setLastMessage("Login failed: account is inactive."); return; }
                if (!user.getPassword().equals(password)) { setLastMessage("Login failed: wrong password."); return; }
                loggedInUser = user;
                setLastMessage("Login successful. Welcome, " + user.getName() + "!");
                return;
            }
        }
        setLastMessage("Login failed: username not found.");
    }

    public void logout() {
        loggedInUser = null;
        setLastMessage("Logged out successfully.");
    }

    // ===== Create Staff =====
    public void createStaff(String id, String name, String role, String username, String password) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(CREATE_STAFF)) {
            setLastMessage("Permission denied: your role cannot create staff.");
            return;
        }
        if (isBlank(id) || isBlank(username)) {
            setLastMessage("Cannot create staff: ID or username is empty.");
            return;
        }
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getUsername().equalsIgnoreCase(username.trim())) {
                setLastMessage("Cannot create staff: username already exists.");
                return;
            }
        }
        if (role.equalsIgnoreCase("ADMIN")) {
            Staff adminStaff = new Staff(id, name, username, password, "ADMIN");
            staffs.add(new Admin(adminStaff));
            setLastMessage("Admin created successfully.");
        } else if (role.equalsIgnoreCase("MANAGER")) {
            Staff managerStaff = new Staff(id, name, username, password, "MANAGER");
            staffs.add(new Manager(managerStaff));
            setLastMessage("Manager created successfully.");
        } else if (role.equalsIgnoreCase("STAFF")) {
            staffs.add(new Staff(id, name, username, password, "STAFF"));
            setLastMessage("Staff created successfully.");
        } else {
            setLastMessage("Invalid role: use ADMIN, MANAGER, or STAFF.");
        }
    }

    // ===== Create Horse =====
    public void createHorse(String name, String breed, String color,
                            Horse.Gender gender, LocalDate birthDate, LocalDate arrivalDate,
                            int stallId, String sire, String dam, double weight, double height) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(CREATE_HORSE)) {
            setLastMessage("Permission denied: your role cannot add horses.");
            return;
        }
        if (isBlank(name)) { setLastMessage("Cannot create horse: name is required."); return; }
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getStallId() == stallId && stallId != 0) {
                setLastMessage("Cannot create horse: stall " + stallId + " is already occupied.");
                return;
            }
        }
        Horse horse = new Horse(name, breed, color, gender, birthDate, arrivalDate,
                                stallId, sire, dam, weight, height);
        horse.setId(horses.size() + 1);
        horses.add(horse);
        setLastMessage("Horse '" + name + "' added to the stable successfully.");
    }

    // ===== Set Horse Availability =====
    public void setHorseAvailability(int horseId, boolean available) {
        if (!requireStaffLogin()) return;
        Horse horse = findHorseById(horseId);
        if (horse == null) { setLastMessage("Horse not found with ID: " + horseId); return; }
        horse.setAvailable(available);
        setLastMessage("Horse '" + horse.getName() + "' availability set to: " + available);
    }

    // ===== Create Customer =====
    public void createCustomer(String customerId, String customerName, String phone,
                               String password, double balance, int horseId) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(CREATE_CUSTOMER)) {
            setLastMessage("Permission denied: your role cannot register customers.");
            return;
        }
        if (isBlank(customerId) || isBlank(phone)) {
            setLastMessage("Cannot create customer: ID or phone is empty.");
            return;
        }
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equalsIgnoreCase(customerId.trim())) {
                setLastMessage("Cannot create customer: ID already exists."); return;
            }
            if (customers.get(i).getPhone().equals(phone.trim())) {
                setLastMessage("Cannot create customer: phone already exists."); return;
            }
        }
        Horse horse = findHorseById(horseId);
        customers.add(new Customer(customerId, customerName, phone, password, balance, horse, loggedInUser));
        setLastMessage("Customer '" + customerName + "' registered successfully.");
    }

    // ===== Print helpers =====
    public void printStaff() {
        System.out.println("\n--- Staff (" + staffs.size() + ") ---");
        if (staffs.isEmpty()) { System.out.println("No staff."); return; }
        for (int i = 0; i < staffs.size(); i++) System.out.println((i+1) + ") " + staffs.get(i));
    }
    public void printHorses() {
        System.out.println("\n--- Horses (" + horses.size() + ") ---");
        if (horses.isEmpty()) { System.out.println("No horses."); return; }
        for (int i = 0; i < horses.size(); i++) System.out.println((i+1) + ") " + horses.get(i));
    }
    public void printCustomers() {
        System.out.println("\n--- Customers (" + customers.size() + ") ---");
        if (customers.isEmpty()) { System.out.println("No customers."); return; }
        for (int i = 0; i < customers.size(); i++) System.out.println((i+1) + ") " + customers.get(i));
    }

    // ===== Find helpers =====
    private Horse findHorseById(int id) {
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getId() == id) return horses.get(i);
        }
        return null;
    }

    public boolean isBlank(String str) { return str == null || str.trim().isEmpty(); }

    @Override
    public String toString() {
        return "Stable [name=" + name + ", staff=" + staffs.size() +
               ", horses=" + horses.size() + ", customers=" + customers.size() + "]";
    }
}
