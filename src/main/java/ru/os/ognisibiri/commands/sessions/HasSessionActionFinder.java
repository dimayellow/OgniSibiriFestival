package ru.os.ognisibiri.commands.sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.os.ognisibiri.commands.MapFinder;

import java.util.Map;

@Component
public class HasSessionActionFinder extends MapFinder<HasSessionAction> {

    @Override
    @Autowired
    @Qualifier(value = "sessionalHashMap")
    protected void setMap(Map<String, HasSessionAction> map) {
        super.setMap(map);
    }
}
