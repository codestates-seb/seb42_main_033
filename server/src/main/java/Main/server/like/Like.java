//package Main.server.like;
//
//import Main.server.board_integrated.BoardIntegrated;
//import Main.server.user.entity.Users;
//import com.fasterxml.jackson.annotation.JsonBackReference;
//import lombok.Builder;
//
//import javax.persistence.*;
//
//@MappedSuperclass
//@Builder
//public abstract class Like {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private Users users;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "post_id")
//    @JsonBackReference
//    private BoardIntegrated post;
//
//    private String category;
//}
