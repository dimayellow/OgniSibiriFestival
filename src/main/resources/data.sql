INSERT INTO bot_commands (command_id, display_text, menu_maker_name, action_name, back_command_id)
VALUES ('/start', 'Главное меню', 'standart', null, null),
       ('/lk', 'Личный кабинет', 'standart', null, '/start'),
       ('/changeProps', 'Изменить реквизиты', 'standart', null, '/lk'),
       ('/changeLKFirstName', 'Изменить имя', 'standart', 'changeLkFirstName', '/changeProps'),
       ('/changeLKLastName', 'Изменить фамилию', 'standart', 'changeLkLastName', '/changeProps'),
       ('/changeLKUserName', 'Изменить юзернейм', 'standart', 'changeLkUserName', '/changeProps');
--        (6, '/lk', 'Назад', 'standart');

INSERT INTO available_commands (owner_id, available_command_id)
VALUES ('/start', '/lk'),
       ('/lk', '/changeProps'),
       ('/changeProps', '/changeLKFirstName'),
       ('/changeProps', '/changeLKLastName'),
       ('/changeProps', '/changeLKUserName');



//-----------------------------------------------