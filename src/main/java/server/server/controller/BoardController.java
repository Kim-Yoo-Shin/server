package server.server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.dto.BoardDto;
import server.server.dto.BoardTitleDto;
import server.server.dto.BoardTitlePage;
import server.server.service.BoardService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;


//    /**
//     * board id로 조회하기!!
//     *
//     * @param id
//     * @return
//     */
//    @GetMapping("/{boardId}")
//    public BoardDto findPathBoard(@PathVariable("boardId") Long id) {
//        Board board = boardService.findOne(id);
//        return BoardDto.from(board);
//    }
//
//
//    /**
//     * 전체 보드 조회 하기!!
//     * 나눠서 주느
//     *
//     * @return
//     */
//    @GetMapping("/best")
//    public List<BoardDto> findAllBestBoard() {
//        List<Board> boards = boardService.findAll();
//        List<BoardDto> boardDtos = new ArrayList<>();
//        for (Board board : boards) {
//            boardDtos.add(BoardDto.from(board));
//        }
//        return boardDtos;
//    }
//
//    /**
//     * board 저장하기!
//     * member하고 등록하는 법 공부 후 처리하기.
//     *
//     * @param board
//     */
//    @PostMapping("/write")
//    public void saveBoard(@RequestBody Board board) {
//
//        boardService.save(board);
//    }


    /**
     * (1) + paging 처리하기 =>
     */
    @GetMapping
    public List<BoardTitleDto> findMainBoardList(@RequestParam(defaultValue = "1") int nowPage) {

        boardService.findPage()
        BoardTitlePage boardTitlePage = new BoardTitlePage(nowPage);
        List<Board> boards = boardService.findAll();
        List<BoardTitleDto> boardTitleDtos = new ArrayList<>();
        for (Board board : boards) {
            boardTitleDtos.add(BoardTitleDto.from(board));
        }
        return boardTitleDtos;
    }

    /**
     * (2) + paging 처리
     *
     * @return
     */
    @GetMapping("/{category}")
    public List<BoardTitleDto> findCategoryList(@PathVariable Category category) {
        List<Board> boards = boardService.findCategory(category);
        List<BoardTitleDto> boardTitleDtos = new ArrayList<>();
        for (Board board : boards) {
            boardTitleDtos.add(BoardTitleDto.from(board));
        }
        return boardTitleDtos;
    }

    /**
     * (3) =
     *
     * @param boardId
     * @return
     */
    @GetMapping("/{boardId}")
    public BoardDto findIndiBoard(@PathVariable Long boardId) {
        Board board = boardService.findOne(boardId);
        return BoardDto.from(board);
    }

    /**
     * (4)-1 보드 등록 + validation처리 + member 더해주는거 처리
     */
    @PostMapping("/boards/add")
    public BoardDto saveBoard(@RequestBody Board board) {
        boardService.save(board);
        return BoardDto.from(board);
    }

    /**
     * (5) + validation처리하기.
     */

    @PostMapping("/boards/update")
    public BoardDto updateBoard(@RequestBody Board changeBoard) {
        Board board = boardService.update(changeBoard);

        return BoardDto.from(board);
    }

    /**
     * (6)  plustcount
     */
    @PostMapping("/boards/count/{boardId}")
    public BoardDto plusBoard(@PathVariable Long boardId) {
        boardService.plusLikeCount(boardId);
        Board board = boardService.findOne(boardId);
        return BoardDto.from(board);
    }


}










