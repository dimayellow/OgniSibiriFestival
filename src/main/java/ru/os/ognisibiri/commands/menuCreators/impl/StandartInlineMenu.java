package ru.os.ognisibiri.commands.menuCreators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.menuCreators.MenuCreationHelper;
import ru.os.ognisibiri.telegram.keyboards.CreatesKeyboard;

import java.util.Objects;

@Component
public class StandartInlineMenu implements HaveMenu {

    String id = "standart";

    @Autowired
    @Qualifier(value = "inlineKeyboard")
    CreatesKeyboard inlineKeyboardCreator;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandartInlineMenu that = (StandartInlineMenu) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
