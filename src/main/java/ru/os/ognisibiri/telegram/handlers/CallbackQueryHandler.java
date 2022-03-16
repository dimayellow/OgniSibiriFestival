package ru.os.ognisibiri.telegram.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.enums.BotButtonEnum;
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


    public BotApiMethod<?> processCallbackQuery(CallbackQuery buttonQuery) throws IOException {

        final String chatId = buttonQuery.getMessage().getChatId().toString();

        String data = buttonQuery.getData();
        User user = buttonQuery.getMessage().getFrom();

        UserInBase userInBase = userService.getByChatId(chatId);

        if (data.equals(BotButtonEnum.LK.getCommand())) {
            return menuMaker.getLkMenu(chatId, userInBase);
        } else if (data.equals(BotButtonEnum.CHANGE_PROPS.getCommand())) {
            return menuMaker.getChangePropsMenu(chatId, userInBase);
        } else if (data.substring(0, 8).equals("/changeLK")) {
            return uIPManager.changePorpInLk(data, chatId, user);
        }

        return null;
    }




}
