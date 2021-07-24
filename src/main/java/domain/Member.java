package domain;


import domain.board.Board;
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
public class Member {

    @Id
    @Column("member_id")
    private String id;

    private String name;

    private String password;

    private LocalDateTime dateTime;

    private List<Board> boards = new ArrayList<>();
}
