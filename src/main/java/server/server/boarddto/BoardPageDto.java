package server.server.boarddto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardPageDto {
    private int totalPage;
    private int nowPage;
    private int perBoard;
}