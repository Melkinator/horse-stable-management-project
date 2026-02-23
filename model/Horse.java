package model;

public class Horse {
    private int id;
    private String name;
    private String breed;
    private String color;
    private Gender gender;
    private double weight;
    private double height;

    private static int horseCounter = 0;

    public enum Gender {
        STALLION, MARE, GELDING, FILLY, COLT;

        @Override
        public String toString() {
            return this.name().charAt(0) + this.name().substring(1).toLowerCase();
        }
    } // you use this enum by writing uma.SetGender(Gender.placeholder) (placeholder is any of the Genders listed here) cuh

    // the constructors

    public Horse(String name, String breed, String color, Gender gender, double weight, double height) {
        horseCounter++;
        this.id = horseCounter;
        setName(name);
        setBreed(breed);
        setColor(color);
        setGender(gender);
        setWeight(weight);
        setHeight(height);
    }

    // getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getBreed() { return breed; }
    public String getColor() { return color; }
    public Gender getGender() { return gender; }
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

    public void setHeight(double height) {
        if (height<0) {
            this.height = 0;
        } else {
            this.height = height;
        }
    }

    public void setWeight(double weight) {
        if (weight<0) {
            this.weight = 0;
        } else {
            this.weight = weight;
        }
    }

    @Override
    public String toString() {
        return "Horse [id=" + id + ", name=" + name + ", breed=" + breed + ", color=" + color + ", gender=" + gender
                + ", weight=" + weight + ", height=" + height + "]";
    }

    // man what even are horses
    // they're goated enough to be made into a popular gacha game
    // i love horses
    // we rode these creatures into war in the past, how are they so goofy now
    
}