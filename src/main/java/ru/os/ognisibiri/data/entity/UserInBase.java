package ru.os.ognisibiri.data.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class UserInBase {

    @NotNull
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @NotBlank
    @Column(name = "chat_id")
    private String chatId;

    @NotNull
    @NotBlank
    @Column(name = "user_id")
    private Long userId;

    private String firstName;
    private String lastName;
    private String userName;
    private String languageCode;

    private boolean isAdmin;

    @OneToOne(mappedBy = "user",
    cascade = CascadeType.ALL)
    private UserSession session;

    public UserInBase(org.telegram.telegrambots.meta.api.objects.User user, String chatId) {
        this.userId = user.getId();
        this.firstName = user.getFirstName();
        this.userName = user.getUserName();
        this.languageCode = user.getLanguageCode();
        this.lastName = user.getLastName();
        this.chatId = chatId;
    }

    public void makeAdmin() {
        this.isAdmin = true;
    }

    @Override
    public String toString() {
        return "UserInBase{" +
                "id='" + id + '\'' +
                ", chatId='" + chatId + '\'' +
                ", userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInBase that = (UserInBase) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
