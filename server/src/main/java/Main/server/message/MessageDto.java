package Main.server.message;

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
