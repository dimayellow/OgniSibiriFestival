package ru.os.ognisibiri.telegram.keyboards;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import ru.os.ognisibiri.data.entity.BotCommand;

import java.util.List;

public interface CreatesKeyboard {

    public ReplyKeyboard createStandartMenuByCommand(BotCommand botComand);

}
