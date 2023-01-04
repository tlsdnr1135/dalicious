package com.dalicious.dalicious.controller;

import com.dalicious.dalicious.domain.response.ArticleResponseDTO;
import com.dalicious.dalicious.service.ArticleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.ArrayList;
import java.util.List;


//통합테스트
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) //통합테스트
public class ArticleControllerTest {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static ObjectMapper objectMapper;
    private static HttpHeaders httpHeaders;

    @BeforeAll
    public static void init(){
        objectMapper = new ObjectMapper();
        httpHeaders = new org.springframework.http.HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    @Test
    @DisplayName("1. 게시글 전체 조회 테스트")
    public void getArticleListTest() throws Exception{
        //given


        //when
//        HttpEntity<List<articleResponseDTO>> httpEntity = new HttpEntity<>(null,httpHeaders);
        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();

//        ResponseEntity<List<ArticleResponseDTO>> response =
//                testRestTemplate.exchange("/api/article", HttpMethod.GET, null, articleResponseDTOS );

        //then

    }

}
