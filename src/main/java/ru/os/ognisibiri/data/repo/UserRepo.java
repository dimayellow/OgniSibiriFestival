package ru.os.ognisibiri.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.os.ognisibiri.data.entity.UserInBase;

import java.util.Optional;

public interface UserRepo extends
        CrudRepository<UserInBase,String> {
    public Optional<UserInBase> findFirstByChatId(String chat_id);
}
