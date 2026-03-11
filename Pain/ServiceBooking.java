package Pain; 
public class ServiceBooking {

    private String bookingId;
    private Customer customer;
    private Horse horse;
    private int durationDays;  
    private double totalFee; 
    private IUser createdBy;   
    private boolean paid;

    public ServiceBooking(String bookingId, Customer customer, Horse horse,
                          int durationDays, IUser createdBy) {
        setBookingId(bookingId);
        setCustomer(customer);
        setHorse(horse);
        setDurationDays(durationDays);
        setCreatedBy(createdBy);
        calculateTotal();
        this.paid = true;
    }

    public String getBookingId()  { return bookingId; }
    public Customer getCustomer() { return customer; }
    public Horse getHorse()       { return horse; }
    public int getDurationDays()  { return durationDays; }
    public double getTotalFee()   { return totalFee; }
    public IUser getCreatedBy()   { return createdBy; }
    public boolean isPaid()       { return paid; }

    public void setBookingId(String bookingId) {
        if (bookingId == null || bookingId.trim().isEmpty()) this.bookingId = "UNKNOWN";
        else this.bookingId = bookingId.trim();
    }
    public void setCustomer(Customer customer)  { this.customer = customer; }
    public void setHorse(Horse horse)           { this.horse = horse; }
    public void setCreatedBy(IUser createdBy)   { this.createdBy = createdBy; }

    public void setDurationDays(int durationDays) {
        if (durationDays <= 0) this.durationDays = 1;
        else this.durationDays = durationDays;
    }

    // ===== Calculate total fee =====
    public void calculateTotal() {
        if (horse == null) totalFee = 0;
        else totalFee = horse.getWeight() * durationDays; // placeholder — add dailyCareFee to Horse if needed
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof ServiceBooking)) return false;
        ServiceBooking other = (ServiceBooking) obj;
        return this.bookingId != null && this.bookingId.equals(other.bookingId);
    }

    @Override
    public String toString() {
        String customerName = (customer == null) ? "UNKNOWN" : customer.getCustomerName();
        String horseName    = (horse == null)    ? "UNKNOWN" : horse.getName();
        String staffId      = (createdBy == null) ? "UNKNOWN" : createdBy.getId();
        return "ServiceBooking [bookingId=" + bookingId +
               ", customer=" + customerName +
               ", horse=" + horseName +
               ", durationDays=" + durationDays +
               ", totalFee=" + totalFee +
               ", createdBy=" + staffId +
               ", paid=" + paid + "]";
    }
}