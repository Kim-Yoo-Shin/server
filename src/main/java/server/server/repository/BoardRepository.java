package server.server.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.domain.Member;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional()
public class BoardRepository {

    private final EntityManager em;

    public void save(Board board) {
        board.setDateTime(LocalDateTime.now());
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

//    /**
//     * //plus 최근 날짜 10일 전까지 추천 게시글
//     * @return
//     */
//    /*public List<Board> findOrderLike() {
//       return em.createQuery("select b from Board b order by b.likeCount desc ,b.dateTime desc ",Board.class)
//                .setFirstResult(0)
//                .setMaxResults(20)
//                .getResultList();
//    }*/
//
//    /**
//     * board 페이지 가져오기
//     */
//
//    public Integer findBoardPage(Category category) {
//        return em.createQuery("select count(b) from Board b where b.category = :category", Integer.class)
//                .setParameter("category", category)
//                .getSingleResult();
//
//    }


    public Board update(Board changeBoard) {
        changeBoard.setDateTime(LocalDateTime.now());
        Board oriBoard = findOne(changeBoard.getId());
        return boardChange(oriBoard, changeBoard);


    }

    public Board boardChange(Board oriBoard, Board changeboard) {
        oriBoard.setDateTime(changeboard.getDateTime());
        oriBoard.setTitle(changeboard.getTitle());
        oriBoard.setContent(changeboard.getContent());
        return oriBoard;
    }


    public Long categoryfindPage(Category category) {
        return em.createQuery("select count(b.id) from Board b where b.category = :category ", Long.class)
                .setParameter("category", category)
                .getSingleResult();

    }
    public Long findPage() {
        return em.createQuery("select count(b.id) from Board b ", Long.class)
                .getSingleResult();
    }

}
