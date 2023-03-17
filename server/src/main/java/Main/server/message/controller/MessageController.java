package Main.server.message.controller;

import Main.server.message.dto.MessageDto;

import Main.server.message.repository.MessageRepository;
import Main.server.message.service.MessageService;

import Main.server.response.Response;
import Main.server.user.entity.Users;
import Main.server.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;



@RestController
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    // 쪽지 보내기
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/messages")
    public Response sendMessage(@RequestBody MessageDto messageDto) {
        // jwt 적용 -> 로그인 된 유저의 정보를 넘겨줘야함
        Users user = userRepository.findByNickName(messageDto.getSenderNickName());
        messageDto.setSenderNickName(user.getNickName());

        return new Response<>("성공", "쪽지를 보냈습니다.", messageService.createMessage(messageDto));
    }

    // 받은 편지함 확인
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/received")
    public Response getReceivedMessages(@RequestBody MessageDto messageDto) {
        // jwt 도입 후 로그인 된 유저 정보 넘기기
    Users user = userRepository.findByNickName(messageDto.getReceiverNickName());

    return new Response("성공", "수신 쪽지를 모두 불러왔습니다.", messageService.receivedMessage(user));
    }


    // 받은 쪽지 중 한 개 확인
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/received/{message-id}")
    public Response getReceiveMessage(@PathVariable("message-id") Integer messageId, @RequestBody MessageDto messageDto) {
        Users user = userRepository.findByNickName(messageDto.getReceiverNickName());
        return new Response<>("불러오기 성공", "받은 쪽지를 불러왔습니다", messageService.receiveMessage(messageId, user));
    }

    // 보낸 쪽지 중 한 개 확인
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/sent/{message-id}")
    public Response getSentMessage(@PathVariable("message-id") Integer messageId, @RequestBody MessageDto messageDto) {
        Users user = userRepository.findByNickName(messageDto.getSenderNickName());
        return new Response<>("불러오기 성공", "보낸 쪽지를 불러왔습니다", messageService.sentMessage(messageId, user));
    }

    // 받은 쪽지 삭제하기
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/received/{message-id}")
    public Response deleteReceivedMessage (@PathVariable("message-id") Integer messageId, @RequestBody MessageDto messageDto) {
        // 위와 동일
        Users user = userRepository.findByNickName(messageDto.getReceiverNickName());
        return new Response<>("삭제 성공", "받은 쪽지인," + messageId + "번 쪽지를 삭제했습니다.", messageService.deleteMessageByReceiver(messageId, user));

    }

    //  보낸 편지함 확인
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/messages/sent")
    public Response getSentMessages(@RequestBody MessageDto messageDto) {
        // 위와 동일
        Users user = userRepository.findByNickName(messageDto.getSenderNickName());
        return new Response<>("성공", "보낸 쪽지를 불러왔습니다.", messageService.sentMessage(user));
    }

    // 보낸 쪽지 삭제
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/messages/sent/{message-id}")
    public Response deleteSentMessage (@PathVariable("message-id") Integer messageId,  @RequestBody MessageDto messageDto) {
        // 위와 동일
        Users user = userRepository.findByNickName(messageDto.getSenderNickName());
        return new Response<>("삭제 성공", "보낸 쪽지인, " + messageId + "번 쪽지를 삭제했습니다.", messageService.deleteMessageBySender(messageId,user));
    }

    @Bean
    public PageableHandlerMethodArgumentResolver pageableResolver() {
        return new PageableHandlerMethodArgumentResolver();
    }
}

