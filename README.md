Golem MUD Hub
===
Java Based MUD Engine and Telnet Hub 

![Build](https://travis-ci.org/sshookman/ProjectRead.svg?branch=master)

---

Project Structure:
---

**Source Code**
```
src/main/java/golem/	-- Main Package
--client/				-- Telnet Client Related Classes
--das/					-- Data Access Service Classes
--model/				-- Database Objects
--rendering/			-- Telnet Input and Output Handling
--server/				-- Telnet Server Related Classes
src/test/java/golem/	-- Main Testing Package
```

---

Server Structure
---

**Directories**
```
├── GOLEM.gmh
├── library
│   ├── Throwing_Shadows.glm
│   └── Throwing_Light.glm
└── player
    ├── sean
    │   └── Wrathus_Throwing_Shadows.sglm
    ├── paul
    │   └── Crathus_Throwing_Shadows.sglm
    └── saul
        └── Mathus_Throwing_Shadows.sglm

GOLEM.gmh is the database file for the Hub itself.
The *library* directory contains all of the Golem MUD games.
The *player* directory contains folders for each player with their saved games.
```

**Database Extensions**
```
.gmh = Golem MUD Hub
.glm = Golem MUD
.sglm= Golem MUD Saved Game
```
