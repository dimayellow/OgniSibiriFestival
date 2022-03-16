package ru.os.ognisibiri.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.entity.UserSession;

import java.util.Optional;

public interface UserSessionRepo extends
        CrudRepository<UserSession,String> {

    public Optional<UserSession> getUserSessionByUser(UserInBase user);


}
