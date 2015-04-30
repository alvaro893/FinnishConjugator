-- this is the database for the SQLite implementation only.
-- for the MySQL implementation see the other 2 files
BEGIN TRANSACTION;
CREATE TABLE `verb` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`type`	INTEGER NOT NULL,
	`name`	TEXT NOT NULL,
	`description`	TEXT
);

insert into `verb` (type, name, description) values

-- olla is unique case here
(0, 'olla', 'to be'),

(1, 'puhua', 'to talk, to speak'),
(1, 'sanoa', 'to say'),
(1, 'istua', 'to sit'),
(1, 'ajaa', 'to drive'),
(1, 'alkaa', 'to start, to begin'),
(1, 'etsiä', 'to look for, to seek'),
(1, 'katsoa', 'to look at'),
(1, 'kysyä', 'to ask'),
(1, 'lukea', 'to read'),
(1, 'odottaa', 'to wait, to expect, to be expecting(pregnant)'),
(1, 'rakastaa', 'to love'),
(1, 'tietää', 'to know (something, not someone)'),
(1, 'antaa', 'to give, to let (someone do something), to allow'),
(1, 'asua', 'to live in a place'),
(1, 'autta', 'to help'),
(1, 'herättää', 'to wake up'),
(1, 'hoitaa', 'to take care of'),
(1, 'kirjoittaa', 'to write'),
(1, 'laajentaa', 'to expand'),
(1, 'laskea', 'to count'),
(1, 'lähteä', 'to leave'),
(1, 'maksaa', 'to pay, to cost'),
(1, 'muistaa', 'to remenber'),
(1, 'neuvoa', 'to give advice'),
(1, 'ostaa', 'to buy'),
(1, 'ottaa', 'to take'),
(1, 'paistaa', 'to fry, to shine'),
(1, 'rakastua', 'to fall in love'),
(1, 'saartaa', 'to shatter'),
(1, 'sallia', 'to allow'),
(1, 'soittaa', 'to call  (someone on the phone), to play (an instrument, records, cds)'),
(1, 'sortaa', 'to collapse'),
(1, 'tuntea', 'to feel'),
(1, 'unohtaa', 'to forget'),
(1, 'unohtua', 'to forget oneself'),
(1, 'sallia', 'to allow'),
(1, 'vaatia', 'to demand'),
(1, 'ymmärtää', 'to understand'),

(2, 'saada', 'to get'),
(2, 'juoda', 'to drink'),
(2, 'syödä', 'to eat'),
(2, 'käydä', 'to vist, to go'),
(2, 'luennoida', 'to lecture'),
(2, 'myydä', 'to shell'),
(2, 'pysäköidä', 'to park'),
(2, 'soida', 'to ring (out)'),
(2, 'terrorisoida', 'to terrorize'),
(2, 'tuoda', 'to bring'),
(2, 'uida', 'to swing'),
(2, 'viedä', 'to take'),
(2, 'voida', 'to be able to'),

(3, 'Tulla', 'to come'),
(3, 'Mennä', 'to go'),
(3, 'nousta', 'to rise'),
(3, 'ajatella', 'to think about something'),
(3, 'hymyillä', 'to smile'),
(3, 'julkaista', 'to publish'),
(3, 'kiistellä', 'to quarrel'),
(3, 'kävellä', 'to walk'),
(3, 'ommella', 'to sew'),
(3, 'opetella', 'to learn'),
(3, 'opiskella', 'to study'),
(3, 'panna', 'to put'),
(3, 'pestä', 'to wash'),
(3, 'purra', 'to bite'),
(3, 'ratkaista', 'to solve'),
(3, 'riidellä', 'to fight'),
(3, 'surra', 'to mourn'),
(3, 'suudella', 'to kiss'),
(3, 'tapella', 'to fight'),
(3, 'työskennellä', 'to rise'),



(4, 'haluta', 'to want'),
(4, 'avata', 'to open'),
(4, 'herätä', 'to wake up'),
(4, 'erota', 'to quit'),
(4, 'houmata', 'to notice'),
(4, 'hypätä', 'to jump'),
(4, 'herätä', 'to want'),


(5, 'hallita', 'to rule, to govern, to be able to'),
(5, 'tarvita', 'to need'),
(5, 'valita', 'to choose'),

(6, 'vaaleta', 'to whiten'),
(6, 'lämmetä', 'to become warm'),

(7, 'nähdä', 'to see'),
(7, 'tehdä', 'to do, to make');

CREATE UNIQUE INDEX `idx_verb_name`  ON `verb` (name);
COMMIT;
