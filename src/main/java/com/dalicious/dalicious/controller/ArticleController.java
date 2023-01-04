package com.dalicious.dalicious.controller;

import com.dalicious.dalicious.domain.cms__article;
import com.dalicious.dalicious.domain.request.ArticleRequestDTO;
import com.dalicious.dalicious.domain.response.ArticleResponseDTO;
import com.dalicious.dalicious.service.ArticleService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    //생성된 게시판 PK 리턴
    //게시글 생성
    @PostMapping("/api/article")
    public ResponseEntity<Long> saveArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        return ResponseEntity.ok().body(articleService.saveArticle(articleRequestDTO));
    }

    //게시글 전체 조회
    @GetMapping("/api/article")
    public ResponseEntity<List<ArticleResponseDTO>> getArticleList() {
        return ResponseEntity.ok().body(articleService.getArticleList());
    }

    //게시글 상세 조회
    @GetMapping("/api/article/{id}")
    public ResponseEntity<ArticleResponseDTO> getDetailsArticle(@PathVariable Long id) {
        return ResponseEntity.ok().body(articleService.getDetailsArticle(id));
    }

    //삭제된 개수 반환
    //게시글 삭제
    @DeleteMapping("/api/article/{id}")
    public ResponseEntity<Long> deleteArticle(@PathVariable Long id) {
        return ResponseEntity.ok().body(articleService.deleteById(id));
    }

    //게시물 조회(단어)
    @GetMapping("/api/article/str")
    public ResponseEntity<List<ArticleResponseDTO>>  getArticleListByStr(@RequestParam String title){
        return ResponseEntity.ok().body(articleService.getArticleListByStr(title));
    }

    //게시물 조회(시간)
    @GetMapping("/api/article/date")
    public ResponseEntity<List<ArticleResponseDTO>>  getArticleListByDate(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end
    ){
        //1월 3일 ~ 1월 3일 검색시 end가 3일 00시로 되어 3일이 검색이 안되어 4일 00시로 날짜를 하루 더해줌.
        Timestamp timestampEnd = addOneEndDateTime(end);
        return ResponseEntity.ok().body(articleService.getArticleListByDate(Timestamp.valueOf(start.atStartOfDay()),timestampEnd));
    }

    private Timestamp addOneEndDateTime(LocalDate end) {
        Calendar calendar = Calendar.getInstance();
        Timestamp timestampEnd = Timestamp.valueOf(end.atStartOfDay());
        calendar.setTime(timestampEnd);
        calendar.add(Calendar.DATE,1);
        timestampEnd.setTime(calendar.getTime().getTime());
        return timestampEnd;
    }
}

