package Main.server.like;

import Main.server.board_infj.BoardInfj;
import Main.server.board_infp.BoardInfp;
import Main.server.board_integrated.BoardIntegrated;
import Main.server.board_isfj.BoardIsfj;
import Main.server.board_istj.BoardIstj;
import Main.server.board_istp.BoardIstp;
import Main.server.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "LIKE_TABLE")
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_integrated_id")
    @JsonBackReference
    private BoardIntegrated boardIntegrated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_infj_id")
    @JsonBackReference
    private BoardInfj boardInfj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_infp_id")
    @JsonBackReference
    private BoardInfp boardInfp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_istj_id")
    @JsonBackReference
    private BoardIstj boardIstj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_istp_id")
    @JsonBackReference
    private BoardIstp boardIstp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_isfj_id")
    @JsonBackReference
    private BoardIsfj boardIsfj;

    private String category;
}
