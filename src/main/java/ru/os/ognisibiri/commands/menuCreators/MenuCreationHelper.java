package ru.os.ognisibiri.commands.menuCreators;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.os.ognisibiri.data.entity.BotCommand;

import java.util.List;

@Builder
@Getter
@NoArgsConstructor
public class MenuCreationHelper {

    private String chatId;
    private String displayText;
    private BotCommand backComand;
    private List<BotCommand> commands;



}