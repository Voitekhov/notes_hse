CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    login       varchar,
    first_name  varchar,
    second_name varchar
);


CREATE TABLE groups
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title       varchar,
    description varchar,
    color       varchar,
    user_id     int,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE notes
(
    id          INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    title       varchar,
    description varchar,
    time_limit  timestamp,
    is_done     boolean,
    color       varchar,
    group_id    int,
    user_id     int,
    CONSTRAINT fk_group_id FOREIGN KEY (group_id) REFERENCES groups (id) ON DELETE CASCADE,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

INSERT INTO users
VALUES (1, 's@mail.ru', 's', 's');
