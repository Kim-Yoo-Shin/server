package server.server.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import server.server.domain.Board;
import server.server.domain.Category;
import server.server.boarddto.BoardDto;
import server.server.boarddto.BoardTitleDto;
import server.server.boarddto.BoardTitlePage;
import server.server.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    public BoardDto findOne(Long id) {

        Board board = boardRepository.findOne(id);
        return BoardDto.from(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }









}
