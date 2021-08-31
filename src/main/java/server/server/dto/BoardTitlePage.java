package server.server.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BoardTitlePage {
    private int nowPage, totalPage ;
    private final int perBoard = 20;
    private List<BoardTitleDto> boardTitleDtos = new ArrayList<>();


    public BoardTitlePage(int nowPage, int totalPage) {

    }
}
