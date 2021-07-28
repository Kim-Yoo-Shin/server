package server.server.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter

public class Comment {

    @Id
    @Column(name = "comment_id")
    private Long id;

    private int class_comment;

    private Member member;

    private String content;

    private LocalDateTime date;



}
