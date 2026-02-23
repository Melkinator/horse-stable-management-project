package model;

public class Customer {

    private static int customerCount = 0;

    private String customerName;
    private Horse horse;
    private double weight;
    private boolean isBuying;

    public Customer(String customerName, Horse horse, boolean isBuying) {
        setCustomerName(customerName);
        this.horse = horse;
        this.isBuying = false;
        this.weight = (horse==null) ? 0 : horse.getWeight();

        customerCount++;
    }

    //getters

    public String getCustomerName() { return customerName; }
    public double getWeight() { return weight; }
    public Horse getHorse() { return horse; }
    public boolean isBuying() { return isBuying; }
    public static int getCustomerCount() { return customerCount; }

    //setters

    public void setCustomerName(String customerName) {
        if (customerName==null||customerName.trim().isEmpty()) {
            this.customerName = "No Name";
        } else {
            this.customerName = customerName.trim();
        }
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHorse(Horse horse) {
        this.horse = horse;
        this.weight = (horse==null) ? 0 : horse.getWeight();
    }

    public void setBuying(boolean buying) {
        this.isBuying = buying;
    }

    @Override
    public String toString() {
        return "Customer [customerName=" + customerName + ", horse=" + horse + ", weight=" + weight + ", isBuying="
                + isBuying + "]";
    }

    
}