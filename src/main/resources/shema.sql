DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id varchar(36) not null primary key,
    chat_id varchar (256) not null,
    user_id BIGINT (36) not null,
    firstName varchar (256),
    lastName varchar (256),
    userName varchar (256),
    languageCode varchar (5),
    isAdmin boolean
);
-- DROP TABLE IF EXISTS festival;
-- CREATE TABLE festival
-- (
--     id varchar(36) not null primary key,
--     name varchar (256) not null,
--     startDate date,
--     endDate date
-- )
--;
DROP TABLE IF EXISTS userSession;
CREATE TABLE userSession
(
    id varchar(36) not null primary key,
    foreign key (userId) REFERENCES user (id),
    status int
    -- foreign key (currentFestival) REFERENCES festival (id),
);
