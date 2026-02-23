package model;

public class Horse {
    private int id;
    private String name;
    private String breed;
    private String color;
    private Gender gender;
    private int stallId;
    private double weight;
    private double height;

    public enum Gender {
        STALLION, MARE, GELDING, FILLY, COLT;

        @Override
        public String toString() {
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }
    } // you use this enum by writing uma.SetGender(Gender.placeholder) (placeholder is any of the Genders listed here) cuh

    // the constructors
    
    public Horse(String name, String breed, String color, Gender gender, int stallId, double weight, double height) {
        setName(name);
        setBreed(breed);
        setColor(color);
        setGender(gender);
        this.stallId = stallId;
        this.weight = weight;
        this.height = height;
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getColor() { return color; }
    public Gender getGender() { return gender; }
    public int getStallId() { return stallId; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }

    // setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        if (name==null||name.trim().isEmpty()) {
            this.name = "No Name";
        } else {
            this.name = name.trim();
        }
    }

    public void setBreed(String breed) {
        if (breed==null||breed.trim().isEmpty()) {
            this.breed = "Unknown";
        } else {
            this.breed = breed.trim();
        }
    }

    public void setColor(String color) {
        if (color==null||color.trim().isEmpty()) {
            this.color = "Unknown";
        } else {
            this.color = color.trim();
        }
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setStallId(int stallId) {
        if (stallId < 0) {
            throw new IllegalArgumentException("stall id cant be lower than 0 twin");
        }
        this.stallId = stallId;
    }

    public void setHeight(double height) {
        if (height < 0) {
            throw new IllegalArgumentException("how does a horse have a negative height vro :broken_heart:");
        }
        this.height = height;
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