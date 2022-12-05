-- To generate database
drop database FMDB; -- Fresher manager database
create database if not exists FMDB;
use FMDB;

-- FRESHER
DROP TABLE IF EXISTS fresher;
CREATE TABLE fresher
(
    id            BIGINT       NOT NULL AUTO_INCREMENT,
    deleted       BIT(1)       NOT NULL DEFAULT 0,
    created_on    datetime NULL,
    last_modified datetime NULL,
    name          VARCHAR(255) NOT NULL,
    dob           date         NOT NULL,
    address       VARCHAR(255) NULL,
    phone         VARCHAR(255) NULL,
    email         VARCHAR(255) NULL,
    CONSTRAINT pk_fresher PRIMARY KEY (id)
);
insert into  fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUE
(1,0,'2022-11-21','2022-05-11','Pear','2001-05-07','MK Kingdom','091333332','leducanh@gmail.com');
insert into  fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUE
    (2,0,'2022-05-21','2022-04-11','John','2001-05-07','Tokyo ','091239932','John@gmail.com');
insert into  fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUE
    (3,0,'2022-03-21','2022-03-11','Michael','2001-05-07','North America','092853432','Michael@gmail.com');
insert into  fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUE
    (4,0,'2022-02-21','2022-01-11','Lear','2001-05-07','MK Kingdom','0432456732','Learlear@gmail.com');
insert into  fresher(id, deleted, created_on, last_modified, name, dob, address, phone, email) VALUE
    (5,0,'2022-04-21','2022-04-11','Trearast','2001-05-07','MK Kingdom','0943293532','Trearast@gmail.com');

-- CENTER
DROP TABLE IF EXISTS center;
CREATE TABLE center
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)       NOT NULL DEFAULT 0,
    created_on    datetime NULL,
    last_modified datetime NULL,
    name          VARCHAR(255) NOT NULL,
    code          VARCHAR(255) NULL,
    dob           date         NOT NULL,
    address       VARCHAR(255) NOT NULL,
    CONSTRAINT pk_center PRIMARY KEY (id)
);

-- CENTER FRESHER
DROP TABLE IF EXISTS center_fresher;
CREATE TABLE center_fresher
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1) NOT NULL DEFAULT 0,
    created_on    datetime NULL,
    last_modified datetime NULL,
    center_id     BIGINT NULL,
    fresher_id    BIGINT NULL,
    start_date    date NULL,
    end_date      date NULL,
    CONSTRAINT pk_center_fresher PRIMARY KEY (id)
);

ALTER TABLE center_fresher
    ADD CONSTRAINT FK_CENTER_FRESHER_ON_CENTER FOREIGN KEY (center_id) REFERENCES center (id);

ALTER TABLE center_fresher
    ADD CONSTRAINT FK_CENTER_FRESHER_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);

-- programming_language
DROP TABLE IF EXISTS programming_language;
CREATE TABLE programming_language
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    name          VARCHAR(255)          NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_programming_language PRIMARY KEY (id)
);

insert into  programming_language(id, created_on, last_modified, name, description) value
(1,'2022-04-21','2022-04-11','Java','OOP'),
    (2,'2022-04-21','2022-04-11','C++','OOP'),
    (3,'2022-04-21','2022-04-11','Python','OOP'),
    (4,'2022-04-21','2022-04-11','C#','OOP'),
    (5,'2022-04-21','2022-04-11','Kotlin','OOP'),
    (6,'2022-04-21','2022-04-11','HTML','Web'),
    (7,'2022-04-21','2022-04-11','Javascript','Web'),
    (8,'2022-04-21','2022-04-11','CSS','Web'),
    (9,'2022-04-21','2022-04-11','SQL','Data');


-- fresher_language
DROP TABLE IF EXISTS fresher_language;
CREATE TABLE fresher_language
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    language_id   BIGINT                NOT NULL,
    fresher_id    BIGINT                NOT NULL,
    CONSTRAINT pk_fresher_language PRIMARY KEY (id)
);
insert into fresher_language(created_on, last_modified, language_id, fresher_id) VALUE
('2022-04-21','2022-04-11',1,1),
    ('2022-04-21','2022-04-11',2,1),
    ('2022-04-21','2022-04-11',3,1),
    ('2022-04-21','2022-04-11',1,2),
    ('2022-04-21','2022-04-11',2,2),
    ('2022-04-21','2022-04-11',3,2),
    ('2022-04-21','2022-04-11',9,2),
    ('2022-04-21','2022-04-11',1,3),
    ('2022-04-21','2022-04-11',1,4),
    ('2022-04-21','2022-04-11',2,4),
    ('2022-04-21','2022-04-11',3,4),
    ('2022-04-21','2022-04-11',4,4),
    ('2022-04-21','2022-04-11',5,4),
    ('2022-04-21','2022-04-11',6,4),
    ('2022-04-21','2022-04-11',7,4),
    ('2022-04-21','2022-04-11',8,4),
    ('2022-04-21','2022-04-11',9,4),
    ('2022-04-21','2022-04-11',3,5),
    ('2022-04-21','2022-04-11',2,5),
    ('2022-04-21','2022-04-11',1,5);

ALTER TABLE fresher_language
    ADD CONSTRAINT FK_FRESHER_LANGUAGE_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);

ALTER TABLE fresher_language
    ADD CONSTRAINT FK_FRESHER_LANGUAGE_ON_LANGUAGE FOREIGN KEY (language_id) REFERENCES programming_language (id);

-- assignment
DROP TABLE IF EXISTS assignment;
CREATE TABLE assignment
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    percentage    INT                   NOT NULL,
    `description` VARCHAR(255)          NULL,
    CONSTRAINT pk_assignment PRIMARY KEY (id)
);
insert into assignment ( created_on, last_modified, percentage, description)
VALUE ('2022-11-21','2022-05-11',30,'	Progress test 1');
insert into assignment (created_on, last_modified, percentage, description)
    VALUE ('2022-11-21','2022-05-11',30,'	Progress test 2');
insert into assignment (created_on, last_modified, percentage, description)
    VALUE ('2022-11-21','2022-05-11',40,'	Progress test 3');
-- assignment_score
DROP TABLE IF EXISTS assignment_score;
CREATE TABLE assignment_score
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    deleted       BIT(1)                NOT NULL DEFAULT 0,
    created_on    datetime              NULL,
    last_modified datetime              NULL,
    fresher_id    BIGINT                NOT NULL,
    assignment_id BIGINT                NOT NULL,
    score         INT                   NOT NULL,
    CONSTRAINT pk_assignment_score PRIMARY KEY (id)
);


ALTER TABLE assignment_score
    ADD CONSTRAINT FK_ASSIGNMENT_SCORE_ON_ASSIGNMENT FOREIGN KEY (assignment_id) REFERENCES assignment (id);

ALTER TABLE assignment_score
    ADD CONSTRAINT FK_ASSIGNMENT_SCORE_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);

insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',1,1,10);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',1,2,10);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',1,3,10);

insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',2,1,10);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',2,2,8);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',2,3,10);

insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',3,1,5);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',3,2,5);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',3,3,5);

insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',4,1,7);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',4,2,7);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',4,3,7);

insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',5,1,1);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',5,2,1);
insert into assignment_score( created_on, last_modified, fresher_id, assignment_id, score)
    VALUE ('2022-11-21','2022-05-11',5,3,1);

# DROP TABLE IF EXISTS assignment;
# CREATE TABLE assignment
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     percentage    INT                   NOT NULL,
#     name          VARCHAR(255)          NOT NULL,
#     `description` VARCHAR(255)          NOT NULL,
#     CONSTRAINT pk_assignment PRIMARY KEY (id)
# );
#
# DROP TABLE IF EXISTS center;
# CREATE TABLE center
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     name          VARCHAR(255)          NOT NULL,
#     code          VARCHAR(255)          NULL,
#     dob           date                  NOT NULL,
#     address       VARCHAR(255)          NOT NULL,
#     CONSTRAINT pk_center PRIMARY KEY (id)
# );
# DROP TABLE IF EXISTS fresher;
# CREATE TABLE fresher
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     name          VARCHAR(255)          NOT NULL,
#     dob           date                  NOT NULL,
#     address       VARCHAR(255)          NOT NULL,
#     phone         VARCHAR(255)          NOT NULL,
#     email         VARCHAR(255)          NOT NULL,
#     CONSTRAINT pk_fresher PRIMARY KEY (id)
# );
# DROP TABLE IF EXISTS programming_language;
# CREATE TABLE programing_language
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     name          VARCHAR(255)          NOT NULL,
#     `description` VARCHAR(255)          NOT NULL,
#     CONSTRAINT pk_programing_language PRIMARY KEY (id)
# );
#
# DROP TABLE IF EXISTS assignment_score;
# CREATE TABLE assignment_score
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     assignment_id BIGINT                NOT NULL,
#     fresher_id    BIGINT                NOT NULL,
#     score         INT                   NOT NULL,
#     CONSTRAINT pk_assignment_score PRIMARY KEY (id)
# );
#
# ALTER TABLE assignment_score
#     ADD CONSTRAINT FK_ASSIGNMENT_SCORE_ON_ASSIGNMENT FOREIGN KEY (assignment_id) REFERENCES assignment (id);
#
# ALTER TABLE assignment_score
#     ADD CONSTRAINT FK_ASSIGNMENT_SCORE_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);
#
# DROP TABLE IF EXISTS center_fresher;
# CREATE TABLE center_fresher
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     center_id     BIGINT                NULL,
#     fresher_id    BIGINT                NULL,
#     start_date    date                  NULL,
#     end_date      date                  NULL,
#     CONSTRAINT pk_center_fresher PRIMARY KEY (id)
# );
#
# ALTER TABLE center_fresher
#     ADD CONSTRAINT FK_CENTER_FRESHER_ON_CENTER FOREIGN KEY (center_id) REFERENCES center (id);
#
# ALTER TABLE center_fresher
#     ADD CONSTRAINT FK_CENTER_FRESHER_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);
#
# DROP TABLE IF EXISTS fresher_language;
# CREATE TABLE fresher_language
# (
#     id            BIGINT AUTO_INCREMENT NOT NULL,
#     deleted       BIT(1)                NULL,
#     created_on    datetime              NULL,
#     last_modified datetime              NULL,
#     fresher_id    BIGINT                NOT NULL,
#     language_id   BIGINT                NOT NULL,
#     address       VARCHAR(255)          NOT NULL,
#     phone         VARCHAR(255)          NOT NULL,
#     email         VARCHAR(255)          NOT NULL,
#     center_id     BIGINT                NULL,
#     CONSTRAINT pk_fresher_language PRIMARY KEY (id)
# );
#
# ALTER TABLE fresher_language
#     ADD CONSTRAINT FK_FRESHER_LANGUAGE_ON_CENTER FOREIGN KEY (center_id) REFERENCES programing_language (id);
#
# ALTER TABLE fresher_language
#     ADD CONSTRAINT FK_FRESHER_LANGUAGE_ON_FRESHER FOREIGN KEY (fresher_id) REFERENCES fresher (id);