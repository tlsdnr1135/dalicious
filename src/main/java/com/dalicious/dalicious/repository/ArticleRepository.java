package com.dalicious.dalicious.repository;

import com.dalicious.dalicious.domain.cms__article;
import com.dalicious.dalicious.domain.response.ArticleResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Mapper
public interface ArticleRepository {

    void saveArticle(cms__article article);

    List<ArticleResponseDTO> findAll();
    ArticleResponseDTO findById(Long id);

    void viewArticle(Long id); //조회수 증가.

    //삭제
    Long deleteById(Long id);

    //조회(단어)
    List<ArticleResponseDTO> findByStr(String title);

    //조회(시간)
    List<ArticleResponseDTO> findByDate(Timestamp start, Timestamp end);


}
