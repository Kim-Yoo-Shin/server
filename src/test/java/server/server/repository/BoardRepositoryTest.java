package server.server.repository;

import org.assertj.core.api.Assertions;
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
    @Autowired
    BoardRepository boardRepository;
    @Test
    void save() {
        Board board = new Board();
        board.setDateTime(LocalDateTime.now());
        board.setCategory(Category.FOOD);
        em.persist(board);
        
    }
    @Test
    void findBoardpage(){

        //given
        Board board = new Board();
        Board board1 = new Board();
        board.setCategory(Category.SOCCER);
        board1.setCategory(Category.SOCCER);
        boardRepository.save(board);
        boardRepository.save(board1);
        //when

        Integer page = boardRepository.findBoardPage(Category.SOCCER);

        //then
        Assertions.assertThat(page).isEqualTo(2);
    }


}