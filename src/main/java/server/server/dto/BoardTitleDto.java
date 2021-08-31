package server.server.dto;

import lombok.Getter;
import lombok.Setter;
import server.server.domain.Board;
import server.server.domain.Member;

import java.time.LocalDateTime;


//no content
@Getter
@Setter
public class BoardTitleDto {

    public Long id;
    public String title;
    public int likeCount;
    public Member member;
    public LocalDateTime localDateTime;
    public BoardTitleDto(Long id, String title, int likeCount, Member member,LocalDateTime localDateTime) {
        this.id = id;
        this.title = title;
        this.likeCount = likeCount;
        this.member = member;
        this.localDateTime = localDateTime;
    }
    public static BoardTitleDto from(Board board) {
        return new BoardTitleDto(board.getId(), board.getTitle(),
                board.getLikeCount(),
                board.getMember(),
                board.getDateTime());
    }
}
