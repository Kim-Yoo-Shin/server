package server.server.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


<<<<<<< Updated upstream
import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.util.Date;
import java.util.List;
=======
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

>>>>>>> Stashed changes

@Entity
@Table(name = "member")
@Getter @Setter

// for Null 필드 제외
@DynamicInsert
@DynamicUpdate
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    @Column(name = "create_data")
    private Date createDate;

<<<<<<< Updated upstream
    private List<Board> boardList;
=======

>>>>>>> Stashed changes
}
