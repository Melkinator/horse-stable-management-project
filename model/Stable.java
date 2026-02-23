package model;

public class Stable {
    private String name;
    private Staff manager;
    private Horse[] horses;

    private int horseCount;

    public Stable(String name, int capacity, Staff manager) {
        setName(name);
        setManager(manager);
        this.horses = new Horse[capacity];
        this.horseCount = 0;
    }

    // getters
    public String getName() { return name; }
    public Staff getManager() { return manager; }
    public Horse[] getHorses() { return horses; }
    public int getHorseCount() { return horseCount; }

    // setters
    public void setName(String name) {
        if (name==null||name.trim().isEmpty()) {
            this.name = "No Name";
        } else {
            this.name = name.trim();
        }
    }

    public void setManager(Staff manager) {
        this.manager = manager;
    }

    public void addHorse(Horse horse) {
        if (horse==null) return;
        if (horseCount >= horses.length) return;
        for (int i=0; i<horseCount; i++) {
            Horse h = horses[i];
            if (h.equals(horse)) return;
        }
        horses[horseCount++] = horse;
    }

    public Horse getHorse(int i) {
        if (i>=0&&i<horseCount) {
            return horses[i];
        }
        return null;
    }
}