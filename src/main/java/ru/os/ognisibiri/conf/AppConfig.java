package ru.os.ognisibiri.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.sessions.HasSessionAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfig {

    @Autowired
    private List<HasSessionAction> sessionals;

    @Autowired
    private List<HaveMenu> menus;

    @Bean(name = "sessionalHashMap")
    public Map<String, HasSessionAction> sessionalHashMap() {

        Map<String, HasSessionAction> map = new HashMap<>();
        sessionals.forEach(sessional -> map.put(sessional.getId(), sessional));

        return map;
    }

    @Bean(name = "haveMenuMap")
    public Map<String, HaveMenu> haveMenuMap() {

        Map<String, HaveMenu> map = new HashMap<>();
        menus.forEach(haveMenu -> map.put(haveMenu.getId(), haveMenu));

        return map;
    }

}
