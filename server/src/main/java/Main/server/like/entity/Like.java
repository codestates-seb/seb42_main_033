package Main.server.like.entity;

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
@Table(name = "LIKE_TABLE")
public class Like {
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
    
    private String category;

    @Builder
    public Like(Users users, BoardIntegrated post, String category) {
        this.users = users;
        this.post = post;
        this.category = category;
    }
}
