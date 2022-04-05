package ru.os.ognisibiri.commands.sessions;

import org.telegram.telegrambots.meta.api.objects.User;

public interface HasSessionAction {

    public void changeAction(HasSessionActionCreationHelper hasSessionActionCreationHelper);

    public default HasSessionActionCreationHelper getHasSessionActionCreationHelper(String chatId, User user, String text) {

        HasSessionActionCreationHelper hasSessionActionCreationHelper = HasSessionActionCreationHelper.builder()
                                        .text(text)
                                        .userInTelegram(user)
                                        .chatId(chatId)
                                        .build();

        return hasSessionActionCreationHelper;
    };

    public String getMessage();

    public String getId();
}
