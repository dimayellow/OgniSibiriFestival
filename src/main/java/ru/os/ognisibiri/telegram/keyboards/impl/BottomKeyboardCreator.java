package ru.os.ognisibiri.telegram.keyboards.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.telegram.keyboards.CreatesKeyboard;

import java.util.List;

@Component
public class BottomKeyboardCreator implements CreatesKeyboard {

        @Override
        public ReplyKeyboard createStandartMenuByComandLists(List<BotCommand> commandsList, BotCommand backComand) {
                return null;
        }

//      Пример
//        public ReplyKeyboardMarkup getTestKeyboardMaker() {
//
//                KeyboardRow row1 = new KeyboardRow();
//                row1.add(KeyboardButtonName.REGISTER.getName());
//
//                List<KeyboardRow> keyboard = new ArrayList<>();
//                keyboard.add(row1);
//
//                final ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
//                replyKeyboardMarkup.setKeyboard(keyboard);
//                replyKeyboardMarkup.setSelective(true);
//                replyKeyboardMarkup.setResizeKeyboard(true);
//                replyKeyboardMarkup.setOneTimeKeyboard(false);
//
//                return replyKeyboardMarkup;
//
//        }

}
