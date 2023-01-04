package com.dalicious.dalicious.repository;

import com.dalicious.dalicious.domain.cms__board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {

    //게시판 목록 조회
    List<cms__board> findAll();
    void saveBoard(cms__board cms__board);

}
