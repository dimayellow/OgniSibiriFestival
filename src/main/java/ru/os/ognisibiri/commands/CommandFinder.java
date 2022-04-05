package ru.os.ognisibiri.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.sessions.HasSessionAction;

public class CommandFinder {

    private CommandFinder() {
    }

    @Autowired
    @Getter
    private static MapFinder<HasSessionAction> hasSessionActionMapFinder;

    @Autowired
    @Getter
    private static MapFinder<HaveMenu> haveMenuMapFinder;


}
