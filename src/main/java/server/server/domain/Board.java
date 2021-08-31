package server.server.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
// for Null 필드 제외
@DynamicInsert
@DynamicUpdate
public class Board {

    @Id
    @Column(name = "board_id")
    @GeneratedValue
    private Long id;

    private String password;
    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime dateTime;

    private int likeCount;

}
