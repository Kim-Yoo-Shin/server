package server.server.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import server.server.controller.pageController;
import server.server.domain.Board;
import server.server.domain.Category;
import server.server.boarddto.BoardDto;
import server.server.boarddto.BoardTitleDto;
import server.server.boarddto.BoardTitlePage;
import server.server.repository.BoardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;




    public BoardDto findOne(Long id) {

        Board board = boardRepository.findOne(id);
        return BoardDto.from(board);
    }

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    public List<Board> findCategory(Category category) {
        return boardRepository.findCategoryAll(category);
    }

    public void plusLikeCount(Long id) {
        boardRepository.plusLikeCount(id);

    }

    public Board update(Board changeBoard) {
        return boardRepository.update(changeBoard);
    }


    /**
     * 처음 화면에서 페이징 처리
     * @param nowPage
     * @return
     */
    public BoardTitlePage findMainPage(int nowPage) {
        BoardTitlePage boardTitlePage = new BoardTitlePage();
        boardTitlePage.setNowPage(nowPage);
        boardTitlePage.setTotalPage(boardRepository.findMainPageCount(boardTitlePage.getPerBoard())
                .intValue());
        List<Board> boards = boardRepository.findMainPageBoard(nowPage
                ,boardTitlePage.getPerBoard());

        List<BoardTitleDto> boardTitleDtos = boardTitlePage.getBoardTitleDtos();
        for (Board board : boards) {
            boardTitleDtos.add(BoardTitleDto.from(board));
        }
        return boardTitlePage;

    }

    /**
     * category별 페이지 처리
     * @param category
     * @param nowPage
     * @return
     */
    public BoardTitlePage findCategoryPage(Category category,int nowPage) {
        BoardTitlePage boardTitlePage = new BoardTitlePage();
        boardTitlePage.setNowPage(nowPage);
        boardTitlePage.setTotalPage(boardRepository.findCategoryPageCount(category,boardTitlePage.getPerBoard())
                .intValue());
        List<BoardTitleDto> boardTitleDtos = boardTitlePage.getBoardTitleDtos();
        List<Board> boards = boardRepository.findCategoryPageBoard(category, nowPage, boardTitlePage.getPerBoard());
        for (Board board : boards) {
            boardTitleDtos.add(BoardTitleDto.from(board));
        }
        return boardTitlePage;

    }




}
