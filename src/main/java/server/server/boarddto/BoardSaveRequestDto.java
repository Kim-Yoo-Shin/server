package server.server.boarddto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.domain.Member;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class BoardSaveRequestDto{
    @NotNull
    @Size(max = 20, min = 2)
    private String title;
    @NotNull
    private String content;
    @NotNull
    @Size(max = 10, min = 6)
    private String password;
    private Category category;

    public static Board changeToBoard(BoardSaveRequestDto boardSaveRequestDto, Member member) {
        Board board = new Board();
        board.setTitle(boardSaveRequestDto.getTitle());
        board.setContent(boardSaveRequestDto.getContent());
        board.setPassword(boardSaveRequestDto.getPassword());
        board.setCategory(boardSaveRequestDto.getCategory());
        board.setMember(member);
        return board;
    }
}