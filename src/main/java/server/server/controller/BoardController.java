package server.server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Board;
import server.server.dto.BoardDto;
import server.server.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


    /**
     * board id로 조회하기!!
     * @param id
     * @return
     */
    @GetMapping("/{boardId}")
    public BoardDto findPathBoard(@PathVariable("boardId") Long id) {
        Board board = boardService.findOne(id);
        return BoardDto.from(board);
    }


    /**
     * 전체 보드 조회 하기!!
     * 나눠서 주느
     * @return
     */
    @GetMapping("/best")
    public List<BoardDto> findAllBestBoard() {
        List<Board> boards = boardService.findAll();
        List<BoardDto> boardDtos = new ArrayList<>();
        for (Board board : boards) {
            boardDtos.add(BoardDto.from(board));
        }
        return boardDtos;
    }

    /**
     * board 저장하기!
     * member하고 등록하는 법 공부 후 처리하기.
     * @param board
     */
    @PostMapping("/write")
    public void saveBoard(@RequestBody Board board) {

        boardService.save(board);
    }




}
