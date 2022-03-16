package ru.os.ognisibiri.telegram.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.telegram.utils.MenuMaker;

@Component
public class MessageHandler {

    @Autowired
    MenuMaker menuMaker;

    // Services
    @Autowired
    UserService userService;


    public BotApiMethod<?> answerMessage(Message message) {

        String chatId = message.getChatId().toString();
        String command = message.getText();
        User user = message.getFrom();

        if (command.equals("/start")) return checkRegistrationAndGetMainMenu(chatId, user);

        throw new IllegalArgumentException();

    }

    public SendMessage checkRegistrationAndGetMainMenu(String chatId, User user) {

        UserInBase userInBase = userService.getByChatId(chatId);

        if (userInBase == null) {
            userInBase = new UserInBase(user, chatId);
            userService.save(userInBase);
        }

        return menuMaker.getMainMenu(chatId);

    }

}
