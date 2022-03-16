package ru.os.ognisibiri.telegram.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import ru.os.ognisibiri.enums.KeyboardButtonName;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeyboardCreator {

    public ReplyKeyboardMarkup getTestKeyboardMaker() {

        KeyboardRow row1 = new KeyboardRow();
        row1.add(KeyboardButtonName.REGISTER.getName());

        List<KeyboardRow> keyboard = new ArrayList<>();
        keyboard.add(row1);

        final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(keyboard);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        return replyKeyboardMarkup;

    }


}
