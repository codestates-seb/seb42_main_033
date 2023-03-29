package Main.server.comment;

import Main.server.audit.Auditable;
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

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_isfp_id")
    private BoardIsfp boardIsfp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_intj_id")
    private BoardIntj boardIntj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_intp_id")
    private BoardIntp boardIntp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_estj_id")
    private BoardEstj boardEstj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_estp_id")
    private BoardEstp boardEstp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_esfj_id")
    private BoardEsfj boardEsfj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_esfp_id")
    private BoardEsfp boardEsfp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_entj_id")
    private BoardEntj boardEntj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_entp_id")
    private BoardEntp boardEntp;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_enfj_id")
    private BoardEnfj boardEnfj;

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "board_enfp_id")
    private BoardEnfp boardEnfp;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "USER_ID")
    private Users user;
}