package ru.os.ognisibiri.commands.menuCreators;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface HaveMenu {

    public SendMessage createMenu(MenuCreationHelper helper);
    public String getId();

}
