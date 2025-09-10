\gset
DROP TABLE IF EXISTS  action,location,emotion_state,view,memory,person,action_type;


CREATE TABLE action_type
(
    id SERIAL PRIMARY KEY,
    activity TEXT
);
CREATE TABLE location
(
    id SERIAL PRIMARY KEY,
    location_name TEXT NOT NULL UNIQUE,
    curtime TEXT NOT NULL
);
CREATE TABLE memory
(    
    id SERIAL PRIMARY KEY,
    storage TEXT
);
CREATE TABLE view
(
    id SERIAL PRIMARY KEY,
    location_id int REFERENCES location(id) ON DELETE CASCADE,
    memory_id int REFERENCES memory(id) ON DELETE CASCADE,
    desciption TEXT
);
CREATE TABLE person
(
    id SERIAL PRIMARY KEY,
    memory_id int REFERENCES memory(id) ON DELETE CASCADE,
    person_name TEXT NOT NULL
    
);
CREATE TABLE action
(
    id SERIAL PRIMARY KEY,
    action_type_id int REFERENCES action_type(id) ON DELETE CASCADE,
    person_id int REFERENCES person(id) ON DELETE CASCADE,
    action_time TIMESTAMP
);
CREATE TABLE emotion_state
(
    id SERIAL PRIMARY KEY,
    action_type_id int REFERENCES action_type(id) ON DELETE CASCADE,
    emotions TEXT
);


INSERT INTO action_type(activity)
VALUES
('делал всё, что было в его силах');

INSERT INTO location(location_name,curtime)
VALUES
('Юпитер', 'Полночь');


INSERT INTO memory(storage) 
VALUES
('вся жизнь');

INSERT INTO view(location_id,memory_id,desciption)
VALUES
(1,1,'волшебное зрелище');


INSERT INTO person(memory_id, person_name)
VALUES
(1,'Флойд');

INSERT INTO action(action_type_id,person_id,action_time)
VALUES
(1,1,'2023-10-19 10:23:54');

INSERT INTO emotion_state(action_type_id,emotions)
VALUES
(1,'спокойный и беззаботный');


