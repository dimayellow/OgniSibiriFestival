package ru.os.ognisibiri.data.repo;

import org.springframework.data.repository.CrudRepository;
import ru.os.ognisibiri.data.entity.BotCommand;

public interface BotCommandsRepo extends
        CrudRepository<BotCommand,String> {

}
