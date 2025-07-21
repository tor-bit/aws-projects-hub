package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Agent;
import models.Case;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Read in Agents and display them
            List<Agent> agents = mapper.readValue(
                new File("data/agents.json"),
                new TypeReference<List<Agent>>() {}
            );
            for (Agent agent : agents) {
                System.out.println(agent.toString());
            }

            // Read in Cases and display them
            List<Case> cases = mapper.readValue(
                new File("data/cases.json"),
                new TypeReference<List<Case>>() {}
            );
            for (Case supportCase : cases) {
                System.out.println(supportCase.toString());
            }

            for (Case supportCase : cases) {
                for (Agent agent : agents) {
                    // need to check if the agent has the required skills
                    if (agent.isAvailable() && !supportCase.isAssigned()) {
                        Date date = Date.from(LocalDateTime.now()
                        .atZone(ZoneId.systemDefault())
                        .toInstant());
                        // Assign the case to the agent
                        Map<String, Date> assignee = new HashMap<>();
                        assignee.put(agent.getName(), date);
                        supportCase.setAssigned(assignee);

                        agent.setAvailable(false); // Mark agent as unavailable
                        System.out.println("Assigned case " + supportCase.getCase_id() + " to agent " + agent.getName());
                        break;
                    }
                    else {
                        System.out.println("Agent " + agent.getName() + " is not available.");
                    }
                }
            };

            System.out.println("All cases have been assigned.");
            for (Case supportCase : cases) {
                System.out.println(supportCase.toString());
            };


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
