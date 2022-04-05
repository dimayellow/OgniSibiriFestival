package ru.os.ognisibiri.data.entity;

import lombok.Data;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
@Table(name = "botCommands")
public class BotCommand {

    @NotNull
    @NotBlank
    @Id
    @Column(name = "commandId")
    private String commandId;

    @NotNull
    @NotBlank
    @Column(name = "displayText")
    private String displayText;

    @Column(name = "actionName")
    private String actionName;

    @Column(name = "menuMakerName")
    private String menuMakerName;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "backCommandId")
    private BotCommand backCommand;

    @OneToMany()
    @JoinTable(
            name = "availableCommands",
            joinColumns = @JoinColumn(
                    name = "botCommands",
                    referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "botCommands",
                    referencedColumnName = "id"
            )
    )
    List<BotCommand> availableCommands;

}
