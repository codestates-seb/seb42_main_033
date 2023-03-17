package Main.server.message.dto;

import Main.server.message.entity.Message;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotBlank
    private String senderNickName;
    @NotBlank
    private String receiverNickName;


    public static MessageDto toDto(Message message) {
        return new MessageDto(

                message.getTitle(),
                message.getContent(),
                message.getSender().getNickName(),
                message.getReceiver().getNickName()
        );
    }
}
