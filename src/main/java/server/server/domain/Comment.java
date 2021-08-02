package server.server.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Comment {

    @Id
    @Column(name = "comment_id")
    private Long id;

    private int class_comment;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String content;



    private LocalDateTime datetime;



}
