package Pain;

import java.time.LocalDate;
import java.util.ArrayList;

public class Stable {

    public static final String CREATE_STAFF    = "CREATE_STAFF";
    public static final String CREATE_HORSE    = "CREATE_HORSE";
    public static final String CREATE_CUSTOMER = "CREATE_CUSTOMER";
    public static final String VIEW_STABLES    = "VIEW_STABLE";
    public static final String BOOK_SERVICE     = "BOOK_SERVICE";
    public static final String VIEW_BOOKINGS    = "VIEW_BOOKINGS";

    private String name;
    private ArrayList<IUser> staffs;
    private ArrayList<Horse> horses;
    private ArrayList<Customer> customers;
    private ArrayList<ServiceBooking> bookings;
    private IUser loggedInUser;
    private String lastMessage;

    public Stable(String name) {
        setName(name);
        this.staffs = new ArrayList<>();
        this.horses = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.loggedInUser = null;
        seedDefaultAdmin();
        lastMessage = "Stable system started. Default login: admin / 1234";
    }

    public String getName()        { return name; }
    public boolean isLoggedIn()    { return loggedInUser != null; }
    public IUser getLoggedInUser() { return loggedInUser; }
    public String getLastMessage() { return lastMessage; }
    public int getStaffCount()     { return staffs.size(); }
    public int getHorseCount()     { return horses.size(); }
    public int getCustomerCount()  { return customers.size(); }
    public int getBookingCount()   { return bookings.size(); }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) this.name = "No Name";
        else this.name = name.trim();
    }
    private void setLastMessage(String msg) { lastMessage = msg; }

    private void seedDefaultAdmin() {
        staffs.add(new Admin("A001", "Admin", "00000001", "admin", "1234", 2000));
    }

    public boolean requireStaffLogin() {
        if (loggedInUser == null) { setLastMessage("Action denied: you must be logged in."); return false; }
        if (!loggedInUser.isActive()) {
            loggedInUser = null;
            setLastMessage("Action denied: account inactive (auto logout).");
            return false;
        }
        return true;
    }

    public void login(String username, String password) {
        if (username == null || username.trim().isEmpty() || password == null) {
            setLastMessage("Login failed: missing username or password."); return;
        }
        for (int i = 0; i < staffs.size(); i++) {
            IUser user = staffs.get(i);
            if (user.getUsername().equalsIgnoreCase(username.trim())) {
                if (!user.isActive()) { setLastMessage("Login failed: account is inactive."); return; }
                if (!user.checkPassword(password)) { setLastMessage("Login failed: wrong password."); return; }
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

    public void createStaff(String id, String name, String phone, String role,
                            String username, String password, float salary) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(CREATE_STAFF)) { setLastMessage("Permission denied: cannot create staff."); return; }
        if (id == null || id.trim().isEmpty() || username == null || username.trim().isEmpty()) {
            setLastMessage("Cannot create staff: ID or username is empty."); return;
        }
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i).getUsername().equalsIgnoreCase(username.trim())) {
                setLastMessage("Cannot create staff: username already exists."); return;
            }
        }
        if (role.equalsIgnoreCase("ADMIN")) {
            staffs.add(new Admin(id, name, phone, username, password, salary));
            setLastMessage("Admin created successfully.");
        } else if (role.equalsIgnoreCase("MANAGER")) {
            staffs.add(new Manager(id, name, phone, username, password, salary));
            setLastMessage("Manager created successfully.");
        } else {
            setLastMessage("Invalid role: use ADMIN or MANAGER.");
        }
    }

    public void createHorse(String name, String breed, String color,
                            Horse.Gender gender, LocalDate birthDate, LocalDate arrivalDate,
                            int stallId, String sire, String dam, double weight, double height) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(CREATE_HORSE)) { setLastMessage("Permission denied: cannot add horses."); return; }
        if (name == null || name.trim().isEmpty()) { setLastMessage("Cannot create horse: name is required."); return; }
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getStallId() == stallId && stallId != 0) {
                setLastMessage("Cannot create horse: stall " + stallId + " is already occupied."); return;
            }
        }
        Horse horse = new Horse(name, breed, color, gender, birthDate, arrivalDate, stallId, sire, dam, weight, height);
        horse.setId(horses.size() + 1);
        horses.add(horse);
        setLastMessage("Horse '" + name + "' added successfully.");
    }

    public void setHorseAvailability(int horseId, boolean available) {
        if (!requireStaffLogin()) return;
        Horse horse = findHorseById(horseId);
        if (horse == null) { setLastMessage("Horse not found with ID: " + horseId); return; }
        horse.setAvailable(available);
        setLastMessage("Horse '" + horse.getName() + "' availability set to: " + available);
    }

    public void createCustomer(String customerId, String customerName, String phone,
                               String password, double balance, int horseId) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(CREATE_CUSTOMER)) { setLastMessage("Permission denied: cannot register customers."); return; }
        if (customerId == null || customerId.trim().isEmpty()) { setLastMessage("Cannot create customer: ID is empty."); return; }
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

    public void addBooking(String bookingId, String customerId, int horseId, int durationDays) {
        if (!requireStaffLogin()) return;
        if (!loggedInUser.can(BOOK_SERVICE)) { setLastMessage("Permission denied: cannot book services."); return; }
        Customer customer = findCustomerById(customerId);
        if (customer == null) { setLastMessage("Customer not found with ID: " + customerId); return; }

        Horse horse = findHorseById(horseId);
        if (horse == null) { setLastMessage("Horse not found with ID: " + horseId); return; }
        ServiceBooking booking = new ServiceBooking(bookingId, customer, horse, durationDays, loggedInUser);
        bookings.add(booking);
        setLastMessage("Service booked successfully for customer '" + customer.getCustomerName() + "' and horse '" + horse.getName() + "'. Total fee: $" + booking.getTotalFee());
    }

    public void printBookings() {
        System.out.println("\n--- Service Bookings (" + bookings.size() + ") ---");
        if (bookings.isEmpty()) { System.out.println("No bookings."); return; }
        for (int i = 0; i < bookings.size(); i++) System.out.println((i+1) + ") " + bookings.get(i));
    }

    private Customer findCustomerById(String customerId) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getCustomerId().equalsIgnoreCase(customerId.trim())) {
                return customers.get(i);
            }
        }
        return null;
    }

    public void filterStaff(StaffFilter filter) {
        System.out.println("\n--- Filtered Staff ---");
        boolean found = false;
        for (int i = 0; i < staffs.size(); i++) {
            if (staffs.get(i) instanceof Staff) {
                Staff s = (Staff) staffs.get(i);
                if (filter.test(s)) { System.out.println("  " + s); found = true; }
            }
        }
        if (!found) System.out.println("  No staff matched.");
    }

    public void printActiveStaff() {
        System.out.println("\n[Anonymous Inner Class] Active staff:");
        filterStaff(new StaffFilter() {
            @Override
            public boolean test(Staff s) {
                return s.isActive();
            }
        });
    }

    public void printActiveStaffLambda() {
        System.out.println("\n[Lambda] Active staff:");
        filterStaff(s -> s.isActive());
    }

    public void printStaffByRole(String role) {
        System.out.println("\n[Lambda] Staff with role '" + role + "':");
        filterStaff(s -> s.getRole().equalsIgnoreCase(role));
    }


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

    private Horse findHorseById(int id) {
        for (int i = 0; i < horses.size(); i++) {
            if (horses.get(i).getId() == id) return horses.get(i);
        }
        return null;
    }

    @Override
    public String toString() {
        return "Stable [name=" + name + ", staff=" + staffs.size() +
               ", horses=" + horses.size() + ", customers=" + customers.size() + "]";
    }
}
