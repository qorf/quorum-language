CREATE TABLE run_code
(
quorum_version VARCHAR(65535) NOT NULL,
code TEXT NOT NULL,
output TEXT NOT NULL,
times_requested BIGINT
);

CREATE TABLE class_ratings
(
static_key VARCHAR(65535) NOT NULL,
user VARCHAR(255) NOT NULL,
rating_name TINYINT,
rating_example TINYINT,
rating_description TINYINT,
PRIMARY KEY (user)
);

CREATE TABLE action_ratings
(
class_static_key VARCHAR(65535) NOT NULL,
static_key VARCHAR(65535) NOT NULL,
user VARCHAR(255) NOT NULL,
rating_name TINYINT,
rating_example TINYINT,
rating_description TINYINT,
PRIMARY KEY (user)
);

CREATE TABLE parameter_ratings
(
class_static_key VARCHAR(65535) NOT NULL,
action_static_key VARCHAR(65535) NOT NULL,
static_key VARCHAR(65535) NOT NULL,
user VARCHAR(255) NOT NULL,
rating_name TINYINT,
rating_example TINYINT,
rating_description TINYINT,
PRIMARY KEY (user)
);


ALTER TABLE class_ratings ADD FOREIGN KEY user_fk_00 (user) REFERENCES sodbeans_users (username);

ALTER TABLE action_ratings ADD FOREIGN KEY user_fk_01 (user) REFERENCES sodbeans_users (username);

ALTER TABLE parameter_ratings ADD FOREIGN KEY user_fk_02 (user) REFERENCES sodbeans_users (username);

ALTER TABLE sodbeans_users ADD COLUMN google_id varchar(255);

ALTER TABLE sodbeans_users ADD COLUMN email varchar(255);

ALTER TABLE sodbeans_users ADD UNIQUE(email);
