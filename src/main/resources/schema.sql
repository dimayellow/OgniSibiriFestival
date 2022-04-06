DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id varchar(255) not null primary key,
    chat_id varchar (255) not null,
    user_id BIGINT not null,
    first_name varchar (255),
    last_name varchar (255),
    user_name varchar (255),
    language_code varchar (5),
    is_admin boolean
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
DROP TABLE IF EXISTS user_session;
CREATE TABLE user_session
(
    id varchar(255) not null primary key,
    user_id varchar(255) not null,
    foreign key (user_id) REFERENCES user (id),
    session_id varchar(255)
    -- foreign key (currentFestival) REFERENCES festival (id),
);

-- COMMANDS!

DROP TABLE IF EXISTS bot_commands;
CREATE TABLE bot_commands
(
    command_id varchar(255) not null primary key,
    display_text varchar(255) not null,
    action_name varchar(255),
    menu_maker_name varchar(255) not null,
    back_command_id varchar(255),
    FOREIGN KEY (back_command_id) REFERENCES bot_commands(command_id)
);

DROP TABLE IF EXISTS available_commands;
CREATE TABLE available_commands
(
    owner_id varchar(255) not null,
    available_command_id varchar(255) not null,
    CONSTRAINT PK_available_commands PRIMARY KEY (owner_id, available_command_id),
    FOREIGN KEY (owner_id)  REFERENCES bot_commands (command_id),
    FOREIGN KEY (available_command_id)  REFERENCES bot_commands (command_id)
);