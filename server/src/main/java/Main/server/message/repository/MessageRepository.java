package Main.server.message.repository;

import Main.server.message.dto.MessageDto;
import Main.server.message.entity.Message;
import Main.server.user.entity.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
     List<Message> findBySender(Users user);


     List<Message> findByReceiver(Users user);


}
