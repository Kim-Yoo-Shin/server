package server.server.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.domain.Member;

import javax.persistence.EntityManager;
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
                .getResultList();
    }

    public List<Board> findMemberBoard(Member member) {
        return em.createQuery("select b from Board b where b.member = :member  ", Board.class)
                .getResultList();
    }

    public void plusLikeCount(Long id) {
        Board board = em.find(Board.class, id);
        board.setLikeCount(board.getLikeCount() + 1);
    }

    public List<Board> findOrderLike() {
       return em.createQuery("select b from Board b order by b.likeCount asc ",Board.class)
                .setFirstResult(0)
                .setMaxResults(20)
                .getResultList();
    }


}
