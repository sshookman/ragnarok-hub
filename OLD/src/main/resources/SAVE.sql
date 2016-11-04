DROP TABLE IF EXISTS "state";

CREATE TABLE "state" (
        "id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,
        "location_entity_id" INTEGER NOT NULL
    );