package server.server.controller;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.boarddto.BoardDto;
import server.server.boarddto.BoardTitlePage;
import server.server.domain.Member;
import server.server.repository.BoardRepository;
import server.server.service.BoardService;
import server.server.service.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.net.http.HttpRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {


    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final MemberService memberService;
    /**
     * 보드 등록 + validation처리 + member 더해주는거 처리
     */
    @PostMapping("/save")
    public void saveBoard(HttpServletRequest request, @RequestBody BoardSaveRequestDto bsrd) {
        // 토큰을 받지 않고는 save 테스트 할 수 없다.
        // board member cascade 생각해보기.
//        Member member = memberService.getCurrentUserInfo(request).get();
        Member member = new Member();
        member.setName("aaa");
        member.setPassword("aaa");
        member.setDatetime(LocalDateTime.now());
        member.setUserId("aaa");
        memberService.join(member);
        Board board = BoardSaveRequestDto.changeToBoard(bsrd, member);
        boardRepository.save(board);
    }
    @Data
    @AllArgsConstructor
    static class BoardSaveRequestDto{
        @NotNull
        @Size(max = 20, min = 2)
        private String title;
        @NotNull
        private String content;
        @NotNull
        @Size(max = 10, min = 6)
        private String password;
        private Category category;

        static Board changeToBoard(BoardSaveRequestDto boardSaveRequestDto,Member member) {
            Board board = new Board();
            board.setTitle(boardSaveRequestDto.getTitle());
            board.setContent(boardSaveRequestDto.getContent());
            board.setPassword(boardSaveRequestDto.getPassword());
            board.setCategory(boardSaveRequestDto.getCategory());
            board.setMember(member);
            return board;
        }
    }


    @PostMapping("/count/{boardId}")
    public void plusLikeCount(@PathVariable Long boardId) {
        boardRepository.plusLikeCount(boardId);

    }



}










