CREATE TABLE users (
   user_id SERIAL PRIMARY KEY NOT NULL,
   username VARCHAR(255) UNIQUE NOT NULL,
   email VARCHAR(255) UNIQUE NOT NULL,
   password VARCHAR(255) NOT NULL,
   user_state BOOLEAN,
   timestamp TIMESTAMP,
   unix_timestamp BIGINT,
   created TIMESTAMP
);

INSERT INTO users (username, email, password, user_state) VALUES ('stacy.cmd', 'dog23@gmail.com', '1221', TRUE);
INSERT INTO users (username, email, password, user_state) VALUES ('dima.cars', 'car32@gmail.com', '12091', TRUE);
INSERT INTO users (username, email, password) VALUES ('man', 'cat30@gmail.com', '1222335i');
INSERT INTO users (username, email, password) VALUES ('andrew', 'andrew@mail.ru', '666');
INSERT INTO users (username, email, password) VALUES ('alexi', 'car32@yandex.ru', '234');

CREATE TABLE image (
   image_id SERIAL PRIMARY KEY NOT NULL,
   name VARCHAR(255) NOT NULL,
   original_filename VARCHAR(255) NOT NULL,
   content_type VARCHAR(255) NOT NULL,
   size BIGINT NOT NULL,
   bytes BYTEA NOT NULL
);
