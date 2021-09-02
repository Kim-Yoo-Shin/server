package server.server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.boarddto.BoardDto;
import server.server.boarddto.BoardTitlePage;
import server.server.service.BoardService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    

    /**
     * (1) + paging 처리하기 =>
     */
    @GetMapping
    public BoardTitlePage findMainBoardList(@RequestParam(defaultValue = "1",name = "nowpage") int nowPage) {

        BoardTitlePage boardtitlepage = boardService.findMainPage(nowPage);

        return boardtitlepage;
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










