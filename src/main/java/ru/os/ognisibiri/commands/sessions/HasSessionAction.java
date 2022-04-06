package ru.os.ognisibiri.commands.sessions;

import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;

public interface HasSessionAction {

    public void changeAction(HasSessionActionCreationHelper hasSessionActionCreationHelper);

    public default HasSessionActionCreationHelper getHasSessionActionCreationHelper(String chatId, User user, String text, UserInBase userInBase) {

        HasSessionActionCreationHelper hasSessionActionCreationHelper = HasSessionActionCreationHelper.builder()
                                        .text(text)
                                        .userInTelegram(user)
                                        .chatId(chatId)
                                        .userInBase(userInBase)
                                        .build();

        return hasSessionActionCreationHelper;
    };

    public String getMessage();

    public String getId();
}
