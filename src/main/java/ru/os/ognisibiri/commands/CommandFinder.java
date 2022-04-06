package ru.os.ognisibiri.commands;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.sessions.HasSessionAction;

@Component
public class CommandFinder {

    public CommandFinder() {
    }

    @Autowired
    @Getter
    private MapFinder<HasSessionAction> hasSessionActionMapFinder;

    @Autowired
    @Getter
    private MapFinder<HaveMenu> haveMenuMapFinder;


}
