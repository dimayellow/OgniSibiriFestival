package ru.os.ognisibiri.commands.menuCreators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.menuCreators.MenuCreationHelper;
import ru.os.ognisibiri.telegram.keyboards.InlineKeyboardCreator;

public class StandartInlineMenu implements HaveMenu {

    String id = "standart";

    @Autowired
    InlineKeyboardCreator inlineKeyboardCreator;

    @Override
    public SendMessage createMenu(MenuCreationHelper helper) {


        SendMessage sendMessage = new SendMessage(helper.getChatId(), helper.getDisplayText());
        sendMessage.enableMarkdown(true);

        ReplyKeyboard keyboard = inlineKeyboardCreator.createStandartMenuByComandLists(helper.getCommands(), helper.getBackComand());

        sendMessage.setReplyMarkup(keyboard);

        return sendMessage;
    }

    @Override
    public String getId() {
        return id;
    }
}
