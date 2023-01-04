package com.dalicious.dalicious.service;


import com.dalicious.dalicious.domain.cms__article;
import com.dalicious.dalicious.domain.request.ArticleRequestDTO;
import com.dalicious.dalicious.domain.response.ArticleResponseDTO;
import com.dalicious.dalicious.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ArticleServiceTest {

    @InjectMocks
    private ArticleService articleService;

    @Mock
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("1. 게시글 전체 조회 테스트")
    public void getArticleListTest(){
        //given

        //stub
        Timestamp timestamp = Timestamp.valueOf("2023-01-03 10:20:30.0");

        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        articleResponseDTOS.add(ArticleResponseDTO.builder()
                        .article_id(1L)
                        .created_datetime(timestamp)
                        .is_pinned(false)
                        .view_count(0)
                        .title("서비스 테스트 제목1")
                        .content_html("서비스 테스트 내용1")
                .build());

        articleResponseDTOS.add(ArticleResponseDTO.builder()
                .article_id(2L)
                .created_datetime(timestamp)
                .is_pinned(false)
                .view_count(0)
                .title("서비스 테스트 제목2")
                .content_html("서비스 테스트 내용2")
                .build());

        when(articleRepository.findAll()).thenReturn(articleResponseDTOS);

        //when
        List<ArticleResponseDTO> List = articleService.getArticleList();
        System.out.println(List.get(0).getTitle());

        //then
        assertThat(List.get(0).getTitle()).isEqualTo("서비스 테스트 제목1");
        assertThat(List.get(1).getTitle()).isEqualTo("서비스 테스트 제목2");

    }

//    @Test
//    @DisplayName("2. 게시글 등록 테스트")
//    public void saveArticleListTest(){
//        //given
//        ArticleRequestDTO articleRequestDTO = ArticleRequestDTO.builder()
//                .boardId(1)
//                .title("게시글 등록 테스트")
//                .content("게시글 등록 테스트")
//                .build();
//
//        Integer boardId = 1;
//        String title = "테스트 저장";
//        String content = "테스트 내용";
//        cms__article article = cms__article.builder()
//                .board_id(boardId)
//                .title(title)
//                .content_html(content)
//                .content_string(content)
//                .build();
//
//        //stub
//
//        //when
//        articleRepository.saveArticle(article);
//        System.out.println(article.getArticle_id());
//        Long id= articleService.saveArticle(articleRequestDTO);
//
//        //then
////        assertTrue(id != 0);
//    }

    @Test
    @DisplayName("3. 게시글 상세 조회 테스트")
    public void getDetailsArticleTest(){
        //given
        Long id = 1L;

        //stub
        Timestamp timestamp = Timestamp.valueOf("2023-01-03 10:20:30.0");

        ArticleResponseDTO articleResponseDTO = ArticleResponseDTO.builder()
                .article_id(1L)
                .created_datetime(timestamp)
                .is_pinned(false)
                .view_count(0)
                .title("서비스 테스트 제목1")
                .content_html("서비스 테스트 내용1")
                .build();

        when(articleRepository.findById(id)).thenReturn(articleResponseDTO);

        //when
        ArticleResponseDTO article = articleService.getDetailsArticle(id);

        //then
        assertThat(article.getTitle()).isEqualTo("서비스 테스트 제목1");
    }

    @Test
    @DisplayName("4. 게시글 삭제 테스트")
    public void deleteArticleTest(){
        //given
        Long id = 1L;

        //stub
        when(articleRepository.deleteById(id)).thenReturn(1L);

        //when
        Long deleteRow = articleService.deleteById(id);

        //then
        assertThat(deleteRow).isEqualTo(1);
    }

    @Test
    @DisplayName("5. 게시글 조회(단어) 테스트")
    public void getArticleListByStrTest(){
        //given
        Long id = 1L;
        String str = "서비스";

        //stub
        Timestamp timestamp = Timestamp.valueOf("2023-01-03 10:20:30.0");

        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        articleResponseDTOS.add(ArticleResponseDTO.builder()
                .article_id(1L)
                .created_datetime(timestamp)
                .is_pinned(false)
                .view_count(0)
                .title("서비스 테스트 제목1")
                .content_html("서비스 테스트 내용1")
                .build());

        articleResponseDTOS.add(ArticleResponseDTO.builder()
                .article_id(2L)
                .created_datetime(timestamp)
                .is_pinned(false)
                .view_count(0)
                .title("서비스 테스트 제목2")
                .content_html("서비스 테스트 내용2")
                .build());

        when(articleRepository.findByStr(str)).thenReturn(articleResponseDTOS);

        //when
        List<ArticleResponseDTO> List = articleService.getArticleListByStr(str);

        //then
        assertThat(List.get(0).getTitle()).isEqualTo("서비스 테스트 제목1");
        assertThat(List.get(1).getTitle()).isEqualTo("서비스 테스트 제목2");
    }

    @Test
    @DisplayName("6. 게시글 조회(시간) 테스트")
    public void getArticleListByDateTest(){
        //given
        Long id = 1L;

        LocalDate start = LocalDate.parse("2023-01-03",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-01-04",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Timestamp start1 = Timestamp.valueOf(start.atStartOfDay());
        Timestamp end1 = Timestamp.valueOf(end.atStartOfDay());

        //stub
        Timestamp timestamp = Timestamp.valueOf("2023-01-03 10:20:30.0");

        List<ArticleResponseDTO> articleResponseDTOS = new ArrayList<>();
        articleResponseDTOS.add(ArticleResponseDTO.builder()
                .article_id(1L)
                .created_datetime(timestamp)
                .is_pinned(false)
                .view_count(0)
                .title("서비스 테스트 제목1")
                .content_html("서비스 테스트 내용1")
                .build());

        articleResponseDTOS.add(ArticleResponseDTO.builder()
                .article_id(2L)
                .created_datetime(timestamp)
                .is_pinned(false)
                .view_count(0)
                .title("서비스 테스트 제목2")
                .content_html("서비스 테스트 내용2")
                .build());

        when(articleRepository.findByDate(start1,end1)).thenReturn(articleResponseDTOS);

        //when
        List<ArticleResponseDTO> list = articleService.getArticleListByDate(start1,end1);

        //then
        assertThat(list.get(0).getTitle()).isEqualTo("서비스 테스트 제목1");
        assertThat(list.get(1).getTitle()).isEqualTo("서비스 테스트 제목2");
    }

}
