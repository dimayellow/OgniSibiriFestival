package ru.os.ognisibiri.commands.sessions;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.os.ognisibiri.data.entity.UserInBase;

import java.util.Objects;

@Data
@Builder
public class HasSessionActionCreationHelper {

    private String text;
    private String chatId;
    private User userInTelegram;
    private UserInBase userInBase;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HasSessionActionCreationHelper helper = (HasSessionActionCreationHelper) o;
        return Objects.equals(text, helper.text) && Objects.equals(chatId, helper.chatId) && Objects.equals(userInTelegram, helper.userInTelegram) && Objects.equals(userInBase, helper.userInBase);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, chatId, userInTelegram, userInBase);
    }
}
