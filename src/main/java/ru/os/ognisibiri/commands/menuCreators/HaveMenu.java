package ru.os.ognisibiri.commands.menuCreators;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.commands.sessions.HasSessionActionCreationHelper;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;

public interface HaveMenu {

    public SendMessage createMenu(MenuCreationHelper helper);
    public String getId();

    public default MenuCreationHelper getMenuCreationHelper(BotCommand botCommand, String chatId) {

        MenuCreationHelper helper = MenuCreationHelper.builder().commands(botCommand.getAvailableCommands())
                .backComand(botCommand.getBackCommand())
                .displayText(botCommand.getDisplayText())
                .chatId(chatId)
                .build();

        return helper;
    };


}
