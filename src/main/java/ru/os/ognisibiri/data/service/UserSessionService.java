package ru.os.ognisibiri.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.entity.UserSession;
import ru.os.ognisibiri.data.repo.UserSessionRepo;
import ru.os.ognisibiri.enums.SessionStatusEnum;

@Service
public class UserSessionService {

    @Autowired
    private UserSessionRepo repo;

    public void setSessionStatus(UserInBase user, SessionStatusEnum status) {

        UserSession session = user.getSession();
        if (session == null) {
            session = new UserSession();
        }

        session.setStatusId(status.getId());
        repo.save(session);
    }

    public void clearSession(UserInBase user) {
        if (user.getSession() != null) {
            repo.delete(user.getSession());
        }
    }


}
