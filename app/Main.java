package app;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Agent;
import models.Case;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Load agents
            List<Agent> agents = mapper.readValue(
                new File("data/agents.json"),
                new TypeReference<List<Agent>>() {}
            );

            // Load cases
            List<Case> cases = mapper.readValue(
                new File("data/cases.json"),
                new TypeReference<List<Case>>() {}
            );

            // Assign cases using CaseAssigner
            System.out.println("Starting case assignment...");
            CaseAssigner.assignCases(cases, agents);

            // Print final state
            System.out.println("\nFinal case assignments:");
            for (Case c : cases) {
                System.out.println(c.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
