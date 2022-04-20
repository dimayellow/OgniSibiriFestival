package ru.os.ognisibiri.data.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.repo.BotCommandsRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BotCommandsServiceTest  {

    @MockBean
    BotCommandsRepo repo;

    @InjectMocks
    BotCommandsService service;


    @BeforeEach
    void init_mocks() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getByNameTest() {

        // Arrange

        BotCommand botCommand = Mockito.mock(BotCommand.class);

        Mockito.when(repo.findById("test")).thenReturn(Optional.of(botCommand));

        // Act
        Optional<BotCommand> optionalReply = service.getByName("test");

        // Asserts
        Assertions.assertTrue(optionalReply.isPresent());
        Assertions.assertEquals(optionalReply.get(), botCommand);

    }
}