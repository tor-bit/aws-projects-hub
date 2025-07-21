package models;

import java.util.List;
import java.util.Map;
import java.util.Date;
import java.time.*;


public class Case {
    public int case_id;
    public String description;
    public List<String> required_skills;
    public String complexity;
    public int severity;
    public Map<String, Date> Assigned;
    public boolean isAssigned;

    // Default constructor
    public Case() {}

    // Parameterized constructor
    public Case(int case_id, String description, List<String> required_skills,
                String complexity, int severity, Map<String, Date> Assigned, boolean isAssigned) {
        this.case_id = case_id;
        this.description = description;
        this.required_skills = required_skills;
        this.complexity = complexity;
        this.severity = severity;
        this.Assigned = Assigned;
        this.isAssigned = isAssigned;
    }

    // Getters
    public int getCase_id() { 
        return case_id; 
    }
    public String getDescription() { 
        return description; 
    }
    public List<String> getRequired_skills() { 
        return required_skills; 
    }
    public String getComplexity() { 
        return complexity; 
    }
    public int getSeverity() {
         return severity; 
    }
    public Map<String, Date> getAssigned() {
         return Assigned; 
    }
    public boolean isAssigned() {
        return isAssigned;
    };

    // Setters
    public void setCase_id(int case_id) { 
        this.case_id = case_id; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }
    public void setRequired_skills(List<String> required_skills) { 
        this.required_skills = required_skills; 
    }
    public void setComplexity(String complexity) { 
        this.complexity = complexity; 
    }
    public void setSeverity(int severity) { 
        this.severity = severity; 
    }
    public void setAssigned(Map<String, Date> Assigned) { 
        this.Assigned = Assigned; 
    }
    public void setAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    };

    // toString
    @Override
    public String toString() {
        StringBuilder skills = new StringBuilder("[");
        for (int i = 0; i < required_skills.size(); i++) {
            skills.append(required_skills.get(i));
            if (i < required_skills.size() - 1) {
                skills.append(", ");
            }
        }
        skills.append("]");

        return "Case{" +
                "case_id=" + case_id +
                ", description='" + description + '\'' +
                ", required_skills=" + skills +
                ", complexity='" + complexity + '\'' +
                ", severity=" + severity +
                ", Assigned=" + Assigned +
                '}';
    }
}
