DROP TABLE IF EXISTS "entity";
DROP TABLE IF EXISTS "c_display";
DROP TABLE IF EXISTS "c_pickup";
DROP TABLE IF EXISTS "c_path";

CREATE TABLE "entity" (
        "id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE
    );

CREATE TABLE "c_display" (
        "id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,
        "entity_id" INTEGER NOT NULL,
        "message" TEXT NOT NULL
    );

CREATE TABLE "c_pickup" (
        "id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,
        "entity_id" INTEGER NOT NULL,
        "stackable" BOOLEAN DEFAULT FALSE,
        "quantity" INTEGER DEFAULT 1
    );

CREATE TABLE "c_path" (
        "id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE,
        "entity_id" INTEGER NOT NULL,
        "dest_entity_id" INTEGER NOT NULL,
        "name" TEXT NOT NULL,
        "direction" TEXT NOT NULL,
        "display_message" TEXT,
        "transition_message" TEXT
    );
