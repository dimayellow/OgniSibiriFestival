package ru.os.ognisibiri.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.repo.BotCommandsRepo;

import java.util.Optional;

@Service
public class BotCommandsService {

    @Autowired
    BotCommandsRepo repo;

    public Optional<BotCommand> getByName(String commandName) {
        return repo.findById(commandName);
    }


}
