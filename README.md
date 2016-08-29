# Golem MUD Hub
Java Based MUD Engine and Telnet Hub for MUDs 

![Build](https://travis-ci.org/sshookman/ProjectRead.svg?branch=master) ![License](https://img.shields.io/badge/license-GPL%20v3-brightgreen.svg) [![Coverage](https://codecov.io/gh/sshookman/ProjectRead/branch/master/graph/badge.svg)](https://codecov.io/gh/sshookman/ProjectRead)

---

Common Commands
---

- **Validate:**       mvn pmd:pmd pmd:cpd
- **Build:**          mvn clean package
- **Docker Build:**   docker build -t seanshookman/golem .
- **Docker Run:**     docker run --name golem -d -p 1127:1127 seanshookman/golem
- **Play:**           telnet localhost 1127
- **Docker Kill:**    docker ps -aq | xargs docker kill | xargs docker rm

---

Project Structure:
---

```
golem/mud
--command               -- Common Command Interface and Abstract Classes
--das                   -- Common Data Service Classes and Abstract Class
----model				-- Common Database Objects and Abstract Class
--exception 			-- Common Exception Classes
--hub
----command             -- Hub Command Objects
----das					-- Hub Data Access Service Classes
------model				-- Hub Database Objects
----telnet				-- Hub Telnet Classes
--util                  -- Common Utility Classes
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
