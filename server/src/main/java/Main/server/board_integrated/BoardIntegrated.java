package Main.server.board_integrated;

import Main.server.comment.Comment;
import Main.server.audit.Auditable;
import Main.server.user.Users;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class BoardIntegrated extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String category;

    private String tag;

    private Long commentCount;

    private Long viewCount;

    private Long likeCount;

    @JsonManagedReference
    @OneToMany(mappedBy = "boardIntegrated", cascade = CascadeType.PERSIST)
    private List<Comment> comment = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;
}
