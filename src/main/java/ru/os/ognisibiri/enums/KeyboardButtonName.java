package ru.os.ognisibiri.enums;

public enum KeyboardButtonName {

    REGISTER("Зарегистрироваться"),
    CHANGE_DATA("Изменить данные"),
    VIEW_DATA("Посмотреть данные");


    private final String buttonName;

    KeyboardButtonName(String buttonName) {
        this.buttonName = buttonName;
    }

    public String getName() {
        return buttonName;
    }

}
