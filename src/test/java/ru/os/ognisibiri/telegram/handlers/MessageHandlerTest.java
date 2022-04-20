package ru.os.ognisibiri.telegram.handlers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.commands.CommandFinder;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.entity.UserSession;
import ru.os.ognisibiri.data.service.BotCommandsService;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;
import ru.os.ognisibiri.exceptions.InvalidCommandNameException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Optional;

@SpringBootTest
class MessageHandlerTest {

    @MockBean
    UserSessionService userSessionService;
    @MockBean
    UserService userService;
    @MockBean
    BotCommandsService botCommandsService;
    @MockBean
    CommandFinder commandFinder;

    @InjectMocks
    MessageHandler messageHandlerForTests;

    @Mock
    Message message;
    @Mock
    UserInBase testUserInBase;

    final long CHAT_ID = 123456789L;
    final String COMMAND = "/testCommand";
    final String CHAT_ID_STRING = "123456789";

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);

        // Message
        LinkedList
        Mockito.when(message.getChatId()).thenReturn(CHAT_ID);
        Mockito.when(message.getText()).thenReturn(COMMAND);

        User userForTests = Mockito.mock(User.class);

//        testUserInBase.setSession(sessionForTest);
        ArrayList
        Mockito.when(message.getFrom()).thenReturn(userForTests);

        BotCommand startBaseCommand = Mockito.mock(BotCommand.class);

        Mockito.when(botCommandsService.getByName("/start"))
                .thenReturn(Optional.of(startBaseCommand));

        // User session
        UserSession sessionForTest = Mockito.mock(UserSession.class);

        Mockito.when(sessionForTest.getCommand()).thenReturn(startBaseCommand);

        // User in base
        Mockito.when(testUserInBase.getChatId()).thenReturn(CHAT_ID_STRING);
    }


    @Test
    void nonExistentCommandThrowException() {

        Mockito.when(botCommandsService.getByName(COMMAND)).thenReturn(Optional.empty());

        Assertions.assertThrows(InvalidCommandNameException.class, () -> messageHandlerForTests.processMessage(message));

    }

    @Test
    void creationNonExistentUser() {

        // Arrange
        BotCommand botCommand = Mockito.mock(BotCommand.class);
        Mockito.when(botCommandsService.getByName(COMMAND)).thenReturn(Optional.of(botCommand));

        Mockito.when(userService.getByChatId(CHAT_ID_STRING)).thenReturn(null);

        // Act

        messageHandlerForTests.processMessage(message);

        Mockito.verify(userService).getByChatId(Mockito.any());
        Mockito.verify(userService).save(Mockito.any());
    }

}