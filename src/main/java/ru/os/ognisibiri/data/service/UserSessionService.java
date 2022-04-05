package ru.os.ognisibiri.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.entity.UserSession;
import ru.os.ognisibiri.data.repo.UserSessionRepo;

@Service
public class UserSessionService {

    @Autowired
    private UserSessionRepo repo;

    public void setCurrentCommandForUser(BotCommand command, UserInBase user) {

        UserSession session = user.getSession();
        if (session == null) {
            session = new UserSession();
            session.setUser(user);
        }
        session.setCommand(command);

        repo.save(session);
    }





}
