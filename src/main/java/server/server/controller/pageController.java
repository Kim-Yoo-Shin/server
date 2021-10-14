package server.server.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Category;
import server.server.repository.BoardRepository;
import server.server.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/page")
@RequiredArgsConstructor
@Slf4j
public class pageController {
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final BoardRepository boardRepository;
    @GetMapping("/user")
    public String getCurrentUserName(HttpServletRequest request){
        return memberService.getCurrentUserInfo(request).get().getName();
    }

    @GetMapping("/board")
    public Result getBoardPage(@RequestParam(name = "category", defaultValue = "MAIN") Category category,
                               @RequestParam(name = "nowpage", defaultValue = "1") int nowPage,
                               @RequestParam(name = "perboard", defaultValue = "10") int perBoard) {
        int totalPage = boardRepository.findPage(category, perBoard);
        List<BoardDataDto> boardDataDtoList = boardRepository.findBoard(category, nowPage, perBoard)
                .stream()
                .map(board -> new BoardDataDto(board.getId(), board.getTitle(), board.getContent(), board.getMember().getName(), board.getDateTime(), board.getLikeCount(), new BoardPageDto(totalPage, nowPage, perBoard)))
                .collect(Collectors.toList());

        return new Result(boardDataDtoList);
    }


    @Data
    @AllArgsConstructor
    static class Result<T>{
        private T data;

    }
    @Data
    @AllArgsConstructor
    static class BoardPageDto {
        private int totalPage;
        private int nowPage;
        private int perBoard;
    }
    @Data
    @AllArgsConstructor
    static class BoardDataDto {
        private Long boardId;
        private String title;
        private String content;
        private String userName;
        private LocalDateTime dateTime;
        private int likeCount;
        private BoardPageDto boardPageDto;


    }
}
