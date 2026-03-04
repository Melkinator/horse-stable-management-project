package Pain;

public class Customer {
    private static int customerCount = 0;

    private String customerId;
    private String customerName;
    private String phone;
    private String password;
    private Horse horse;
    private double balance;
    private boolean active;
    private boolean isBuying;
    private IUser createdBy;

    public Customer(String customerId, String customerName, String phone,
                    String password, double balance, Horse horse, IUser createdBy) {
        setCustomerId(customerId);
        setCustomerName(customerName);
        setPhone(phone);
        setPassword(password);
        setBalance(balance);
        setHorse(horse);
        this.createdBy = createdBy;
        this.active    = true;
        this.isBuying  = false;
        customerCount++;
    }

    public String getCustomerId()   { return customerId; }
    public String getCustomerName() { return customerName; }
    public String getPhone()        { return phone; }
    public double getBalance()      { return balance; }
    public Horse getHorse()         { return horse; }
    public boolean isActive()       { return active; }
    public boolean isBuying()       { return isBuying; }
    public IUser getCreatedBy()     { return createdBy; }
    public static int getCustomerCount() { return customerCount; }

    public boolean checkPassword(String input) {
        return password != null && password.equals(input);
    }

    public void setCustomerId(String customerId) {
        if (isBlank(customerId)) this.customerId = "UNKNOWN";
        else this.customerId = customerId.trim();
    }
    public void setCustomerName(String customerName) {
        if (isBlank(customerName)) this.customerName = "No Name";
        else this.customerName = customerName.trim();
    }
    public void setPhone(String phone) {
        String p = (phone == null) ? "" : phone.trim();
        if (!isDigits(p) || p.length() < 8 || p.length() > 15) this.phone = "00000000";
        else this.phone = p;
    }
    public void setPassword(String password) {
        String pw = (password == null) ? "" : password;
        if (pw.length() < 4) this.password = "0000";
        else this.password = pw;
    }
    public void setBalance(double balance) {
        if (balance < 0) this.balance = 0;
        else this.balance = balance;
    }
    public void setHorse(Horse horse)       { this.horse = horse; }
    public void setActive(boolean active)   { this.active = active; }
    public void setBuying(boolean buying)   { this.isBuying = buying; }

    public void addBalance(double amount) {
        if (amount > 0) balance += amount;
    }
    public boolean deductBalance(double amount) {
        if (amount <= 0 || amount > balance) return false;
        balance -= amount;
        return true;
    }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
    private boolean isDigits(String s) {
        if (isBlank(s)) return false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String staffInfo = (createdBy == null) ? "UNKNOWN" : createdBy.getId();
        String horseName = (horse == null) ? "None" : horse.getName();
        return "Customer [customerId=" + customerId + ", customerName=" + customerName +
               ", phone=" + phone + ", balance=" + balance +
               ", horse=" + horseName + ", active=" + active +
               ", isBuying=" + isBuying + ", createdBy=" + staffInfo + "]";
    }
}
