package server.server.boarddto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardListDto {
    private Long boardId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime dateTime;
    private int likeCount;


}