package ru.os.ognisibiri.commands.sessions.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.os.ognisibiri.commands.sessions.HasSessionAction;
import ru.os.ognisibiri.commands.sessions.HasSessionActionCreationHelper;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;

import java.util.Objects;

@Component
public class ChangeLkUserName implements HasSessionAction {

    private String id = "changeLkUserName";
    private String exceptionMessage = "изменения юзернейма";

    // Services
    @Autowired
    UserService userService;
    @Autowired
    UserSessionService userSessionService;

    @Override
    @Transactional
    public void changeAction(HasSessionActionCreationHelper hasSessionActionCreationHelper) {
        if (hasSessionActionCreationHelper.getUserInBase().getSession() == null)
            throw new IllegalArgumentException();
        userService.changeUserName(hasSessionActionCreationHelper.getUserInBase(), hasSessionActionCreationHelper.getText());
    }

    @Override
    public String getMessage() {
        return exceptionMessage;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChangeLkUserName that = (ChangeLkUserName) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
