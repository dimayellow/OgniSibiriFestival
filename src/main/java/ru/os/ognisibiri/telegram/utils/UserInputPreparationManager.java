package ru.os.ognisibiri.telegram.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;
import ru.os.ognisibiri.enums.BotButtonEnum;
import ru.os.ognisibiri.enums.BotMessageEnum;
import ru.os.ognisibiri.enums.SessionStatusEnum;

@Component
public class UserInputPreparationManager {

    @Autowired
    UserSessionService sessionService;
    @Autowired
    UserService userService;
    @Autowired
    MenuMaker menuMaker;


    public SendMessage changePorpInLk(String data, String chatId) {

        UserInBase userInBase = userService.getByChatId(chatId);
        SessionStatusEnum newSessionStatus = null;
        String text = "";

        if (data.equals(BotButtonEnum.CHANGE_FIRSTNAME.getCommand())) {
            newSessionStatus = SessionStatusEnum.CHANGE_FIRSTNAME;
            text = BotMessageEnum.ENTER_FIRSTNAME.getMessage();
        } else if (data.equals(BotButtonEnum.CHANGE_LASTNAME.getCommand())) {
            newSessionStatus = SessionStatusEnum.CHANGE_LASTNAME;
            text = BotMessageEnum.ENTER_LASTNAME.getMessage();
        } else if (data.equals(BotButtonEnum.CHANGE_USERNAME.getCommand())) {
            newSessionStatus = SessionStatusEnum.CHANGE_USERNAME;
            text = BotMessageEnum.ENTER_USERNAME.getMessage();
        }

        sessionService.setSessionStatus(userInBase, newSessionStatus);

        return menuMaker.getCancelSession(chatId, text);

    }

}
