package server.server.controller;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import server.server.boarddto.BoardFindResponseDto;
import server.server.boarddto.BoardSaveRequestDto;
import server.server.boarddto.BoardUpdateDto;
import server.server.domain.Board;
import server.server.domain.Member;
import server.server.repository.BoardRepository;
import server.server.service.MemberService;

import javax.servlet.http.HttpServletRequest;

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
        Member member = memberService.getCurrentUserInfo(request).get();
//        Member member = new Member();
//        member.setName("aaa");
//        member.setPassword("aaa");
//        member.setDatetime(LocalDateTime.now());
//        member.setUserId("aaa");
        memberService.join(member);
        Board board = BoardSaveRequestDto.changeToBoard(bsrd, member);
        boardRepository.save(board);
    }


    @GetMapping("/find")
    public BoardFindResponseDto findBoard(@RequestParam("boardid") Long boardId) {
        Board board = boardRepository.findBoard(boardId);
        return new BoardFindResponseDto(board.getId(), board.getTitle(), board.getMember().getName(), board.getContent(), board.getLikeCount());
    }

    @GetMapping("/count")
    public void plusLikeCount(@RequestParam("boardid") Long boardId) {
        boardRepository.plusLikeCount(boardId);

    }

    //비밀번호 확인 체크.
    @PostMapping("/update")
    public void updateBoardData(@RequestBody BoardUpdateDto boardUpdateDto){
        boardRepository.updateBoard(boardUpdateDto.getBoardId(), boardUpdateDto.getTitle(), boardUpdateDto.getContent());


    }





}










