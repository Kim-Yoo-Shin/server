package server.server.boarddto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardFindResponseDto {
    private Long boardId;
    private String title;
    private String memberName;
    private String content;
    private int likecount;

}