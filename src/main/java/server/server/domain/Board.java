package server.server.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
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

    @NotNull
    @Size(max = 10, min = 6)
    private String password;
    @NotNull
    @Size(max = 20, min = 2)
    private String title;
    @NotNull
//    @Max(value = 2000)
    private String content;
    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime dateTime;

    private int likeCount;

    private LocalDateTime updateDateTime;
}
