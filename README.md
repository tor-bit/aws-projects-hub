# Case Routing Simulator

A Java-based simulator that models incoming support cases and routes them to the best‑matched available engineer based on case severity, required skills, and agent experience.

---

## Features

- **Severity prioritization**  
  Cases with lower `severity` values (1 = highest urgency) are assigned first.

- **Skill matching**  
  Agents are scored by the number of required skills they share with a case.

- **Experience bonus**  
  Each year of experience adds +1 to an agent’s match score.

- **Simple assignment**  
  The highest‑scoring available agent “wins” the case.

- **Console output**  
  Assignments and final state are printed to the terminal.

---

## Project Structure

```
call-center-simulator/
├── data/
│   ├── agents.json              # List of Agent records
│   └── cases.json               # List of Case records
│
├── lib/                         # Third‑party JARs (Jackson) - To assist with reading from the files
│   ├── jackson-core-*.jar
│   ├── jackson-databind-*.jar
│   └── jackson-annotations-*.jar
│
├── models/
│   ├── Agent.java               # POJO for support engineers
│   └── Case.java                # POJO for support cases
│
├── app/
│   ├── CaseAssigner.java        # Core assignment logic
│   └── Main.java                # Entry point (loads JSON & invokes assigner)
│
└── out/                         # Compiled .class files (javac output)
```

---

## Prerequisites

- **Java 8+**  
- **Jackson** JARs placed in `lib/`  
- A terminal or command prompt

---

## Build & Run
1. **Compile** all sources:

**macOS/Linux**  
```
   mkdir -p out
   javac -cp "lib/*" -d out models/*.java app/*.java
```
**windows**
```
mkdir out
javac -cp "lib/*;out" -d out models/*.java app/*.java
```

2. Run the simulator:

**macOS/Linux**  
```
java -cp "out:lib/*" app.Main
```
**windows**
```
java -cp "out;lib/*" app.Main
```

## You should see output similar to:
```
Starting case assignment...
Assigned case 101 to Thabile (score=13)
Assigned case 102 to Nkosi (score=15)
Assigned case 103 to Lerato (score=4)
Assigned case 104 to Sipho (score=12)
No available agent with skills matching for case 105

Final case assignments:
Case{case_id=101, description='Production server is down. No customer access.', required_skills=[aws, linux, ec2], complexity='high', severity=1, Assigned={Thabile=Wed Jul 23 23:59:48 SAST 2025}}
…
```

## Next Steps:
```
Tie‑breaking: Introduce agent load or random selection for equal scores.
Dynamic updates: Reload JSON periodically or watch for file changes.
Persistence: Write updated assignments back to JSON or a database.
Concurrency: Simulate asynchronous case arrivals.
```