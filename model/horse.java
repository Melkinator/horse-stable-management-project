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

    public horse() {}

    public horse(String name, String breed, String color, Gender gender, LocalDate birthDate) {
        this();
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.gender = gender;
        this.birthDate = birthDate;
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
}