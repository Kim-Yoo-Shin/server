package server.server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import server.server.domain.Board;
import server.server.domain.Category;

import javax.persistence.EntityManager;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class BoardRepositoryTest {

    @Autowired
    EntityManager em;

    @Test
    void save() {
        Board board = new Board();
        board.setDateTime(LocalDateTime.now());
        board.setCategory(Category.FOOD);
        em.persist(board);
        
    }

    @Test
    void findOne() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findCategoryAll() {
    }

    @Test
    void findMemberBoard() {
    }

    @Test
    void plusLikeCount() {
    }

    @Test
    void findOrderLike() {
    }
}