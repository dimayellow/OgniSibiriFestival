package ru.os.ognisibiri.commands;

import java.util.Map;

public abstract class MapFinder<T> {

    private Map<String, T> map;

    protected void setMap(Map<String, T> map) {
        this.map = map;
    };

    public T getByName(String name) {
        T reply = null;

        if (name != null)
            reply = (T) map.get(name);

        return reply;
    }

}
