CREATE TABLE IF NOT EXISTS run_code
(
quorum_version TEXT NOT NULL,
code TEXT NOT NULL,
output TEXT NOT NULL,
times_requested BIGINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS class_ratings
(
static_key TEXT NOT NULL,
user VARCHAR(255) NOT NULL,
rating_name TINYINT,
rating_example TINYINT,
rating_description TINYINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS action_ratings
(
class_static_key TEXT NOT NULL,
static_key TEXT NOT NULL,
user VARCHAR(255) NOT NULL,
rating_name TINYINT,
rating_example TINYINT,
rating_description TINYINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE IF NOT EXISTS parameter_ratings
(
class_static_key TEXT NOT NULL,
action_static_key TEXT NOT NULL,
static_key TEXT NOT NULL,
user VARCHAR(255) NOT NULL,
rating_name TINYINT,
rating_example TINYINT,
rating_description TINYINT
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

ALTER TABLE sodbeans_users ADD COLUMN google_id varchar(255);

ALTER TABLE sodbeans_users ADD COLUMN email varchar(255);

ALTER TABLE sodbeans_users ADD UNIQUE(email);

ALTER TABLE sodbeans_users ADD UNIQUE(username);

ALTER TABLE class_ratings ADD FOREIGN KEY class_ratings (user) REFERENCES sodbeans_users (username);

ALTER TABLE action_ratings ADD FOREIGN KEY action_ratings (user) REFERENCES sodbeans_users (username);

ALTER TABLE parameter_ratings ADD FOREIGN KEY parameter_ratings (user) REFERENCES sodbeans_users (username);