package ru.os.ognisibiri.enums;

import lombok.Getter;

@Getter
public enum SessionStatusEnum {

    CHANGE_FIRSTNAME(1, "change firstname"),
    CHANGE_LASTNAME(2, "change lastname"),
    CHANGE_USERNAME(3, "change username")
    ;

    private final int id;
    private final String statusName;

    SessionStatusEnum(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }



}
