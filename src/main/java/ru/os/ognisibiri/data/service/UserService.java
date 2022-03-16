package ru.os.ognisibiri.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.repo.UserRepo;
import ru.os.ognisibiri.data.repo.UserSessionRepo;

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


}
