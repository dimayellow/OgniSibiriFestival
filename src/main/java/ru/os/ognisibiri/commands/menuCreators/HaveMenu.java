package ru.os.ognisibiri.commands.menuCreators;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.commands.sessions.HasSessionActionCreationHelper;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;

public interface HaveMenu {

    public SendMessage createMenu(BotCommand botCommand, String chatId);
    public String getId();

    public default MenuCreationHelper getMenuCreationHelper(BotCommand botCommand, String chatId) {

        MenuCreationHelper helper = MenuCreationHelper.builder()
                .thisCommand(botCommand)
                .chatId(chatId)
                .build();

        return helper;
    };


}
