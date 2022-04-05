package ru.os.ognisibiri.enums;

public enum BotMessageEnum {

    // exceptions

    ILLEGAL_ARGUMENT_ERROR("Передано некорректное значение"),
    EXCEPTION_ERROR("Произошла непредвиденная ошибка. Обратитесь к администраторам"),
    INVALID_VALUE_IN_SESSION_ERROR("Во время %s передано некорректное значение"),

    // main menu
    MAIN_MENU("Главное меню"),
    LK("Личный кабинет"),

    // change props
    ENTER_FIRSTNAME("Введите имя"),
    ENTER_LASTNAME("Введите имя"),
    ENTER_USERNAME("Введите имя"),
    ;

    private final String message;

    BotMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
