package server.server.domain;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import javax.persistence.*;
import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @NotBlank
    @Size(max = 30)
    private String userId;

    @NotBlank
    private String name;

    @NotBlank
    @Size(max = 100)
    private String password;

    @Column(name = "create_data")
    private LocalDateTime datetime;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<RoleDao> roles = new HashSet<>();

    public Member() {
    }
    public Member(String userId, String name, String password,LocalDateTime localDateTime) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.datetime = localDateTime;
    }
}
