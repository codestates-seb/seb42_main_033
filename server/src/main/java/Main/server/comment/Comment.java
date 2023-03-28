package Main.server.comment;

import Main.server.audit.Auditable;
import Main.server.board_infj.BoardInfj;
import Main.server.board_infp.BoardInfp;
import Main.server.board_integrated.BoardIntegrated;
import Main.server.board_isfj.BoardIsfj;
import Main.server.board_istj.BoardIstj;
import Main.server.board_istp.BoardIstp;
import Main.server.user.Users;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private String category;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_integrated_id")
    private BoardIntegrated boardIntegrated;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_infj_id")
    private BoardInfj boardInfj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_infp_id")
    private BoardInfp boardInfp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_istj_id")
    private BoardIstj boardIstj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_istp_id")
    private BoardIstp boardIstp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_isfj_id")
    private BoardIsfj boardIsfj;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private Users user;
}