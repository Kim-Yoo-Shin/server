package server.server.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import server.server.boarddto.SortMethod;
import server.server.domain.Board;
import server.server.domain.Category;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardRepository {

    private final EntityManager em;

    @Transactional
    public void save(Board board) {
        board.setDateTime(LocalDateTime.now());
        board.setUpdateDateTime(LocalDateTime.now());
        em.persist(board);
    }
    public Board findOne(Long id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll() {
        return em.createQuery("select b from Board b", Board.class).getResultList();
    }





    public int findPage(Category category,int perBoard) {
        int boardNum = em.createQuery("select count(b) from Board b where b.category = :category", Long.class)
                .setParameter("category", category)
                .getSingleResult().intValue();
        return (boardNum / perBoard) + 1;
    }

    public List<Board> findBoardList(Category category, int nowPage, int perBoard, SortMethod sortMethod) {
        String query = "select b " +
                "from Board b join fetch b.member m " +
                "where b.category = :category " +
                "order by ";
        query = queryMade(query, sortMethod);
        return em.createQuery(query, Board.class)
                .setParameter("category", category)
                .setFirstResult((nowPage - 1) * perBoard)
                .setMaxResults(nowPage * perBoard)
                .getResultList();
    }
    public String queryMade(String query, SortMethod sortMethod){
        if (sortMethod == SortMethod.NORMAL) {
            query = query + "b.dateTime desc";
        }
        else if(sortMethod == SortMethod.LIKE){
            query = query + "b.likeCount desc";
        }

        return query;
    }
    @Transactional
    public void plusLikeCount(Long id) {
        Board board = findOne(id);
        board.setLikeCount(board.getLikeCount() + 1);
    }

    public Board findBoard(Long boardId) {
        return em.createQuery("select b " +
                "from Board b join fetch b.member " +
                "where b.id = :boardId ",Board.class)
                .setParameter("boardId",boardId)
                .getSingleResult();
    }

    @Transactional
    public void updateBoard(Long boardId, String title, String content) {
        Board board = findBoard(boardId);
        board.setContent(content);
        board.setTitle(title);
        board.setUpdateDateTime(LocalDateTime.now());

    }
}
