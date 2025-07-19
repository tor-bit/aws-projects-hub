import java.util.List;
import java.util.Map;


public class Case {
    private int caseId;
    private String description;
    private List<String> requiredSkills;
    private String complexity;
    private int severity;
    private Map<String, String> assigned; 

    // Constructor
    public Case(int caseId, String description, List<String> requiredSkills, String complexity, int severity, Map<String, String> assigned) {
        this.caseId = caseId;
        this.description = description;
        this.requiredSkills = requiredSkills;
        this.complexity = complexity;
        this.severity = severity;
        this.assigned = assigned;
    }

    // Getters
    public int getCaseId() {
        return caseId;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public String getComplexity() {
        return complexity;
    }

    public int getSeverity() {
        return severity;
    }
    public Map<String, String> getAssigned() {
        return assigned;
    }

    // Setters
    public void setCaseId(int caseId) {
        this.caseId = caseId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRequiredSkills(List<String> requiredSkills) {
        this.requiredSkills = requiredSkills;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setAssigned(Map<String, String> assigned) {
        this.assigned = assigned;
    }

}
