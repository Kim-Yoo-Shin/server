package server.server.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import javax.persistence.GeneratedValue;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter @Setter

// for Null 필드 제외
@DynamicInsert
@DynamicUpdate
public class Mexmber_1 {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    private String password;

    @Column(name = "create_data")
    private LocalDateTime datetime;







}
