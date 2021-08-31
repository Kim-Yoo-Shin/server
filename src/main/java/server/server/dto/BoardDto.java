package server.server.dto;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.domain.Member;

import java.time.LocalDateTime;


//content
public class BoardDto {
    public final Long id;

    public final String title;

    public final String content;

    public final int likeCount;

    public final Category category;

    public final LocalDateTime dateTime;

    public final Member member;

    public BoardDto(Long id, String title, String content, int likeCount, Category category, LocalDateTime dateTime, Member member) {

        this.id = id;
        this.title = title;
        this.content = content;
        this.likeCount = likeCount;
        this.category = category;
        this.dateTime = dateTime;
        this.member = member;

    }

    public static BoardDto from(Board board) {
        return new BoardDto(board.getId(), board.getTitle()
                , board.getContent(), board.getLikeCount()
                , board.getCategory(), board.getDateTime(), board.getMember());
    }



}
