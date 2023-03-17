package Main.server.board_integrated.like.entity;

import Main.server.board_integrated.entity.BoardIntegrated;
import Main.server.user.entity.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardIntegratedLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private BoardIntegrated post;

    @Builder
    public BoardIntegratedLike(Users users, BoardIntegrated post) {
        this.users = users;
        this.post = post;
    }
}
