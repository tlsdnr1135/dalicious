package com.dalicious.dalicious.domain.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDTO {

    private Integer boardId;

    private String title;

    private String content;

}
