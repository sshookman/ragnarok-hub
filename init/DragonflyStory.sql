CREATE TABLE "chapter" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "title" TEXT NOT NULL , "body" TEXT NOT NULL );
CREATE TABLE "page" ("id" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , "body" TEXT NOT NULL , "isPrompt" BOOL NOT NULL );
