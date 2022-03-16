package ru.os.ognisibiri.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.os.ognisibiri.telegram.OgniSibiriBot;

@RestController
@AllArgsConstructor
public class WebhookController {

    private final OgniSibiriBot ogniSibiriBot;

    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return ogniSibiriBot.onWebhookUpdateReceived(update);
    }

}