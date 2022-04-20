package ru.os.ognisibiri.telegram.keyboards.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.telegram.keyboards.CreatesKeyboard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InlineKeyboardCreatorTest {

    @Qualifier("inlineKeyboard")
    @Autowired
    CreatesKeyboard createsKeyboard;

    @Test
    void createStandartMenuByComandListsTest() {

        // Arrange

        List<BotCommand> availableBotCommandList = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        Map<String, String> testDateMap = new HashMap<>();
        testDateMap.put("/fnc", "first");
        testDateMap.put("/snc", "second");

        testDateMap.forEach((comebackText, displayText) -> {

            // botCommand
            BotCommand nestedCommand = new BotCommand();
            nestedCommand.setCommandId(comebackText);
            nestedCommand.setDisplayText(displayText);

            availableBotCommandList.add(nestedCommand);

            //reply
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(displayText);
            button.setCallbackData(comebackText);

            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(button);

            rowList.add(keyboardButtonsRow);

        });

        BotCommand testCommand = new BotCommand();
        testCommand.setAvailableCommands(availableBotCommandList);

        //reply

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        // Act
        ReplyKeyboard replyMessage = createsKeyboard.createStandartMenuByCommand(testCommand);

        // Assert
        Assertions.assertEquals(replyMessage, inlineKeyboardMarkup);

    }

    @Test
    void createStandartMenuByComandListsTestWithBackComand() {

        // Arrange

        List<BotCommand> availableBotCommandList = new ArrayList<>();
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        Map<String, String> testDateMap = new HashMap<>();
        testDateMap.put("/fnc", "first");
        testDateMap.put("/snc", "second");

        testDateMap.forEach((comebackText, displayText) -> {

            // BotCommand
            BotCommand nestedCommand = new BotCommand();
            nestedCommand.setCommandId(comebackText);
            nestedCommand.setDisplayText(displayText);

            availableBotCommandList.add(nestedCommand);

            // reply
            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(displayText);
            button.setCallbackData(comebackText);

            List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
            keyboardButtonsRow.add(button);

            rowList.add(keyboardButtonsRow);

        });

            // Bot command. Add back command

        BotCommand nestedCommand = new BotCommand();
        nestedCommand.setCommandId("/back");
        nestedCommand.setDisplayText("back text");

        availableBotCommandList.add(nestedCommand);

            // reply

        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText("back text");
        button.setCallbackData("/back");

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);

        rowList.add(keyboardButtonsRow);

            // BotCommand

        BotCommand testCommand = new BotCommand();
        testCommand.setAvailableCommands(availableBotCommandList);

            // reply

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);

        // Act
        ReplyKeyboard replyMessage = createsKeyboard.createStandartMenuByCommand(testCommand);

        // Assert
        Assertions.assertEquals(replyMessage, inlineKeyboardMarkup);

    }


}