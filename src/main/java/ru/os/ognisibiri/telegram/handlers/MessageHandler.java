package ru.os.ognisibiri.telegram.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.commands.CommandFinder;
import ru.os.ognisibiri.commands.menuCreators.HaveMenu;
import ru.os.ognisibiri.commands.menuCreators.MenuCreationHelper;
import ru.os.ognisibiri.commands.sessions.HasSessionAction;
import ru.os.ognisibiri.commands.sessions.HasSessionActionCreationHelper;
import ru.os.ognisibiri.data.entity.BotCommand;
import ru.os.ognisibiri.data.entity.UserInBase;
import ru.os.ognisibiri.data.service.BotCommandsService;
import ru.os.ognisibiri.data.service.UserService;
import ru.os.ognisibiri.data.service.UserSessionService;
import ru.os.ognisibiri.exceptions.InvalidCommandNameException;
import ru.os.ognisibiri.exceptions.InvalidValueInSessionException;

import java.util.Optional;

@Component
public class MessageHandler {

    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private UserService userService;
    @Autowired
    private BotCommandsService botCommandsService;
    @Autowired
    private CommandFinder commandFinder;



    public BotApiMethod<?> processMessage(Message message) throws IllegalArgumentException {

        String chatId   = message.getChatId().toString();
        String text  = message.getText().trim();
        User user       = message.getFrom();

        return checkSessionAndSendAnswer(chatId, user, text);

    }

    public BotApiMethod<?> processMessage(CallbackQuery buttonQuery) throws IllegalArgumentException {

        Message message = buttonQuery.getMessage();

        String chatId = message.getChatId().toString();
        User user     = buttonQuery.getFrom();
        String text   = buttonQuery.getData().trim();

        return checkSessionAndSendAnswer(chatId, user, text);

    }

    private SendMessage checkSessionAndSendAnswer(String chatId, User user, String text) throws IllegalArgumentException {

        UserInBase userInBase = checkRegistrationAndRegisterIfNot(chatId, user);

        BotCommand nextStepCommand = getNextStepBotCommand(chatId, user, text, userInBase);

        userSessionService.setCurrentCommandForUser(nextStepCommand, userInBase);

        return createReturnMenu(chatId, nextStepCommand);

    }

    private SendMessage createReturnMenu(String chatId, BotCommand nextStepCommand) {
        HaveMenu menuCreator = commandFinder.getHaveMenuMapFinder().getByName(nextStepCommand.getMenuMakerName());
        return menuCreator.createMenu(nextStepCommand, chatId);
    }

    private BotCommand getNextStepBotCommand(String chatId, User user, String text, UserInBase userInBase) throws IllegalArgumentException {
        BotCommand nextStepCommand;

        BotCommand currentCommand = userInBase.getSession().getCommand();

        if (currentCommand.getActionName() != null) {
            nextStepCommand = getBackCommandAfterCompliteSessionAction(chatId, user, text, userInBase);
        } else {
            nextStepCommand = checkInputDateAndReturnBotCommand(text, currentCommand);
        }
        return nextStepCommand;
    }

    private BotCommand checkInputDateAndReturnBotCommand(String text, BotCommand currentCommand) {
        BotCommand nextStepCommand;
        Optional<BotCommand> optionalBotCommand = botCommandsService.getByName(text);

        if (optionalBotCommand.isEmpty()) throw new InvalidCommandNameException();

        BotCommand inputBotCommand = optionalBotCommand.get();

        if (!isItsAvailableCommand(currentCommand, inputBotCommand)) throw new InvalidCommandNameException("Воспользуйтесь командами текущего меню.");

        nextStepCommand = inputBotCommand;
        return nextStepCommand;
    }

    private BotCommand getBackCommandAfterCompliteSessionAction(String chatId, User user, String text, UserInBase userInBase) {

        BotCommand nextStepCommand;
        BotCommand currentCommand = userInBase.getSession().getCommand();

        if (text.equals(currentCommand.getBackCommand().getCommandId()))
            nextStepCommand = currentCommand.getBackCommand();
        else {
            String sessionActionName = currentCommand.getActionName();
            HasSessionAction sessionAction = commandFinder.getHasSessionActionMapFinder().getByName(sessionActionName);

            // Нельзя выбрать комманду кроме возврата
            if (text.startsWith("/"))
                throw new InvalidValueInSessionException(sessionAction.getMessage());

            HasSessionActionCreationHelper helper = sessionAction.getHasSessionActionCreationHelper(chatId, user, text, userInBase);
            sessionAction.changeAction(helper);

            nextStepCommand = currentCommand.getBackCommand();
        }
        return nextStepCommand;
    }

    private boolean isItsAvailableCommand(BotCommand currentCommand, BotCommand inputBotCommand) {
        return inputBotCommand.getCommandId().equals("/start")
                || inputBotCommand.equals(currentCommand.getBackCommand())
                || currentCommand.getAvailableCommands().contains(inputBotCommand);
    }

    private UserInBase checkRegistrationAndRegisterIfNot(String chatId,User user) {
        UserInBase userInBase = userService.getByChatId(chatId);

        if (userInBase == null) {
            userInBase = registerUser(chatId, user);
        }
        return userInBase;
    }

    private UserInBase registerUser(String chatId, User user) {

        UserInBase userInBase = new UserInBase(user, chatId);

        userService.save(userInBase);

        // Все новые пользователи начинают со старта
        BotCommand StartCommand = findCommandByName("/start");
        userInBase = userSessionService.setCurrentCommandForUser(StartCommand, userInBase);

        return userInBase;
    }

    private BotCommand findCommandByName(String commandName) {
        Optional<BotCommand> optionalCommand = botCommandsService.getByName(commandName);
        if (!optionalCommand.isPresent()) throw new InvalidCommandNameException();
        return optionalCommand.get();
    }
}
