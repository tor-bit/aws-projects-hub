package app;

import models.Case;
import models.Agent;

import java.util.*;
import java.util.Date;
import java.time.LocalDate;

public class CaseAssigner {
    /**
     * Assign cases to agents based on severity, skill matching, and experience.
     * Highest severity first (lower number), then best combined score:
     * skill match count + years of experience.
     */
    public static void assignCases(List<Case> cases, List<Agent> agents) {
        // 1. Sort cases by severity ascending (1 = highest priority)
        cases.sort(Comparator.comparingInt(Case::getSeverity));

        for (Case caze : cases) {
            // 2. Check if case already assigned
            Map<String, Date> assignedInfo = caze.getAssigned();
            // if (assignedInfo != null && assignedInfo.getOrDefault("agent_name", new Date(0)).toString().length() > 0) {
            //     continue;
            // }

            if (caze.isAssigned()) {
                System.out.println("Case " + caze.getCase_id() + " is already assigned.");
                continue;
            }

            // 3. Gather candidate agents and compute scores
            Map<Agent, Integer> scoreMap = new HashMap<>();
            for (Agent a : agents) {
                if (!a.isAvailable()) continue;

                int matchCount = 0;
                List<String> reqSkills = caze.getRequired_skills();
                String[] agentSkills = a.getSkills();
                for (String req : reqSkills) {
                    for (String has : agentSkills) {
                        if (req.equalsIgnoreCase(has)) {
                            matchCount++;
                        }
                    }
                }
                if (matchCount > 0) {
                    int combinedScore = matchCount * 10 + a.getExperience(); // making the skills count more significant
                    scoreMap.put(a, combinedScore);
                }
                else {
                    int combinedScore = a.getExperience(); // Only experience if no skill match.
                    scoreMap.put(a, combinedScore);
                }
            }

            // 4. Select best agent by highest score
            Agent bestAgent = null;
            int bestScore = -1;
            for (Map.Entry<Agent, Integer> entry : scoreMap.entrySet()) {
                if (entry.getValue() > bestScore) {
                    bestScore = entry.getValue();
                    bestAgent = entry.getKey();
                }
            }
            // 5. Assign case if agent found
            if (bestAgent != null) {
                caze.setAssigned(true);

                // Update case assignment details
                caze.getAssigned().clear(); // Clear previous assignments
                caze.getAssigned().put(bestAgent.getName(), new Date());
        
                // Need to update the agents object passed in the main method
                 for (Agent agent : agents) {
                     if (agent.getId() == bestAgent.getId()) {
                         agent.setAvailable(false);
                         break;
                     }
                }
            
                System.out.println("Assigned case " + caze.getCase_id()
                        + " to " + bestAgent.getName() + " (score=" + bestScore + ")");
            } else {
                System.out.println("No available agent with skills matching for case " + caze.getCase_id());

            }
        }
    }
}
