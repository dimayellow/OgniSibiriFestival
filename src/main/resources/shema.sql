DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id varchar(36) not null primary key,
    chat_id varchar (256) not null,
    user_id BIGINT not null,
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
    userId BIGINT not null,
    foreign key (userId) REFERENCES user (id),
    sessionId varchar(255)
    -- foreign key (currentFestival) REFERENCES festival (id),
);

-- COMMANDS!

DROP TABLE IF EXISTS botCommands;
CREATE TABLE botCommands
(
    commandId varchar(255) not null primary key,
    displayText varchar(255) not null,
    actionName varchar(255),
    menuMakerName varchar(255) not null,
    backCommandId varchar(255),
    FOREIGN KEY (backCommandId) REFERENCES botCommands(commandId)
);

DROP TABLE IF EXISTS availableCommands;
CREATE TABLE availableCommands
(
    ownerId varchar(255) not null,
    availableCommandId varchar(255) not null,
    CONSTRAINT PK_availableCommands PRIMARY KEY (ownerId, availableCommandId),
    FOREIGN KEY (ownerId)  REFERENCES botCommands (commandId),
    FOREIGN KEY (availableCommandId)  REFERENCES botCommands (commandId)
);

INSERT INTO botCommands (commandId, displayText, menuMakerName, actionName, backCommandId)
VALUES ('/start', 'Главное меню', 'standart', null, null),
       ('/lk', 'Личный кабинет', 'standart', null, '/start'),
       ('/changeProps', 'Изменить реквизиты', 'standart', null, '/lk'),
       ('/changeLKFirstName', 'Изменить имя', 'standart', 'changeLkFirstName', '/changeProps'),
       ('/changeLKLastName', 'Изменить фамилию', 'standart', 'changeLkLastName', '/changeProps'),
       ('/changeLKUserName', 'Изменить юзернейм', 'standart', 'changeLkUserName', '/changeProps'),
--        (6, '/lk', 'Назад', 'standart');

INSERT INTO availableCommands (ownerId, availableCommandId)
VALUES ('/start', '/lk'),
       ('/lk', '/changeProps'),
       ('/changeProps', '/changeLKFirstName'),
       ('/changeProps', '/changeLKLastName'),
       ('/changeProps', '/changeLKUserName');



//-----------------------------------------------