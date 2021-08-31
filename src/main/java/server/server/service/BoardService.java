package server.server.service;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.dto.BoardDto;
import server.server.repository.BoardRepository;

import java.time.LocalDateTime;
import java.util.List;
@Service
@RequiredArgsConstructor
//@Transactional
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;


    public void save(Board board) {
        board.setDateTime(LocalDateTime.now());
        boardRepository.save(board);


    }

    public Board findOne(Long id) {
        return boardRepository.findOne(id);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> findCategory(Category category) {
        return boardRepository.findCategoryAll(category);
    }

    public void plusLikeCount(Long id) {
        boardRepository.plusLikeCount(id);

    }

}
