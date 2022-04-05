package ru.os.ognisibiri.telegram.keyboards;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.enums.BotButtonEnum;

import java.util.ArrayList;
import java.util.List;

@Component
public class InlineKeyboardCreator {

    public InlineKeyboardMarkup createStandartMenuByComandLists(List<BotCommand> commandsList, BotCommand backComand) {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        commandsList.forEach(command -> addInlineKeyboardMarkupByRowListInArray(command, rowList));

        addInlineKeyboardMarkupByRowListInArray(backComand, rowList);

        return getInlineKeyboardMarkupByRowList(rowList);
    }

    @Deprecated
    public InlineKeyboardMarkup getMainMenu() {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.LK, rowList);
        
        return getInlineKeyboardMarkupByRowList(rowList);
    }

    @Deprecated
    public InlineKeyboardMarkup getLKMenu() {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.CHANGE_PROPS, rowList);

        return getInlineKeyboardMarkupByRowList(rowList);
    }

    @Deprecated
    public InlineKeyboardMarkup getChangePropsMenu() {

        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.CHANGE_FIRSTNAME, rowList);
        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.CHANGE_LASTNAME, rowList);
        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.CHANGE_USERNAME, rowList);

        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.BACK_TO_LK, rowList);

        return getInlineKeyboardMarkupByRowList(rowList);

    }

    //

    public InlineKeyboardMarkup getCancelMenu() {
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();

        addInlineKeyboardMarkupByRowListInArray(BotButtonEnum.CANCEL_SESSION, rowList);

        return getInlineKeyboardMarkupByRowList(rowList);
    }

    // system

    public void addInlineKeyboardMarkupByRowListInArray(BotCommand command,
                                                        List<List<InlineKeyboardButton>> rowList
    ) {
        List<InlineKeyboardButton> keyboardButtonsRow = getOneLineButtonByNuttonEnum(command);
        rowList.add(keyboardButtonsRow);
    }

    private List<InlineKeyboardButton> getOneLineButtonByNuttonEnum(BotCommand command) {
        return getOneLineButton(command.getDisplayText(), command.getCommandText());
    }

    private List<InlineKeyboardButton> getOneLineButton(String buttonName, String buttonCallBackData) {
        InlineKeyboardButton button = new InlineKeyboardButton();
        button.setText(buttonName);
        button.setCallbackData(buttonCallBackData);

        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
        keyboardButtonsRow.add(button);
        return keyboardButtonsRow;
    }

    @Deprecated
    public void addInlineKeyboardMarkupByRowListInArray(BotButtonEnum buttonEnum,
                                                        List<List<InlineKeyboardButton>> rowList
                                                        ) {
        List<InlineKeyboardButton> keyboardButtonsRow = getOneLineButtonByNuttonEnum(buttonEnum);
        rowList.add(keyboardButtonsRow);
    }

    @Deprecated
    private InlineKeyboardMarkup getInlineKeyboardMarkupByRowList(List<List<InlineKeyboardButton>> rowList) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        inlineKeyboardMarkup.setKeyboard(rowList);
        return inlineKeyboardMarkup;
    }

    @Deprecated
    private List<InlineKeyboardButton> getOneLineButtonByNuttonEnum(BotButtonEnum buttonEnum) {
        return getOneLineButton(buttonEnum.getName(), buttonEnum.getCommand());
    }




}
