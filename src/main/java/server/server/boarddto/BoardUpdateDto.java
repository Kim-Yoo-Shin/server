package server.server.boarddto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardUpdateDto{
    private Long boardId;
    private String title;
    private String content;


}