package com.dalicious.dalicious.service;

import com.dalicious.dalicious.domain.cms__article;
import com.dalicious.dalicious.domain.request.ArticleRequestDTO;
import com.dalicious.dalicious.domain.response.ArticleResponseDTO;
import com.dalicious.dalicious.repository.ArticleRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    //게시글 생성
    public Long saveArticle(ArticleRequestDTO articleRequestDTO){
        //파라미터에다 직접 DTO를 바인딩 해도 상관이 없을까?
        //TODO 엔티티쪽으로 빼기
        cms__article article = cms__article.builder()
                .board_id(articleRequestDTO.getBoardId())
                .title(articleRequestDTO.getTitle())
                .content_html(articleRequestDTO.getContent())
                .content_string(articleRequestDTO.getContent())
                .build();

        articleRepository.saveArticle(article);
        return article.getArticle_id();
    }

    //게시글 전체 조회
    public List<ArticleResponseDTO> getArticleList(){
        return articleRepository.findAll();
    }

    //게시글(단어)
    public List<ArticleResponseDTO> getArticleListByStr(String title){
        return articleRepository.findByStr(title);
    }

    //게시글(시간)
    public List<ArticleResponseDTO> getArticleListByDate(Timestamp start, Timestamp end){
        return articleRepository.findByDate(start,end);
    }

    //게시글 상세 조회
    public ArticleResponseDTO getDetailsArticle(Long id){
        articleRepository.viewArticle(id);
        return articleRepository.findById(id);
    }

    //게시글 삭제
    public Long deleteById(Long id){
        return articleRepository.deleteById(id);
    }

}
