package Main.server.message;

import Main.server.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {
     List<Message> findBySender(Users user);


     List<Message> findByReceiver(Users user);


}
