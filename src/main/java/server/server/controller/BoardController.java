package server.server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.boarddto.BoardDto;
import server.server.boarddto.BoardTitlePage;
import server.server.repository.BoardRepository;
import server.server.service.BoardService;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {


    private final BoardService boardService;
    private final BoardRepository boardRepository;

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
    public BoardTitlePage findMainBoardList(@RequestParam(defaultValue = "1",name = "nowpage") int nowPage) {

        BoardTitlePage boardtitlepage = boardService.findMainPage(nowPage);

        return boardtitlepage;
    }
    @GetMapping("/111")
    public void find() {
        for (int i = 0; i < 133; i++) {
            Board board = new Board();
            board.setTitle(Integer.toString(i));
            boardService.save(board);
        }

    }

    /**
     * (2) category board paging
     * @param category
     * @param nowPage
     * @return
     */

    @GetMapping("/{category}")
    public BoardTitlePage findCategoryList(@PathVariable Category category,
                                           @RequestParam(name = "nowpage", defaultValue = "1") int nowPage) {
        return boardService.findCategoryPage(category, nowPage);
    }

    /**
     * (3) =
     *
     * @param boardId
     * @return
     */
    @GetMapping("indi/{boardId}")
    public BoardDto findIndiBoard(@PathVariable Long boardId) {
        return boardService.findOne(boardId);
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
        return boardService.findOne(boardId);

    }



}










