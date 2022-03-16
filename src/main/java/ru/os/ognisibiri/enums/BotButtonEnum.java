package ru.os.ognisibiri.enums;

public enum BotButtonEnum {

    START("Главное меню", "/start"),
    LK("Личный кабинет","/lk"),

    CHANGE_PROPS("Изменить реквизиты", "/changeProps"),

    CHANGE_FIRSTNAME("Имя", "/changeLKFirstName"),
    CHANGE_LASTNAME("Фамилия", "/changeLKLastName"),
    CHANGE_USERNAME("Username", "/changeLKUserName"),
    BACK_TO_CHANGE_PROPS("Назад", "/changeProps"),

    CANCEL_SESSION("Отменить", "/cancelSession")

    ;


    private final String command;
    private String name;

    BotButtonEnum(String name, String command) {
        this.name = name;
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String getName() {
        return name;
    }
}
