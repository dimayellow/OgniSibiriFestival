package ru.os.ognisibiri.telegram;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.os.ognisibiri.enums.BotMessageEnum;
import ru.os.ognisibiri.exceptions.InvalidCommandNameException;
import ru.os.ognisibiri.exceptions.InvalidValueInSessionException;
import ru.os.ognisibiri.telegram.handlers.MessageHandler;

import java.io.IOException;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OgniSibiriBot extends SpringWebhookBot {

    String botPath;
    String botUsername;
    String botToken;

    MessageHandler messageHandler;

    public OgniSibiriBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        super(setWebhook);
        this.messageHandler = messageHandler;
    }


    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return handleUpdate(update);
        } catch (InvalidValueInSessionException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    String.format(BotMessageEnum.INVALID_VALUE_IN_SESSION_ERROR.getMessage(), e.getMessage()));
        } catch (InvalidCommandNameException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ERROR.getMessage());
        } catch (IllegalArgumentException e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.ILLEGAL_ARGUMENT_ERROR.getMessage());
        } catch (Exception e) {
            return new SendMessage(update.getMessage().getChatId().toString(),
                    BotMessageEnum.EXCEPTION_ERROR.getMessage());
        }
    }

    private BotApiMethod<?> handleUpdate(Update update) throws IOException {

        if (update.hasCallbackQuery()) {
            return messageHandler.processMessage(update.getCallbackQuery());
        } else {
            return messageHandler.processMessage(update.getMessage());
        }

    }


}
