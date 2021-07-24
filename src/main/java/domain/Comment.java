package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment {

    @Id
    @Column("comment_id")
    private Long id;

    @GeneratedValue
    private int order;

    private int class_comment;

    private Member member;

    private String content;

    private LocalDateTime date;




    private
}
