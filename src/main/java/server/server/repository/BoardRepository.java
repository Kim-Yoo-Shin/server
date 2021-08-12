package server.server.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.domain.Comment;
import server.server.domain.Member;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        em.persist(board);
    }

    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }

    public List<Board> findCategoryAll(Category category) {
        return em.createQuery("select b from Board b where b.category = :category", Board.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Board> findMemberBoard(Member member) {
        return em.createQuery("select b from Board b where b.member = :member  ", Board.class)
                .setParameter("member", member)
                .getResultList();
    }

    //날짜로 댓글 조회
    public List<Board> findPeriodBoard(LocalDateTime startDate, LocalDateTime finalDate) {
        return em.createQuery("select b from Board b where b.dateTime > :startDate and b.dateTime < :finalDate", Board.class)
                .setParameter("startDate", startDate)
                .setParameter("finalDate", finalDate)
                .getResultList();
    }

    public void plusLikeCount(Long id) {
        Board board = em.find(Board.class, id);
        board.setLikeCount(board.getLikeCount() + 1);
    }

    //plus 최근 날짜 10일 전까지 추천 게시글
    public List<Board> findOrderLike() {
       return em.createQuery("select b from Board b order by b.likeCount desc ,b.dateTime desc ",Board.class)
                .setFirstResult(0)
                .setMaxResults(20)
                .getResultList();
    }




}
