package Main.server.Comment.entity;

import Main.server.audit.Auditable;
import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.user.entity.Users;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JsonManagedReference
    @JoinColumn(name = "BOARD_ID")
    @JsonIgnore
    private BoardIntegrated board;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private Users user;
}