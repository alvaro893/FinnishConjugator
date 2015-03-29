-- This file will populate the database (DML queries only)

use conjugator;

insert into verb (type, name, description) values

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

(2, 'saada', 'to get'),
(2, 'juoda', 'to drink'),
(2, 'syödä', 'to eat'),

(3, 'Tulla', 'to come'),
(3, 'Mennä', 'to go'),
(3, 'nousta', 'to rise'),

(4, 'haluta', 'to want'),
(4, 'avata', 'to open'),
(4, 'herätä', 'to want'),

(5, 'hallita', 'to rule, to govern, to be able to'),
(5, 'tarvita', 'to need'),
(5, 'valita', 'to choose'),

(6, 'vaaleta', 'to whiten'),
(6, 'lämmetä', 'to become warm');
