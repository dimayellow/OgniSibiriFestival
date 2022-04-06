package ru.os.ognisibiri.telegram.keyboards.impl;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.telegram.keyboards.CreatesKeyboard;

import java.util.ArrayList;
import java.util.List;

@Component(value = "inlineKeyboard")
public class InlineKeyboardCreator implements CreatesKeyboard {

    @Override
    public InlineKeyboardMarkup createStandartMenuByComandLists(List<BotCommand> commandsList, BotCommand backComand) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        commandsList.forEach(command -> addInlineKeyboardMarkupByRowListInArray(command, rowList));

        if (backComand != null)
            addBackCommandInArray(backComand, rowList);

        return getInlineKeyboardMarkupByRowList(rowList);
    }

    // system

    private void addInlineKeyboardMarkupByRowListInArray(BotCommand command,
                                                        List<List<InlineKeyboardButton>> rowList
    ) {
        List<InlineKeyboardButton> keyboardButtonsRow = getOneLineButtonByNuttonEnum(command);
        rowList.add(keyboardButtonsRow);
    }

    private List<InlineKeyboardButton> getOneLineButtonByNuttonEnum(BotCommand command) {
        return getOneLineButton(command.getDisplayText(), command.getCommandId());
    }

    private void addBackCommandInArray(BotCommand command,
                                                         List<List<InlineKeyboardButton>> rowList
    ) {
        List<InlineKeyboardButton> keyboardButtonsRow = getOneLineButtonBackComand(command);
        rowList.add(keyboardButtonsRow);
    }

    private List<InlineKeyboardButton> getOneLineButtonBackComand(BotCommand command) {
        return getOneLineButton("Назад", command.getCommandId());
    }

    private List<InlineKeyboardButton> getOneLineButton(String buttonName, String buttonCallBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(buttonCallBackData);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }

    private InlineKeyboardMarkup getInlineKeyboardMarkupByRowList(List<List<InlineKeyboardButton>> rowList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }
}
