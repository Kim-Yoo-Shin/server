package server.server.boarddto;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class BoardListPageDto<T>{
    private T boardData;
    private BoardPageDto pageData;

}
