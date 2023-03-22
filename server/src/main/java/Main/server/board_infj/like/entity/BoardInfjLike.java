package Main.server.board_infj.like.entity;

import Main.server.board_infj.BoardInfj;
import Main.server.user.entity.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardInfjLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private BoardInfj post;

    @Builder
    public BoardInfjLike(Users users, BoardInfj post) {
        this.users = users;
        this.post = post;
    }
}
