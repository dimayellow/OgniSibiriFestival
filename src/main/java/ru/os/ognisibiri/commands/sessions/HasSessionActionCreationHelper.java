package ru.os.ognisibiri.commands.sessions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;

@Data
@Builder
public class HasSessionActionCreationHelper {

    private String text;
    private String chatId;
    private User userInTelegram;
    private UserInBase userInBase;



}
