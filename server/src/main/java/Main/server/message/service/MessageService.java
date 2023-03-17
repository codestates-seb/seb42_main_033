package Main.server.message.service;

import Main.server.advice.BusinessLogicalException;
import Main.server.advice.ExceptionCode;
import Main.server.message.dto.MessageDto;
import Main.server.message.entity.Message;
import Main.server.message.repository.MessageRepository;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageService {
    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Transactional
    public MessageDto createMessage(MessageDto messageDto) {
        Users receiver = userRepository.findByNickName(messageDto.getReceiverNickName());
        Users sender = userRepository.findByNickName(messageDto.getSenderNickName());

        Message message = new Message();
        message.setReceiver(receiver);
        message.setSender(sender);

        message.setTitle(messageDto.getTitle());
        message.setContent(messageDto.getContent());
        message.setDeleteByReceiver(false);
        message.setDeleteBySender(false);
        messageRepository.save(message);

        return MessageDto.toDto(message);
    }

    @Transactional(readOnly = true)
    public List<MessageDto> receivedMessage(Users user) {
        // 받은 편지함 불러오기
        // 한 명의 유저가 받은 모든 메세지
        List<Message> messages = messageRepository.findByReceiver(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for (Message message : messages) {
            // message 에서 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내줌
            if (!message.isDeletedByReceiver()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }



    public List<MessageDto> sentMessage(Users user) {
        // 보낸 편지함 불러오기
        // 한 명의 유저가 받은 모든 메세지
        List<Message> messages = messageRepository.findBySender(user);
        List<MessageDto> messageDtos = new ArrayList<>();

        for(Message message : messages) {
            // message 받은 편지함에서 삭제하지 않았으면 보낼 때 추가해서 보내기
            if(!message.isDeletedBySender()) {
                messageDtos.add(MessageDto.toDto(message));
            }
        }
        return messageDtos;
    }
    // 받은 쪽지 중 1개 확인
    public MessageDto receiveMessage(int messageId, Users user) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> {
            throw new IllegalArgumentException("메세지를 찾을 수 없습니다");
        });
        if(user != message.getReceiver()){
            throw new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
        return MessageDto.toDto(message);
    }

    // 보낸 쪽지 중 1개 확인
    public MessageDto sentMessage(int messageId, Users user) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> {
            throw new IllegalArgumentException("메세지를 찾을 수 없습니다");
        });
        if(user != message.getSender()){
            throw new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
        return MessageDto.toDto(message);
    }


    // 받은 편지 삭제
    @Transactional
    public Object deleteMessageByReceiver(int messageId, Users user) {
        Message message = messageRepository.findById(messageId).orElseThrow(() -> {

            throw new IllegalArgumentException("메세지를 찾을 수 없습니다.");
        });

        if(user == message.getReceiver()) {
            message.deleteByReceiver();     // 받은 사람에게 메세지 삭제
            if(message.isDeleted()) {
                // 받은 사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제 요청
                messageRepository.delete(message);
                return "양쪽 모두 삭제";
            }
            return "한쪽만 삭제";
        } else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
    }



    @Transactional
    // 보낸 편지 삭제
    public Object deleteMessageBySender(int messageId, Users user) {
        Message message = messageRepository.findById(messageId).orElseThrow(()->
        {
            throw new IllegalArgumentException("메세지를 찾을 수 없습니다.");
        });

        if(user == message.getSender()) {
            message.deleteBySender(); // 보낸 메세지 삭제
            if(message.isDeleted()) {
                // 받은 사람과 보낸 사람 모두 삭제했으면, 데이터베이스에서 삭제 요청
                messageRepository.delete(message);
                return "양쪽 모두 삭제";
            }
            return "한쪽만 삭제";
        }
        else {
            return new IllegalArgumentException("유저 정보가 일치하지 않습니다.");
        }
    }

}


