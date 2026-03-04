package Pain;

import java.time.LocalDate;

public class Horse {
    private int id;
    private String name;
    private String breed;
    private String color;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate arrivalDate;
    private int stallId;
    private String sire;
    private String dam;
    private double weight;
    private double height;
    private boolean available;

    public Horse(String name, String breed, String color, Gender gender,
                 LocalDate birthDate, LocalDate arrivalDate, int stallId,
                 String sire, String dam, double weight, double height) {
        setName(name);
        setBreed(breed);
        setColor(color);
        setGender(gender);
        setBirthDate(birthDate);
        setArrivalDate(arrivalDate);
        setStallId(stallId);
        setSire(sire);
        setDam(dam);
        setWeight(weight);
        setHeight(height);
        this.available = true;
    }

    public int getId()               { return id; }
    public String getName()          { return name; }
    public String getBreed()         { return breed; }
    public String getColor()         { return color; }
    public Gender getGender()        { return gender; }
    public LocalDate getBirthDate()  { return birthDate; }
    public LocalDate getArrivalDate(){ return arrivalDate; }
    public int getStallId()          { return stallId; }
    public String getSire()          { return sire; }
    public String getDam()           { return dam; }
    public double getWeight()        { return weight; }
    public double getHeight()        { return height; }
    public boolean isAvailable()     { return available; }

    public void setId(int id) {
        if (id < 0) this.id = 0;
        else this.id = id;
    }
    public void setName(String name) {
        if (isBlank(name)) this.name = "Unnamed Horse";
        else this.name = name.trim();
    }
    public void setBreed(String breed) {
        if (isBlank(breed)) this.breed = "Unknown Breed";
        else this.breed = breed.trim();
    }
    public void setColor(String color) {
        if (isBlank(color)) this.color = "Unknown Color";
        else this.color = color.trim();
    }
    public void setGender(Gender gender) {
        if (gender == null) this.gender = Gender.STALLION;
        else this.gender = gender;
    }
    public void setBirthDate(LocalDate birthDate) {
        if (birthDate != null && birthDate.isAfter(LocalDate.now())) {
            System.out.println("Invalid birth date: cannot be in the future. Set to null.");
            this.birthDate = null;
        } else {
            this.birthDate = birthDate;
        }
    }
    public void setArrivalDate(LocalDate arrivalDate) {
        if (arrivalDate != null && arrivalDate.isAfter(LocalDate.now())) {
            System.out.println("Invalid arrival date: cannot be in the future. Set to today.");
            this.arrivalDate = LocalDate.now();
        } else {
            this.arrivalDate = arrivalDate;
        }
    }
    public void setStallId(int stallId) {
        if (stallId < 0) {
            System.out.println("Invalid stall ID: cannot be negative. Set to 0.");
            this.stallId = 0;
        } else {
            this.stallId = stallId;
        }
    }
    public void setSire(String sire) { this.sire = (sire == null) ? "Unknown" : sire.trim(); }
    public void setDam(String dam)   { this.dam  = (dam  == null) ? "Unknown" : dam.trim();  }
    public void setWeight(double weight) {
        if (weight < 0) { System.out.println("Invalid weight: set to 0."); this.weight = 0; }
        else this.weight = weight;
    }
    public void setHeight(double height) {
        if (height < 0) { System.out.println("Invalid height: set to 0."); this.height = 0; }
        else this.height = height;
    }
    public void setAvailable(boolean available) { this.available = available; }

    private boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }

    public void printInfo() {
        System.out.println(
            "Name: "         + name          + "\n" +
            "Breed: "        + breed         + "\n" +
            "Color: "        + color         + "\n" +
            "Gender: "       + gender        + "\n" +
            "Birth Date: "   + birthDate     + "\n" +
            "Arrival Date: " + arrivalDate   + "\n" +
            "Stall ID: "     + stallId       + "\n" +
            "Sire: "         + sire          + "\n" +
            "Dam: "          + dam           + "\n" +
            "Weight: "       + weight + " kg"  + "\n" +
            "Height: "       + height + " hands" + "\n" +
            "Available: "    + available
        );
    }

    @Override
    public String toString() {
        return "Horse [id=" + id + ", name=" + name + ", breed=" + breed +
               ", color=" + color + ", gender=" + gender + ", stallId=" + stallId +
               ", weight=" + weight + ", height=" + height + ", available=" + available + "]";
    }

    public enum Gender {
        STALLION, MARE, GELDING, FILLY, COLT;
        @Override
        public String toString() {
            return name().charAt(0) + name().substring(1).toLowerCase();
        }
    }
}
