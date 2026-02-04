class Horse {
    int id;
    String name;
    String breed;
    double weight;
    boolean forSale;

    Horse(int id, String name, String breed, double weight, boolean forSale) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.weight = weight;
        this.forSale = forSale;
    }

    void printHorse() {
        System.out.println("Horse: " + name + ", Breed: " + breed + ", Weight: " + weight);
    }
}

class Stable {
    String stableName;
    Horse[] horses;
    int horseCount;
    int capacity;

    Stable(String stableName, int capacity) {
        this.stableName = stableName;
        this.capacity = capacity;
        horses = new Horse[capacity];
        horseCount = 0;
    }

    void addHorse(Horse h) {
        if (horseCount < capacity) {
            horses[horseCount] = h;
            horseCount++;
        }
    }

    Horse findHorseByName(String name) {
        for (int i = 0; i < horseCount; i++) {
            if (horses[i].name.equals(name)) {
                return horses[i];
            }
        }
        return null;
    }
}

class Staff {
    String staffName;
    int staffId;
    Horse assignedHorse;
    Stable workplace;

    Staff(String staffName, int staffId, Horse assignedHorse, Stable workplace) {
        this.staffName = staffName;
        this.staffId = staffId;
        this.assignedHorse = assignedHorse;
        this.workplace = workplace;
    }

    void printStaff() {
        System.out.println(staffName + " cares for " + assignedHorse.name +
                " at " + workplace.stableName);
    }
}

class Buyer {
    String buyerName;
    Horse horse;
    double snapshotWeight;
    double budget;

    Buyer(String buyerName, Horse horse, double budget) {
        this.buyerName = buyerName;
        this.horse = horse;
        this.budget = budget;

        // SNAPSHOT (primitive copy)
        snapshotWeight = horse.weight;
    }

    void printBuyerInfo() {
        System.out.println("Buyer: " + buyerName);
        System.out.println("Horse weight at purchase time: " + snapshotWeight);
    }
}

class Main {
    public static void main(String[] args) {

        Horse h1 = new Horse(1, "Still in Love", "Thoroughbred", 500, true);
        Horse h2 = new Horse(2, "Wonder Acute", "Thoroughbred", 480, false);

        Stable stable = new Stable("Sunny Stables", 5);
        stable.addHorse(h1);
        stable.addHorse(h2);

        System.out.println("Horse in array = " + stable.horses[1].name);

        Horse found = stable.findHorseByName("Wonder Acute");
        if (found == null) {
            System.out.println("Horse not found.");
        }

        Staff staff = new Staff("Melk", 101, h1, stable);
        staff.printStaff();

        // f1
        int original = 5;
        int copy = original;
        copy = 10;
        System.out.println("f1: original = " + original + ", copy = " + copy);

        // f2
        Horse refHorse = h1;
        refHorse.weight = 550;
        System.out.println("f2: h1.weight = " + h1.weight);

        // f3
        h2.name = "Neo Universe";
        System.out.println("f3: horse in the array = " + stable.horses[1].name);

        // f4
        Buyer buyer = new Buyer("John", h1, 10000);
        h1.weight = 600;
        buyer.printBuyerInfo();
    }
}