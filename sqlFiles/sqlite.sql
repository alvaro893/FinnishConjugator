-- this is the database for the SQLite implementation only.
-- for the MySQL implementation see the other 2 files
BEGIN TRANSACTION;
CREATE TABLE `verb` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`type`	INTEGER NOT NULL,
	`name`	TEXT NOT NULL,
	`description`	TEXT
);
INSERT INTO `Verb` VALUES (1,0,'olla','to be');
INSERT INTO `Verb` VALUES (2,1,'puhua','to talk, to speak');
INSERT INTO `Verb` VALUES (3,1,'sanoa','to say');
INSERT INTO `Verb` VALUES (4,1,'istua','to sit');
INSERT INTO `Verb` VALUES (5,1,'ajaa','to drive');
INSERT INTO `Verb` VALUES (6,1,'alkaa','to start, to begin');
INSERT INTO `Verb` VALUES (7,1,'etsiä','to look for, to seek');
INSERT INTO `Verb` VALUES (8,1,'katsoa','to look at');
INSERT INTO `Verb` VALUES (9,1,'kysyä','to ask');
INSERT INTO `Verb` VALUES (10,1,'lukea','to read');
INSERT INTO `Verb` VALUES (11,1,'odottaa','to wait, to expect, to be expecting(pregnant)');
INSERT INTO `Verb` VALUES (12,1,'rakastaa','to love');
INSERT INTO `Verb` VALUES (13,1,'tietää','to know (something, not someone)');
INSERT INTO `Verb` VALUES (14,2,'saada','to get');
INSERT INTO `Verb` VALUES (15,2,'juoda','to drink');
INSERT INTO `Verb` VALUES (16,2,'syödä','to eat');
INSERT INTO `Verb` VALUES (17,3,'tulla','to come');
INSERT INTO `Verb` VALUES (18,3,'mennä','to go');
INSERT INTO `Verb` VALUES (19,3,'nousta','to rise');
INSERT INTO `Verb` VALUES (20,4,'haluta','to want');
INSERT INTO `Verb` VALUES (21,4,'avata','to open');
INSERT INTO `Verb` VALUES (22,4,'herätä','to want');
INSERT INTO `Verb` VALUES (23,5,'hallita','to rule, to govern, to be able to');
INSERT INTO `Verb` VALUES (24,5,'tarvita','to need');
INSERT INTO `Verb` VALUES (25,5,'valita','to choose');
INSERT INTO `Verb` VALUES (26,6,'vaaleta','to whiten');
INSERT INTO `Verb` VALUES (27,6,'lämmetä','to become warm');
INSERT INTO `Verb` VALUES (28,7,'nähdä','to see');
INSERT INTO `Verb` VALUES (29,7,'tehdä','to do, to make');
CREATE UNIQUE INDEX `idx_verb_name`  ON `verb` (name);
COMMIT;
