-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

INSERT INTO booking (field1, field2) VALUES ('value1', 'value2');
INSERT INTO booking (field1, field2) VALUES ('value3', 'value4');

INSERT INTO vehicle (type, manufacturer, model, registration_year, price) VALUES ('value1', 'value2', 'value3', 2024, 0.0);
INSERT INTO vehicle (type, manufacturer, model, registration_year, price) VALUES ('value4', 'value5', 'value6', 2024, 0.0);
