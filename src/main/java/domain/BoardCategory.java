package domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class BoardCategory {

    @Id
    @Column("boardcategory_id")
    private String name;

}
