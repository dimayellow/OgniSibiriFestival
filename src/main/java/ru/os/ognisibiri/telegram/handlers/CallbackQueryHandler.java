package ru.os.ognisibiri.telegram.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;
import ru.os.ognisibiri.enums.BotButtonEnum;
import ru.os.ognisibiri.enums.SessionStatusEnum;
import ru.os.ognisibiri.telegram.utils.MenuMaker;
import ru.os.ognisibiri.telegram.utils.UserInputPreparationManager;

import java.io.IOException;

@Component
public class CallbackQueryHandler {

    @Autowired
    MenuMaker menuMaker;
    @Autowired
    UserInputPreparationManager uIPManager;
    @Autowired
    UserService userService;
    @Autowired
    UserSessionService userSessionService;


    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {

        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();

        UserInBase userInBase = userService.getByChatId(chatId);

        if (data.equals(BotButtonEnum.LK.getCommand())) {
            return menuMaker.getLkMenu(chatId, userInBase);
        } else if (data.equals(BotButtonEnum.CHANGE_PROPS.getCommand())) {
            return menuMaker.getChangePropsMenu(chatId, userInBase);
        } else if (data.substring(0, 9).equals("/changeLK")) {
            return uIPManager.changePorpInLk(data, chatId);
        } else if (data.equals(BotButtonEnum.CANCEL_SESSION.getCommand())) {
            return cancelSession(chatId, userInBase);
        }

        return null;
    }

    private SendMessage cancelSession(String chatId, UserInBase user) {

        int statusId = user.getSession().getStatusId();
        SendMessage reply = null;

        if (statusId == SessionStatusEnum.CHANGE_FIRSTNAME.getId() ||
            statusId == SessionStatusEnum.CHANGE_LASTNAME.getId() ||
            statusId == SessionStatusEnum.CHANGE_USERNAME.getId()) {
            reply = menuMaker.getChangePropsMenu(chatId, user);
        }

        userSessionService.clearSession(user);

        return reply;
    }




}
