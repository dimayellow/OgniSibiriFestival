package ru.os.ognisibiri.commands.menuCreators.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.menuCreators.MenuCreationHelper;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.repo.UserRepo;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.telegram.keyboards.CreatesKeyboard;

import java.util.Objects;

@Component
public class StandartInlineMenuWithUserInfo implements HaveMenu {

    String id = "standartWithUserInfo";

    @Autowired
    @Qualifier(value = "inlineKeyboard")
    CreatesKeyboard inlineKeyboardCreator;

    @Autowired
    UserService service;

    @Override
    public SendMessage createMenu(MenuCreationHelper helper) {

        String displayText = String.format(helper.getBackComand().getMessage(),
                helper.getUserInBase().getLastName(),
                helper.getUserInBase().getLastName(),
                helper.getUserInBase().getUserName()
                );

        SendMessage sendMessage = new SendMessage(helper.getChatId(), displayText);
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
    public MenuCreationHelper getMenuCreationHelper(BotCommand botCommand, String chatId) {

        UserInBase userInBase = service.getByChatId(chatId);

        MenuCreationHelper helper = MenuCreationHelper.builder().commands(botCommand.getAvailableCommands())
                .backComand(botCommand.getBackCommand())
                .displayText(botCommand.getMessage())
                .chatId(chatId)
                .userInBase(userInBase)
                .build();

        return helper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandartInlineMenuWithUserInfo that = (StandartInlineMenuWithUserInfo) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
