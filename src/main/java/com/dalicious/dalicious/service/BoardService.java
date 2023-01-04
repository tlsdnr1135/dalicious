package com.dalicious.dalicious.service;

import com.dalicious.dalicious.domain.cms__board;
import com.dalicious.dalicious.domain.request.BoardRequestDTO;
import com.dalicious.dalicious.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //게시판 전체 조회
    public List<cms__board> getBoardList(){
        List<cms__board> temp = boardRepository.findAll();
        return temp;
    }

    //게시판 생성
    public Long saveBoard(BoardRequestDTO boardRequestDTO){
        //TODO cms__board에 entitiy로 만들기.
        cms__board board = cms__board.builder().name_ko(boardRequestDTO.getTitle()).build();
        boardRepository.saveBoard(board);
        return board.getBoard_id();
    }

}
