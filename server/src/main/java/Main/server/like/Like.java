package Main.server.like;

import Main.server.board_enfj.BoardEnfj;
import Main.server.board_enfp.BoardEnfp;
import Main.server.board_entj.BoardEntj;
import Main.server.board_entp.BoardEntp;
import Main.server.board_esfj.BoardEsfj;
import Main.server.board_esfp.BoardEsfp;
import Main.server.board_estj.BoardEstj;
import Main.server.board_estp.BoardEstp;
import Main.server.board_infj.BoardInfj;
import Main.server.board_infp.BoardInfp;
import Main.server.board_integrated.BoardIntegrated;
import Main.server.board_intj.BoardIntj;
import Main.server.board_intp.BoardIntp;
import Main.server.board_isfj.BoardIsfj;
import Main.server.board_isfp.BoardIsfp;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_isfp_id")
    @JsonBackReference
    private BoardIsfp boardIsfp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_intj_id")
    @JsonBackReference
    private BoardIntj boardIntj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_intp_id")
    @JsonBackReference
    private BoardIntp boardIntp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_estj_id")
    @JsonBackReference
    private BoardEstj boardEstj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_estp_id")
    @JsonBackReference
    private BoardEstp boardEstp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_esfj_id")
    @JsonBackReference
    private BoardEsfj boardEsfj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_esfp_id")
    @JsonBackReference
    private BoardEsfp boardEsfp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_entj_id")
    @JsonBackReference
    private BoardEntj boardEntj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_entp_id")
    @JsonBackReference
    private BoardEntp boardEntp;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_enfj_id")
    @JsonBackReference
    private BoardEnfj boardEnfj;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_enfp_id")
    @JsonBackReference
    private BoardEnfp boardEnfp;

    private String category;
}
