package ru.os.ognisibiri.telegram.handlers;

import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.entity.UserSession;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;
import ru.os.ognisibiri.enums.SessionStatusEnum;
import ru.os.ognisibiri.telegram.utils.MenuMaker;

import static java.awt.SystemColor.text;


@Component
public class MessageHandler {

    @Autowired
    MenuMaker menuMaker;

    // Services
    @Autowired
    UserService userService;
    @Autowired
    UserSessionService userSessionService;


    public BotApiMethod<?> answerMessage(Message message) {

        String chatId = message.getChatId().toString();
        String command = message.getText();
        User user = message.getFrom();

        // С /start всегда начинается работа.
        // В дальнейшем действия будут обрабатываться в зависимости от текущего статуса сессии.
        if (command.equals("/start")) return checkRegistrationAndGetMainMenu(chatId, user);

        return processUserMessage(command, userService.getByChatId(chatId), chatId);



//        throw new IllegalArgumentException();
    }

    private SendMessage processUserMessage (String text, UserInBase userInBase, String chatId) {

        if (userInBase.getSession() == null)
            throw new IllegalArgumentException();

        int sessionStatusId = userInBase.getSession().getStatusId();
        SessionStatusEnum statusEnum = SessionStatusEnum.enumById(sessionStatusId);

        SendMessage reply = null;
        text = text.trim();

        if (statusEnum.getStatusName().substring(0, 8).equals("changeLK")) {
            reply = sendMessageAfterLKPropsChanging(text, userInBase, chatId, statusEnum);
        }

        return  reply;

    }

    private SendMessage sendMessageAfterLKPropsChanging(String text, UserInBase userInBase, String chatId, SessionStatusEnum statusEnum) {
        SendMessage reply;
        userService.changeBySessionStatus(userInBase, text, statusEnum);
        userSessionService.clearSession(userInBase);
        reply = menuMaker.getChangePropsMenu(chatId, userInBase);
        return reply;
    }


    private SendMessage checkRegistrationAndGetMainMenu(String chatId, User user) {

        UserInBase userInBase = userService.getByChatId(chatId);

        if (userInBase == null) {
            userInBase = new UserInBase(user, chatId);
            userService.save(userInBase);
        }

        return menuMaker.getMainMenu(chatId);

    }

}
