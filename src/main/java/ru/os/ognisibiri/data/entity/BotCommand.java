package ru.os.ognisibiri.data.entity;

import lombok.Data;
import lombok.*;
import org.hibernate.annotations.SQLInsert;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Data
@Table(name = "bot_commands")
public class BotCommand {

    @NotNull
    @NotBlank
    @Id
    @Column(name = "command_id")
    private String commandId;

    @NotNull
    @NotBlank
    @Column(name = "display_text")
    private String displayText;

    @Column(name = "action_name")
    private String actionName;

    @Column(name = "menu_maker_name")
    private String menuMakerName;

    @Column(name = "message")
    private String message;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "back_command_id")
    private BotCommand backCommand;

    @OneToMany()
    @JoinTable(
            name = "available_commands",
            joinColumns = @JoinColumn(
                    name = "owner_id",
                    referencedColumnName = "command_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "available_command_id",
                    referencedColumnName = "command_id"
            )
    )
    List<BotCommand> availableCommands;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BotCommand that = (BotCommand) o;
        return Objects.equals(commandId, that.commandId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commandId);
    }

    @Override
    public String toString() {
        return "BotCommand{" +
                "commandId='" + commandId + '\'' +
                ", displayText='" + displayText + '\'' +
                '}';
    }
}
