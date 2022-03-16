package ru.os.ognisibiri.telegram.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.enums.BotMessageEnum;
import ru.os.ognisibiri.telegram.keyboards.InlineKeyboardCreator;
import ru.os.ognisibiri.telegram.keyboards.KeyboardCreator;

@Component
public class MenuMaker {

    @Autowired
    InlineKeyboardCreator inlineKeyboardCreator;
    @Autowired
    KeyboardCreator keyboardCreator;

    public SendMessage getMainMenu(String chatId) {
        return createMessageByChatIdTextKeyboard(chatId,
                                                BotMessageEnum.MAIN_MENU.getMessage(),
                                                inlineKeyboardCreator.getMainMenu());
    }

    public SendMessage getLkMenu(String chatId, UserInBase user) {

        String text = String.format("%s\nВаши данные: \n%s",
                BotMessageEnum.LK.getMessage(),
                getNameLastNameUserNameByUserInBase(user)
        );

        return createMessageByChatIdTextKeyboard(chatId, text, inlineKeyboardCreator.getLKMenu());
    }

    public SendMessage getChangePropsMenu(String chatId, UserInBase user) {

        String text = getNameLastNameUserNameByUserInBase(user);

        return createMessageByChatIdTextKeyboard(chatId, text, inlineKeyboardCreator.getChangePropsMenu());
    }

    //

    public SendMessage getCancelSession(String chatId, String text) {
        return createMessageByChatIdTextKeyboard(chatId, text, inlineKeyboardCreator.getCancelMenu());
    }

    //

    private String getNameLastNameUserNameByUserInBase(UserInBase user) {
        String text = String.format("Имя: %s \nФамилия: %s \nНикнейм: %s",
                user.getFirstName(),
                user.getLastName(),
                user.getUserName()
        );
        return text;
    }

    private SendMessage createMessageByChatIdTextKeyboard(String chatId, String text, InlineKeyboardMarkup keyboard) {

        SendMessage sendMessage = new SendMessage(chatId, text);
        sendMessage.enableMarkdown(true);
        sendMessage.setReplyMarkup(keyboard);

        return sendMessage;
    }

}
