package ru.os.ognisibiri.commands.menuCreators;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.os.ognisibiri.data.entity.BotCommand;

import java.util.List;
import java.util.Objects;

@Builder
@Getter
public class MenuCreationHelper {

    private String chatId;
    private String displayText;
    private BotCommand backComand;
    private List<BotCommand> commands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MenuCreationHelper helper = (MenuCreationHelper) o;
        return chatId.equals(helper.chatId) && Objects.equals(displayText, helper.displayText) && Objects.equals(backComand, helper.backComand) && Objects.equals(commands, helper.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, displayText, backComand, commands);
    }
}