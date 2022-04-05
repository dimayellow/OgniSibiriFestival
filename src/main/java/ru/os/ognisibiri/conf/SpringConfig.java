package ru.os.ognisibiri.conf;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.os.ognisibiri.telegram.OgniSibiriBot;
import ru.os.ognisibiri.telegram.handlers.MessageHandler;

@Configuration
@AllArgsConstructor
public class SpringConfig {

    private final TelegramConfig telegramConfig;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(telegramConfig.getWebhookPath()).build();
    }

    @Bean
    public OgniSibiriBot ogniSibiriBot(SetWebhook setWebhook, MessageHandler messageHandler) {
        OgniSibiriBot ogniSibiriBot = new OgniSibiriBot(setWebhook, messageHandler);

        ogniSibiriBot.setBotPath(telegramConfig.getWebhookPath());
        ogniSibiriBot.setBotUsername(telegramConfig.getBotName());
        ogniSibiriBot.setBotToken(telegramConfig.getBotToken());

        return ogniSibiriBot;
    }

}

