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
            List<Agent> agents = mapper.readValue(
                new File("data/agents.json"),
                new TypeReference<List<Agent>>() {}
            );
            for (Agent agent : agents) {
                System.out.println(agent.toString());
            }


            List<Case> cases = mapper.readValue(
                new File("data/cases.json"),
                new TypeReference<List<Case>>() {}
            );
            for (Case supportCase : cases) {
                System.out.println(supportCase.toString());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
