package ru.os.ognisibiri.commands.menuCreators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.os.ognisibiri.commands.MapFinder;

import java.util.Map;

@Component
public class MenuFinder extends MapFinder<HaveMenu> {

    @Override
    @Autowired
    @Qualifier(value = "haveMenuMap")
    protected void setMap(Map<String, HaveMenu> map) {
        super.setMap(map);
    }
}
