package com.dalicious.dalicious.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class cms__board {

    private Long board_id; //게시판 PK

    private String name_ko; //게시판 명


    @Builder
    public cms__board(String name_ko) {
        this.board_id = board_id;
        this.name_ko = name_ko;
    }

}
