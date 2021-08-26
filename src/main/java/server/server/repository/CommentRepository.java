/*
package server.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import server.server.domain.Comment;

import javax.persistence.*;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CommentRepository {

    public final EntityManager em;


    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findOne(Long id) {
        return em.find(Comment.class, id);
    }

//    public List<Comment> findMemberComment(Long id) {
//        return em.createQuery("select c from Comment c where c.");
//    }


}
*/