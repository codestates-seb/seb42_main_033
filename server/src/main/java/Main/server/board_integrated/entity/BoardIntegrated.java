package Main.server.board_integrated.entity;

import Main.server.audit.Auditable;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

    private String tag;

    private Long commentCount;

    private Long viewCount;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
//    @JsonBackReference
//    private List<Comment> answers = new ArrayList<>();
//
//    @ManyToOne(cascade = CascadeType.PERSIST)
//    @JoinColumn(name = "USER_ID")
//    private Member member;
}
