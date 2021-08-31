package server.server.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import server.server.domain.Board;
import server.server.domain.Comment;
import server.server.domain.Member;

import javax.persistence.*;
import java.time.LocalDateTime;
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


    public List<Comment> findBoardComment(Board board) {
        return em.createQuery("select c from Comment c where c.board = :board", Comment.class)
                .setParameter("board", board)
                .getResultList();
    }


    //멤버의 댓글 확
    public List<Comment> findMemberComment(Member member) {
        return em.createQuery("select c from Comment c where c.member = :member", Comment.class)
                .setParameter("member", member)
                .getResultList();
    }

    //날짜로 댓글 확
    public List<Comment> findPeriodComment(LocalDateTime startDate, LocalDateTime finalDate) {
        return em.createQuery("select c from Comment c where c.dateTime > :startDate and c.dateTime < :finalDate", Comment.class)
                .setParameter("startDate", startDate)
                .setParameter("finalDate", finalDate)
                .getResultList();
    }




}
