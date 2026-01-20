package model;

import java.time.LocalDate;
import java.time.Period;

public class horse {
    private int id;
    private String name;
    private String breed;
    private String color;
    private Gender gender;
    private LocalDate birthDate;
    private LocalDate arrivalDate;
    private int stallId;
    private String sire; // the father
    private String dam; // the mother
    private double weight;
    private double height;

    public enum Gender {
        STALLION, MARE, GELDING, FILLY, COLT;

        @Override
        public String toString() {
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }
    }

    // the constructors

    public horse() {}

    public horse(String name, String breed, String color, Gender gender, LocalDate birthDate) {
        this();
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name==null||name.trim().isEmpty()) {
            throw new IllegalArgumentException("Horse name can't be empty dumbass");
        }
        this.name = name.trim();
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed != null ? breed.trim() : null;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color != null ? color.trim() : null;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if (birthDate!=null&&birthDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("How the hell you gonna put in a future birth date");
        }
        this.birthDate = birthDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        if (arrivalDate!=null&&arrivalDate.isAfter(arrivalDate.now())) {
            throw new IllegalArgumentException("How the hell you gonna put in a future arrival date");
        }
        this.arrivalDate = arrivalDate;
    }

    public int getStallId() {
        return stallId;
    }

    public void setStallId(int stallId) {
        if (stallId < 0) {
            throw new IllegalArgumentException("stall id cant be lower than 0 twin");
        }
        this.stallId = stallId;
    }

    public String getSire() {
        return sire;
    }
    
    public void setSire(String sire) {
        this.sire = sire != null ? sire.trim() : null;
    }
    
    public String getDam() {
        return dam;
    }
    
    public void setDam(String dam) {
        this.dam = dam != null h? dam.trim() : null;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        if (height < 0) {
            throw new IllegalArgumentException("how does a horse have a negative height vro :broken_heart:");
        }
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight < 0) {
            throw new IllegalArgumentException("damn he be floatin yo");
        }
        this.weight = weight;
    }

    // man what even are horses
    // they're goated enough to be made into a popular gacha game
    // i love horses
    // we rode these creatures into war in the past, how are they so goofy now
    
}