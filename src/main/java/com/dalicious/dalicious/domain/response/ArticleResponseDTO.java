package com.dalicious.dalicious.domain.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Getter
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
//@AllArgsConstructor
public class ArticleResponseDTO {

    private Long article_id; //게시글 PK

    private Timestamp created_datetime; //생성날짜

    private Boolean is_pinned; //게시판 내 고정여부

    private Integer view_count; //조회수

    private String title; //제목

    private String content_html; //본문


}
