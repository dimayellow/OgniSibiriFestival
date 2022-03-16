package ru.os.ognisibiri.enums;

import lombok.Getter;

@Getter
public enum SessionStatusEnum {

    CHANGE_FIRSTNAME(1, "changeLK firstname"),
    CHANGE_LASTNAME(2, "changeLK lastname"),
    CHANGE_USERNAME(3, "changeLK username")
    ;

    private final int id;
    private final String statusName;

    SessionStatusEnum(int id, String statusName) {
        this.id = id;
        this.statusName = statusName;
    }
    
    public static SessionStatusEnum enumById(int id) {

        SessionStatusEnum reply = null;

        for (SessionStatusEnum statusEnum:
             SessionStatusEnum.values()) {
            if (statusEnum.getId() == id)
                reply = statusEnum;
        }

        return reply;
    }



}
