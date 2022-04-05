package ru.os.ognisibiri.telegram.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.commands.CommandFinder;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.menuCreators.MenuCreationHelper;
import ru.os.ognisibiri.commands.sessions.HasSessionAction;
import ru.os.ognisibiri.commands.sessions.HasSessionActionCreationHelper;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.entity.UserSession;
import ru.os.ognisibiri.data.service.BotCommandsService;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;
import ru.os.ognisibiri.exceptions.InvalidCommandNameException;
import ru.os.ognisibiri.exceptions.InvalidValueInSessionException;

import java.util.Optional;

@Component
public class MessageHandler {

    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private BotCommandsService botCommandsService;



    public BotApiMethod<?> processMessage(Message message) throws IllegalArgumentException {

        String chatId   = message.getChatId().toString();
        String text  = message.getText().trim();
        User user       = message.getFrom();

        return checkSessionAndSendAnswer(chatId, user, text);

    }

    public BotApiMethod<?> processMessage(CallbackQuery buttonQuery) throws IllegalArgumentException {

        Message message = buttonQuery.getMessage();

        String chatId = message.getChatId().toString();
        User user     = message.getFrom();
        String text   = buttonQuery.getData().trim();

        return checkSessionAndSendAnswer(chatId, user, text);

    }

    private SendMessage checkSessionAndSendAnswer(String chatId, User user, String text) throws IllegalArgumentException, NullPointerException {

        UserInBase userInBase = checkRegistrationAndRegisterIfNot(chatId, user);

        BotCommand currentCommand = userInBase.getSession().getCommand();

        BotCommand nextStepCommand;

        if (currentCommand.getActionName() != null) {

            String sessionActionName = currentCommand.getActionName();
            HasSessionAction sessionAction = CommandFinder.getHasSessionActionMapFinder().getByName(sessionActionName);

            // Нельзя выбрать комманду из прошлого раздела
            if (text.startsWith("/")) throw new InvalidValueInSessionException(sessionAction.getMessage());

            HasSessionActionCreationHelper helper = sessionAction.getHasSessionActionCreationHelper(chatId, user, text);
            sessionAction.changeAction(helper);

            nextStepCommand = currentCommand.getBackCommand();

        } else {

            HasSessionAction sessionAction = CommandFinder.getHasSessionActionMapFinder().getByName(text);
//            nextStepCommand = ;

        }

//        Optional<Sessional> sessionalOpt = isSessionOpen(userInBase);
//        if (sessionalOpt.isPresent()) {
//            // Обработка сессии.
//            HasSessionAction session = sessionalOpt.get();
//            // 1. Проверка на "/"
//            if (text.startsWith("/")) throw new InvalidValueInSessionException(session.getMessage());
//
//            HasSessionActionCreationHelper data = session.getHasSessionActionCreationHelper(chatId, user, text);
//            session.changeAction(data);

//            int backCommandId = session.getReturnCommand();
//
//            return createReplyByChatIdAndBackCommandId(chatId, backCommandId);

//        } else {
            // 1. Поиск по команде создателя меню
//            HaveMenu menuCreator = CommandFinder.getHaveMenuMapFinder().getByName(text);
//             BotCommands =
//            MenuCreationHelper helper = MenuCreationHelper.builder()
//                    .chatId(chatId)
//                    .commands(menuCreator.getAvailableCommands())
//                    .displayText(menuCreator.getDisplayText())
//                    .build();
            // пробросить IllegalArgumentException в случе если не нашли
//        }

        // Если требуется создать сессию - создать.
        // Создать меню

        return null;
    }

    private SendMessage createReplyByChatIdAndBackCommandId(String chatId, String backCommandId) {
        Optional<BotCommand> opt = botCommandsService.getByName(backCommandId);
        if (!opt.isPresent()) throw new NullPointerException();

        BotCommand backCommand = opt.get();

        MenuCreationHelper helper = MenuCreationHelper.builder()
                .chatId(chatId)
                .commands(backCommand.getAvailableCommands())
                .displayText(backCommand.getDisplayText())
                .build();

        HaveMenu backMenu = CommandFinder.getHaveMenuMapFinder().getByName(backCommand.getMenuMakerName());

        return backMenu.createMenu(helper);
    }

    private UserInBase checkRegistrationAndRegisterIfNot(String chatId,User user) {
        UserInBase userInBase = userService.getByChatId(chatId);

        if (userInBase == null) {
            userInBase = registerUser(chatId, user);
        }
        return userInBase;
    }

    private UserInBase registerUser(String chatId, User user) {

        UserInBase userInBase = new UserInBase(user, chatId);

        // Все новые пользователи начинают со старта
        BotCommand StartCommand = findCommandByName("/start");
        UserSession session = new UserSession();
        session.setCommand(StartCommand);

        userInBase.setSession(session);
        userService.save(userInBase);

        return userInBase;
    }

    private BotCommand findCommandByName(String commandName) {
        Optional<BotCommand> optionalCommand = botCommandsService.getByName(commandName);
        if (!optionalCommand.isPresent()) throw new InvalidCommandNameException();
        return optionalCommand.get();
    }
}
