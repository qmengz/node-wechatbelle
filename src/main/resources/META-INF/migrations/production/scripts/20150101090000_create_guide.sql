--// Create Guide

CREATE TABLE guide_entry (
ID NUMERIC(20,0) AUTO_INCREMENT NOT NULL,
NAME VARCHAR(50) NOT NULL
);

INSERT INTO guide_entry(ID, NAME) VALUES (1, 'bob is must boy!');
INSERT INTO guide_entry(ID, NAME) VALUES (2, 'john is must boy!');
INSERT INTO guide_entry(ID, NAME) VALUES (3, 'samung is must boy!');
INSERT INTO guide_entry(ID, NAME) VALUES (4, 'skype is must boy!');


--//@UNDO

DROP TABLE guide_entry;