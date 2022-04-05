package ru.os.ognisibiri.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.repo.UserRepo;
import ru.os.ognisibiri.data.repo.UserSessionRepo;
import ru.os.ognisibiri.enums.SessionStatusEnum;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private UserSessionRepo sessionRepo;

    public UserInBase getByChatId(String chatId) {
        Optional<UserInBase> user = repo.findFirstByChatId(chatId);
        if(user.isPresent())
            return user.get();
        else
            return null;
    }

    public void save (UserInBase user) {
        repo.save(user);
    }

    @Deprecated
    public void changeBySessionStatus(UserInBase user, String text, SessionStatusEnum statusEnum) {
        if (statusEnum == SessionStatusEnum.CHANGE_FIRSTNAME) changeFirstName(user, text);
        else if (statusEnum == SessionStatusEnum.CHANGE_LASTNAME) changeLastName(user, text);
        else if (statusEnum == SessionStatusEnum.CHANGE_USERNAME) changeUserName(user, text);
    }

    public void changeFirstName(UserInBase user, String name) {
        user.setFirstName(name);
        save(user);
    }

    public void changeLastName(UserInBase user, String lastName) {
        user.setLastName(lastName);
        save(user);
    }

    public void changeUserName(UserInBase user, String userName) {
        user.setUserName(userName);
        save(user);
    }

}
