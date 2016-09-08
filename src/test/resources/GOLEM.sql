DROP TABLE IF EXISTS "entity";
DROP TABLE IF EXISTS "c_display";
DROP TABLE IF EXISTS "c_pickup";

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
