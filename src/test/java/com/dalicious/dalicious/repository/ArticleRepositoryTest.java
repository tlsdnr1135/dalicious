package com.dalicious.dalicious.repository;

import com.dalicious.dalicious.domain.cms__article;
import com.dalicious.dalicious.domain.request.ArticleRequestDTO;
import com.dalicious.dalicious.domain.response.ArticleResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.format.annotation.DateTimeFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

@MybatisTest
//실 데이터 베이스에 연결 시 필요한 어노테이션
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    @DisplayName("1. 게시글 전체 조회")
    public void getArticleListTest() throws Exception{
        //given
        String title = "두번째";

        //when
        List<ArticleResponseDTO> articleResponseDTO = articleRepository.findAll();
        System.out.println(articleResponseDTO.get(0).getTitle());

        //then
        assertEquals(title, articleResponseDTO.get(0).getTitle());

    }

    @Test
    @DisplayName("2. 게시글 저장")
    public void saveArticleTest() throws Exception{
        //given
        Integer boardId = 1;
        String title = "테스트 저장";
        String content = "테스트 내용";
        cms__article article = cms__article.builder()
                .board_id(boardId)
                .title(title)
                .content_html(content)
                .content_string(content)
                .build();

        //when
        articleRepository.saveArticle(article);
        System.out.println(article.getArticle_id());

        //then
        assertTrue(article.getArticle_id().describeConstable().isPresent());
    }

    @Test
    @DisplayName("3. 게시글 상세 조회")
    public void getDetailsArticleTest() throws Exception{
        //given
        Long boardId = 3L;
        String title = "세번째";
        String content = "번쨰 내용";


        //when
        ArticleResponseDTO articleResponseDTO = articleRepository.findById(boardId);

        //then
        assertEquals(title,articleResponseDTO.getTitle());
        assertEquals(content,articleResponseDTO.getContent_html());
    }

    @Test
    @DisplayName("4. 게시글 삭제")
    public void getDeleteArticleTest() throws Exception{
        //given
        Long boardId = 1L;

        //when
        Long returnRow = articleRepository.deleteById(boardId);

        //then
        assertEquals(returnRow,1);
    }

    @Test
    @DisplayName("5. 게시글 조회(단어)")
    public void getArticleListByStrTest() throws Exception{
        //given
        String str = "육";
        String title = "6세번째";
        String content = "6번쨰 내용";

        //when
        List<ArticleResponseDTO> list = articleRepository.findByStr(str);

        //then
        assertEquals(title,list.get(0).getTitle());
        assertEquals(content,list.get(0).getContent_html());
    }

    @Test
    @DisplayName("6. 게시판 제목 검색(시간)")
    public void getArticleListByDateTest() throws Exception{
        //given
        String title = "두번째";
        String content ="두번쨰 내용";

        LocalDate start = LocalDate.parse("2023-01-03",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate end = LocalDate.parse("2023-01-03",
                DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    
        //시간 하루 더하기
        Timestamp timestampEnd = addOneEndDateTime(end);

        Timestamp start1 = Timestamp.valueOf(start.atStartOfDay());

        //when
        List<ArticleResponseDTO> articleResponseDTOS = articleRepository.findByDate(start1,timestampEnd);

        //then
        assertEquals(title,articleResponseDTOS.get(0).getTitle());
        assertEquals(content,articleResponseDTOS.get(0).getContent_html());
    }

    //
    private Timestamp addOneEndDateTime(LocalDate end) {
        Calendar calendar = Calendar.getInstance();
        Timestamp timestampEnd = Timestamp.valueOf(end.atStartOfDay());
        calendar.setTime(timestampEnd);
        calendar.add(Calendar.DATE,1);
        timestampEnd.setTime(calendar.getTime().getTime());
        return timestampEnd;
    }

}
