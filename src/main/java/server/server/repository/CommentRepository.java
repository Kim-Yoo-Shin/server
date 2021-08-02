package server.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import server.server.domain.Comment;

import javax.persistence.*;


@Repository
@RequiredArgsConstructor
public class CommentRepository {

    public final EntityManager em;



    public void save(Comment comment) {

    }


}
