INSERT INTO bot_commands (command_id, display_text, menu_maker_name, action_name, back_command_id, message)
VALUES ('/start', 'Главное меню', 'standart', null, null, null),
       ('/lk', 'Личный кабинет', 'standartWithUserInfo', null, '/start', 'Добро пожаловать%n%s %s %s'),
       ('/changeProps', 'Изменить реквизиты', 'standartWithUserInfo', null, '/lk', 'Ваше имя: %s%nФамилия: %s%nUsername: %s'),
       ('/changeLKFirstName', 'Изменить имя', 'standart', 'changeLkFirstName', '/changeProps', null),
       ('/changeLKLastName', 'Изменить фамилию', 'standart', 'changeLkLastName', '/changeProps', null),
       ('/changeLKUserName', 'Изменить юзернейм', 'standart', 'changeLkUserName', '/changeProps', null);
--        (6, '/lk', 'Назад', 'standart');

INSERT INTO available_commands (owner_id, available_command_id)
VALUES ('/start', '/lk'),
       ('/lk', '/changeProps'),
       ('/changeProps', '/changeLKFirstName'),
       ('/changeProps', '/changeLKLastName'),
       ('/changeProps', '/changeLKUserName');

//-----------------------------------------------