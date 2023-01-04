package com.dalicious.dalicious.domain;

import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class cms__article {

    private Long article_id; //게시글 PK

    private Integer board_id; //게시판 FK

    private Timestamp created_datetime; //생성날짜

    private Boolean is_pinned; //게시판 내 고정여부

    private Integer view_count; //조회수

    private String title; //제목

    private String content_html; //본문

    private String content_string; //본문 내용만 저장, 검색용

    @Builder
    public cms__article(Long article_id, Integer board_id, Timestamp created_datetime, Boolean is_pinned, Integer view_count, String title, String content_html, String content_string) {
        this.article_id = article_id;
        this.board_id = board_id;
        this.created_datetime = created_datetime;
        this.is_pinned = is_pinned;
        this.view_count = view_count;
        this.title = title;
        this.content_html = content_html;
        this.content_string = content_string;
    }
}
