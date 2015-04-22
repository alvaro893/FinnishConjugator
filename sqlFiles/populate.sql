-- This file will populate the database (DML queries only)

use conjugator;

insert into verb (type, name, description) values

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
(6, 'lämmetä', 'to become warm'),

(7, 'nähdä', 'to see'),
(7, 'tehdä', 'to do, to make');


