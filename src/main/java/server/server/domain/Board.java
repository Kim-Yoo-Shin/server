package server.server.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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

    private BoardCategory bgroup;

    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime dateTime;

}
