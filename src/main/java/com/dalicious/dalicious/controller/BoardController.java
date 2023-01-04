package com.dalicious.dalicious.controller;

import com.dalicious.dalicious.domain.cms__board;
import com.dalicious.dalicious.domain.request.BoardRequestDTO;
import com.dalicious.dalicious.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    //게시판 목록 조회
    @GetMapping("/api/board")
    public ResponseEntity<List<cms__board>> getBoardList(){
        List<cms__board> temp = boardService.getBoardList();
        return ResponseEntity.ok().body(temp);
    }

    //게시판 생성
    @PostMapping("/api/board")
    public ResponseEntity<Long> createBoard(@RequestBody BoardRequestDTO boardRequestDTO){
        return ResponseEntity.ok().body(boardService.saveBoard(boardRequestDTO));
    }


}
