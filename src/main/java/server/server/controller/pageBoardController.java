package server.server.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.server.boarddto.SortMethod;
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
public class pageBoardController {
    @Autowired
    private final MemberService memberService;
    @Autowired
    private final BoardRepository boardRepository;
    @GetMapping("/user")
    public String getCurrentUserName(HttpServletRequest request){
        return memberService.getCurrentUserInfo(request).get().getName();
    }

    @GetMapping("/board")
    public BoardListPageDto getBoardPage(@RequestParam(name = "category", defaultValue = "MAIN") Category category,
                               @RequestParam(name = "nowpage", defaultValue = "1") int nowPage,
                               @RequestParam(name = "perboard", defaultValue = "10") int perBoard,
                               @RequestParam(name = "sort_index", defaultValue = "NORMAL") SortMethod sortMethod) {
        int totalPage = boardRepository.findPage(category, perBoard);
        List<BoardListDto> boardDataDtoList = boardRepository.findBoardList(category, nowPage, perBoard,sortMethod)
                .stream()
                .map(board -> new BoardListDto(board.getId(), board.getTitle(), board.getContent(), board.getMember().getName(), board.getDateTime(), board.getLikeCount()))
                .collect(Collectors.toList());

        return new BoardListPageDto(boardDataDtoList, new BoardPageDto(totalPage, nowPage, perBoard));
    }


    @Data
    @AllArgsConstructor
    static class BoardListPageDto<T>{
        private T boardData;
        private BoardPageDto pageData;

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
    static class BoardListDto {
        private Long boardId;
        private String title;
        private String content;
        private String userName;
        private LocalDateTime dateTime;
        private int likeCount;


    }
}
