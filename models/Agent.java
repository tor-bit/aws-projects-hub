package models;
public class Agent {
    private int id;
    private String name;
    private String[] skills;
    private int experience;
    private boolean available;


    // Constructor
    public Agent(int id, String name, String[] skills, int experience, boolean available) {
        this.id = id;
        this.name = name;
        this.skills = skills;
        this.experience = experience;
        this.available = available;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getSkills() {
        return skills;
    }

    public int getExperience() {
        return experience;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSkills(String[] skills) {
        this.skills = skills;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
