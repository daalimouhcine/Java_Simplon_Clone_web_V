CREATE TYPE rolls AS ENUM('admin', 'teacher', 'student');

CREATE TABLE if not exists accounts(
    fullName VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists admins(
    id SERIAL PRIMARY KEY,
    roll INT DEFAULT 1
) inherits (accounts);
CREATE TABLE if not exists teachers(
    id SERIAL PRIMARY KEY,
    roll INT DEFAULT 2
) inherits (accounts);

CREATE TABLE if not exists promos(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    teacher_id INTEGER REFERENCES teachers(id) DEFAULT NULL
);

CREATE TABLE if not exists students(
    id SERIAL PRIMARY KEY,
    roll INT DEFAULT 3,
    promoId INTEGER REFERENCES promos(id) DEFAULT NULL
) inherits (accounts);

CREATE TABLE if not exists briefs(
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    technologies VARCHAR(255)[] NOT NULL,
    promoId INTEGER REFERENCES promos(id) DEFAULT NULL,
    launched BOOLEAN DEFAULT FALSE
);

CREATE TABLE if not exists ValidationMessages(
    id SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES students(id) NOT NULL,
    brief_id INTEGER REFERENCES briefs(id) NOT NULL,
    message VARCHAR(255) NOT NULL,
    repoLink VARCHAR(255) NOT NULL
);

CREATE TABLE if not exists ValidationMessages(
    id SERIAL PRIMARY KEY,
    student_id INTEGER REFERENCES students(id) NOT NULL,
    brief_id INTEGER REFERENCES briefs(id) NOT NULL,
    message VARCHAR(255) NOT NULL,
    repoLink VARCHAR(255) NOT NULL
);
