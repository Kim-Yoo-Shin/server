package server.server.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Board;
import server.server.dto.BoardDto;
import server.server.service.BoardService;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private BoardService boardService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{boardId}")
    public BoardDto findPathBoard(@PathVariable("boardId") Long id) {

       return boardService.findOne(id);
    }

    

}
