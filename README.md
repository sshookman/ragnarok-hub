# Golem MUD Hub
Java Based MUD Engine and Telnet Hub for MUDs 

![Build](https://travis-ci.org/sshookman/ProjectRead.svg?branch=master) ![License](https://img.shields.io/badge/license-GPL%20v3-brightgreen.svg)

---

Common Commands
---

- **Validate:**       mvn pmd:pmd pmd:cpd
- **Coverage:**       mvn cobertura:cobertura
- **Build:**          mvn clean package
- **Docker Build:**   docker build -t seanshookman/golem .
- **Docker Run:**     docker run --name golem -d -p 1127:1127 seanshookman/golem
- **Play:**           telnet localhost 1127
- **Docker Kill:**    docker ps -aq | xargs docker kill | xargs docker rm

---

Project Structure:
---

```
golem/mud/hub
--command               -- Command Objects
--das					-- Data Access Service Classes
----model				-- Database Objects
--exception 			-- Custom Exception Classes
--telnet				-- Telnet Related Classes for Client, Server, Session, etc
--util                  -- Various Static Utility Classes
```

---

Server Structure
---

```
golem
├── server 
│   ├── GOLEM.gmh 
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
```

---

Database
---

```
.gmh    -- Golem MUD Hub
.glm    -- Golem MUD
.sglm   -- Golem MUD Saved Game
```
