package Main.server.message.entity;

import Main.server.audit.Auditable;
import Main.server.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int messageId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private boolean deleteBySender;

    @Column(nullable = false)
    private boolean deleteByReceiver;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_ID")
    @OnDelete(action = OnDeleteAction.NO_ACTION) // 계정 삭제하면 같이 삭제
    private Users sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "RECEIVER_ID")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Users receiver;


    public void deleteBySender() {
        this.deleteBySender = true;
    }

    public void deleteByReceiver() {
        this.deleteByReceiver = true;
    }

    public boolean isDeleted() {
        return isDeletedBySender() && isDeletedByReceiver();
    }

    public boolean isDeletedBySender() {
        return deleteBySender;
    }

    public boolean isDeletedByReceiver() {
        return deleteByReceiver;
    }

}
