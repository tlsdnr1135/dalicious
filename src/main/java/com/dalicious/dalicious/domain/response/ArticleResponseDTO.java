package com.dalicious.dalicious.domain.response;

import lombok.*;

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

    @Builder
    public ArticleResponseDTO(Long article_id, Timestamp created_datetime, Boolean is_pinned, Integer view_count, String title, String content_html) {
        this.article_id = article_id;
        this.created_datetime = created_datetime;
        this.is_pinned = is_pinned;
        this.view_count = view_count;
        this.title = title;
        this.content_html = content_html;
    }
}
